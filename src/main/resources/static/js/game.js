/* eslint-disable no-undef */
//import {Stomp} from "https://cdn.jsdelivr.net/npm/stomp-websocket@2.3.4-next/lib/stomp.min.js";

const username = prompt("Nickname:")?.trim() || "anon" + Math.floor(Math.random()*999);

/* ---------------------------------------------------------------- STOMP */
let stomp;
function connectSocket() {
    const sock = new SockJS("/ws");
    stomp = Stomp.over(sock);
    stomp.debug = null; // Disable STOMP debug messages
    stomp.connect({}, () => {
        stomp.subscribe("/topic/game-state", onState);
        // tell server we “joined”
        stomp.send("/app/chat.addUser", {}, JSON.stringify({sender: username, type: "JOIN"}));
    });
}
function sendKey(key, pressed) {
    if (!stomp?.connected) return;
    const msg = {type:"INPUT", player:username, key, pressed};
    stomp.send("/app/game.input", {}, JSON.stringify(msg));
}
/* ---------------------------------------------------------------- Phaser */
const config = {
    type: Phaser.AUTO,
    width: 800, height: 450,
    backgroundColor: "#202020",
    physics: { default: "arcade", arcade: {gravity:{y:0},debug:false} },
    scene: { preload, create, update }
};
const game = new Phaser.Game(config);

const players = new Map();   // name -> sprite

function preload() {
    this.load.image("p", "img/quirbi.png"); // 32×32 placeholder
    this.load.image("fist", "img/fist.png");
}

function create() {
    connectSocket();

    // ← → Jump (↑ OR SPACE)
    this.cursors = this.input.keyboard.addKeys({
        LEFT : Phaser.Input.Keyboard.KeyCodes.LEFT,
        RIGHT: Phaser.Input.Keyboard.KeyCodes.RIGHT,
        JUMP : Phaser.Input.Keyboard.KeyCodes.UP,
        ATTACK : Phaser.Input.Keyboard.KeyCodes.SPACE
    });

    // attach listeners; the closure already knows `name`
    Object.entries(this.cursors).forEach(([name, key]) => {
        key.on("down", () => sendKey(name, true));   // ← no toKey() needed
        key.on("up",   () => sendKey(name, false));
    });
}


function update() {
    // nothing here – server is authoritative; we only render snapshots
}

function initializePlayer(name, p) {
    const scene = game.scene.scenes[0];
    // sprite (feet at its own x/y)
    const sprite = scene.add.sprite(0, 0, "p").setOrigin(0.5, 1);

    // label, 4 px above the sprite’s head
    const label = scene.add
                      .text(0, -(sprite.displayHeight + 4), name + ` [${p.damage}]`,
                            { font: "12px Arial", fill: "#ffffff" })
                      .setOrigin(0.5, 1);

    // group them together
    const container = scene.add.container(p.x, p.y, [sprite, label]);
    // Store additional properties on the container
    container.sprite = sprite;
    container.label = label;
    container.attackSprite = null;
    // highlight the local player
    if (name === username) sprite.setTint(0xccddff);
    players.set(name, container);
}

/* Receive state. */
function onState(frame) {
    const state = JSON.parse(frame.body).state;
    // log stuff
    let brief = Object.entries(state.players).map(([name,p]) => name + ' - ' + Object.entries(p).map(([k,v]) => k + ':' + v).join(', '));
    console.log(brief);
    // 1½ lines per player: create if missing, tween toward new pos
    Object.entries(state.players).forEach(([name, p]) => {

        if (!players.has(name)) {
            initializePlayer(name, p);
        }

        const container = players.get(name);
        // show / hide fist while attackFrame > 0
        if (p.attackFrame > 0) {
            if (!container.attackSprite) {
                let spriteX = p.facingRight ? 32 : -32;
                const attack = game.scene.scenes[0].add
                                .sprite(spriteX, 0, "fist")
                                .setOrigin(0.5, 1)
                                .setFlipX(!p.facingRight)
                                .setDisplaySize(32, 32);
                container.add(attack);
                container.attackSprite = attack;
            }
        } else {
            if (container.attackSprite) {
                container.remove(container.attackSprite);
                container.attackSprite.destroy();
                container.attackSprite = null;
            }
        }
        container.label.setText(name + ` [${p.damage}]`);

        game.scene.scenes[0].tweens.add({
            targets: container,
            x: p.x, y: p.y,
            ease: "Linear",
            duration: 30
        });
    });
    // remove disconnected players
    for(const n of players.keys())
        if(!state.players[n]){ players.get(n).destroy(); players.delete(n);}
}
