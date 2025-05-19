/* Helper to turn a value into percentage of its max */
const pct = (val, max) => `${(val / max * 100).toFixed(0)}%`;

const grid   = document.getElementById('fighters');
const nickIn = document.getElementById('nick');
const playBt = document.getElementById('play');

let chosenId = null;

/* 1) fetch fighters, compute the maxima for each attribute, render the cards */
fetch('/api/fighters')
    .then(r => r.json())
    .then(list => {

        /* Find maxima – makes the bars adapt automatically if new fighters appear */
        const maxDamage    = Math.max(...list.map(f => f.attack.damage));
        const maxKnockback = Math.max(...list.map(f => f.attack.knockback));
        const maxSpeed     = Math.max(...list.map(f => f.speed));
        const maxJump      = Math.max(...list.map(f => f.jumpPower / f.weight));
        const maxWeight    = Math.max(...list.map(f => f.weight));

        list.forEach(f => {
            const card = document.createElement('div');
            card.className = 'card';

            card.innerHTML = `
                <img src="${f.imageUrl}" alt=""><br>

                <div class="stats">
                    <div class="stat"><span>DMG</span><div class="bar"><div class="fill" style="width:${pct(f.attack.damage , maxDamage)}"></div></div></div>
                    <div class="stat"><span>KB</span> <div class="bar"><div class="fill" style="width:${pct(f.attack.knockback , maxKnockback)}"></div></div></div>
                    <div class="stat"><span>SPD</span><div class="bar"><div class="fill" style="width:${pct(f.speed          , maxSpeed)}"></div></div></div>
                    <div class="stat"><span>JMP</span><div class="bar"><div class="fill" style="width:${pct(f.jumpPower / f.weight      , maxJump)}"></div></div></div>
                    <div class="stat"><span>WGT</span><div class="bar"><div class="fill" style="width:${pct(f.weight         , maxWeight)}"></div></div></div>
                </div>`;

            card.onclick = () => {
                document.querySelectorAll('.card').forEach(c => c.classList.remove('selected'));
                card.classList.add('selected');
                chosenId = f.id;
                playBt.disabled = false;
            };

            grid.appendChild(card);
        });
    });

/* 2) start the game */
playBt.onclick = () => {
    const nick   = (nickIn.value || '').trim();
    const params = new URLSearchParams({
        name   : nick || ('anon' + Math.floor(Math.random() * 999)),
        fighter: chosenId
    });
    window.location.href = '/game.html?' + params.toString();
};
