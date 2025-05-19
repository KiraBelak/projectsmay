/* eslint-disable no-undef */
const urlParams = new URLSearchParams(location.search);
const username  = urlParams.get('name')    || 'anon' + Math.floor(Math.random()*999);
const fighterId = Number(urlParams.get('fighter')) || null;

const players = new Map();   // name -> sprite
let fightersMap = new Map();

/* STOMP */
let stomp;
function connectSocket() {
    const sock = new SockJS("/ws");
    stomp = Stomp.over(sock);
    stomp.debug = null; // Disable STOMP debug messages
    stomp.connect({}, () => {
        stomp.subscribe("/topic/game-state", onState);
    });
}
function sendKey(key, pressed) {
    if (!stomp?.connected) return;
    const msg = {
        type     : "INPUT",
        player   : username,
        key,
        pressed,
        fighterId
    };
    stomp.send("/app/game.input", {}, JSON.stringify(msg));
}
/* Phaser */
const config = {
    type: Phaser.AUTO,
    width: 800, height: 450,
    backgroundColor: "#555555",
    physics: { default: "arcade", arcade: {gravity:{y:0},debug:false} },
    scene: { preload, create, update }
};
const game = new Phaser.Game(config);


function preload() {
    const scene = this;
    scene.load.image("p", "img/char24.png"); // 32×32 placeholder
    scene.load.image("fist", "img/fist.png");
    scene.load.image("flash", "img/flash.png");
    fetch("/api/fighters").then(r => r.json())
    .then(fs => {
        fightersMap = new Map(fs.map(f => [f.id, f]));
        fs.forEach(fighter => {
            scene.load.image("f_" + fighter.id, fighter.imageUrl);
        });
        scene.load.once('complete', () => console.log('all fighters loaded'));
        scene.load.start();
    });
}

function create() {
    const phaserScene = this;

    // Draw the scene
    fetch("/api/scene")
    .then(r => r.json())
    .then(scene => {
        // Draw every block of the scene
        const g = phaserScene.add.graphics({ fillStyle: { color: 0x000000 } });
        scene.blocks.forEach(b => g.fillRect(b.x, b.y, b.width, b.height));
        connectSocket();
    });
    // ← → Jump (↑ OR SPACE)
    this.cursors = this.input.keyboard.addKeys({
        LEFT : Phaser.Input.Keyboard.KeyCodes.LEFT,
        RIGHT: Phaser.Input.Keyboard.KeyCodes.RIGHT,
        JUMP : Phaser.Input.Keyboard.KeyCodes.UP,
        ATTACK : Phaser.Input.Keyboard.KeyCodes.SPACE
    });
    // attach listeners
    Object.entries(this.cursors).forEach(([name, key]) => {
        key.on("down", () => sendKey(name, true));   // ← no toKey() needed
        key.on("up",   () => sendKey(name, false));
    });
}


function update() {
    // server is authoritative; whole game loop runs on onState()
}

function initializePlayer(name, p) {
    const scene = game.scene.scenes[0];
    // sprite
    // playerFighter from fightersMap or random from fightersMap
    const playerFighter = fightersMap.get(p.fighterId) || fightersMap.values().next().value;
    const sprite = scene.add
        .sprite(0, 0, "f_" + playerFighter.id)
        .setOrigin(0.5, 1)
        .setDisplaySize(32, 32);
    // name and damage label, 4 px above the sprite’s head
    const label = scene.add
        .text(0,
            -(sprite.displayHeight + 4),
            name + ` [${p.damage}]`,
            { font: "12px Arial", fill: "#ffffff" })
        .setOrigin(0.5, 1);
    const container = scene.add.container(p.x, p.y, [sprite, label]);
    container.sprite = sprite;
    container.label = label;
    container.attackSprite = null;
    if (name === username) sprite.setTint(0xccddff); // highlight the local player
    players.set(name, container);
}

/* Receive state. Main game loop. */
function onState(frame) {
    const state = JSON.parse(frame.body).state;
    // log stuff
    let brief = Object.entries(state.players).map(([name,p]) => name + ' - ' + Object.entries(p).map(([k,v]) => k + ':' + v).join(', '));
    console.log(brief);
    Object.entries(state.players).forEach(([name, p]) => {
        if (!players.has(name)) {
            initializePlayer(name, p);
        }
        const container = players.get(name);
        container.label.setText(name + ` [${p.damage}]`);
        container.sprite.setFlipX(!p.facingRight);
        // attack sprite
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
            else {
                container.attackSprite.setFlipX(!p.facingRight);
                container.attackSprite.setX(p.facingRight ? 32 : -32);
            }
        } else {
            if (container.attackSprite) {
                container.remove(container.attackSprite);
                container.attackSprite.destroy();
                container.attackSprite = null;
            }
        }
        // hit sprite, show flash
        if (p.hitCooldown > 0 && p.hitCooldown <= 1) {
            if (!container.flashSprite) {
                const flash = game.scene.scenes[0].add
                                .sprite(0, 0, "flash")
                                .setOrigin(0.5, 0.7)
                                .setDisplaySize(150, 150);
                container.add(flash);
                container.flashSprite = flash;
            }
        } else {
            if (container.flashSprite) {
                container.remove(container.flashSprite);
                container.flashSprite.destroy();
                container.flashSprite = null;
            }
        }


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
