# The Space Cowboy — Card Design Doc

A living reference for Spike's card pool: what each card is, the **Cowboy Bebop source** (why it's a card), and the **design rationale** (why it has that effect). Numbers reflect the current build.

### Roadmap status (v0.0.4-art)
- **[done] Block-debuff bug** — boosted-Flow block now routes through `applyPowers` so Dexterity/Frail apply in both branches (Rain, and the new My Funny Valentine).
- **[done] Added** (14 cards): Asteroid Blues, Bohemian Rhapsody, My Funny Valentine, The Egg and You, Road to the West, Farewell Blues, Ave Maria (Agent 47 art), Blue, Words That We Couldn't Say, Goodnight Julia, Call Me Call Me, Memory, 24 Hours Open, Fantaisie Sign. See **New cards (v0.0.3)** below.
- **[done] Cosmic Dare → Rush** (renamed; new charging-action art). **Mushroom Samba → Mushroom Hunting.**
- **[done] Art pass (v0.0.4):** Strike renamed → "Straight Lead" (EN) / "直拳" (ZH). Defend renamed → "Dodge" / "闪避". Shoot now applies 1 Vulnerable (description updated both locales). Cosmic Dare renamed to Rush (IMG path updated in `CosmicDare_Spike.java`). Full card art replaced with episode screenshots or curated generation: Shoot, Memory, AskDNA, AsteroidBlues, AveMaria, SpokeyDokey, TheRealFolkBlues, CallMeCallMe, TwentyFourHoursOpen, DontBotherNone, Dodge (Defend), CowboyFunk, GoodnightJulia, FantaisieSign.
- **[done] Removed** (unregistered from the pool, class files left dormant on disk): Cat Blues, Stray Dog Strut, Piano Black, Wild Horses, Pierrot le Fou. The Swordfish II relic carries that thread.
- **[done] Long titles auto-shrink** — `patches/CardTitleAutoShrinkPatch` scales any over-wide title to fit the banner (resolution/zoom-independent; tune `MAX_TITLE_LOCAL_WIDTH`).
- **[done] Gotta Knock a Little Harder** double-damage now works (post-multiply in `applyPowers`/`calculateCardDamage`).

## Where to edit
- **Effect numbers** (damage / block / Flow / cost / upgrade amounts): the per-card class in `src/main/java/cards/<Name>_Spike.java` — see the `private static final` constants (`DAMAGE`, `BLOCK`, `FLOW`, `COST`, `UPGRADE_*`).
- **Card name + description text** (EN / 简中): `src/main/resources/localization/Spike_cards-en.json` and `Spike_cards-zh.json` (keyed by card ID). Iconic titles (episode/song names, "Bang.") stay English in both; generic terms are translated (Strike → 打击, Defend → 防御, Jeet Kune Do → 截拳道). Numbers in text use `!D!` (damage), `!B!` (block), `!M!` (magic number).
- **Which cards appear in the game**: registered in `SpikeMod.loadCardsToAdd()`.
- **Starting deck**: `characters/Spike.java` → `getStartingDeck()`.

## Core mechanic — Flow (势)
A momentum resource (custom power `FlowPower`). It starts at 0 each combat, persists through the fight, and has no hard cap.
- **Builders** (most commons) grant Flow when played.
- **Payoffs** (uncommons/rares) either *scale with* current Flow or *consume* it.
- The deck loop: **commons build Flow & survive → uncommons leverage it (scaling/draw/energy) → rares cash it in (esp. `Bang.`)**.

**Balance target:** intentionally a touch above a base-game character — strong scaling engine (Flow) + high burst ceiling (Bang.), kept honest by setup cost and self-damage.

---

## Basics (starting deck: 4 Straight Lead, 1 Shoot, 4 Dodge, 1 Jeet Kune Do)

### Straight Lead — Basic · 1⚡ · Attack  *(card ID: `Strike_Spike`)*
- **Effect:** Deal 6 damage. (Upgrade: 9.)
- **Source:** The Straight Lead is the foundational Jeet Kune Do punch — Spike's bread-and-butter strike, borrowed straight from Bruce Lee.
- **Design:** The baseline attack; unchanged from vanilla math. Renamed from "Strike" to fit the character.

### Dodge — Basic · 1⚡ · Skill  *(card ID: `Defend_Spike`)*
- **Effect:** Gain 5 Block. (Upgrade: 8.)
- **Source:** Spike doesn't *defend* — he slips, weaves, and redirects. JKD is evasion-first.
- **Design:** Baseline block; unchanged from vanilla math. Renamed from "Defend" to fit the character.

### Shoot — Basic · 1⚡ · Attack
- **Effect:** Deal 6 damage. Apply 1 Vulnerable. (Upgrade: 9 damage.)
- **Source:** Spike's a gunman (the Jericho 941) — the ranged counterpart to Straight Lead. Art: episode frame of Spike firing upward on a train.
- **Design:** Puts a gun in the opening hand. Adds a small debuff to differentiate it from the melee basic — a gunshot sets up the next attack.

### Jeet Kune Do — Basic · 1⚡ · Attack
- **Effect:** Deal 5 damage. Gain 1 Flow. (Upgrade: deal 7.)
- **Source:** Spike's actual martial art (Bruce Lee's JKD). The character's signature basic — the "Bash slot."
- **Design:** Teaches the Flow mechanic from turn one; a slightly weaker Strike that pays it back in momentum.

---

## Commons — workhorses (cheap, reliable, build Flow)

### Don't Bother None — Common · 1⚡ · Attack
- **Effect:** Deal 7. Gain 1 Flow. (Upgrade: 10.)
- **Source:** Seatbelts track.
- **Design:** The bread-and-butter Flow-building strike — efficient, repeatable.

### Cat Blues — Common · 1⚡ · Skill  *([REMOVED v0.0.3] unregistered; replaced by Memory)*
- **Effect:** Gain 6 Block. Gain 1 Flow. (Upgrade: 9.)
- **Source:** Seatbelts track.
- **Design:** The defensive counterpart to Don't Bother None — block while building Flow.

### Bad Dog No Biscuits — Common · 1⚡ · Attack
- **Effect:** Deal 3 damage twice. (Upgrade: 4×2.)
- **Source:** Seatbelts track.
- **Design:** Multi-hit — strong with Strength and against Block; great damage-per-energy when scaled.

### Spokey Dokey — Common · 0⚡ · Attack
- **Effect:** Deal 4. (Upgrade: 6.)
- **Source:** Seatbelts track. (Art: Spike on the blues harmonica.)
- **Design:** Zero-cost chain fuel — free to play, keeps "cards played" payoffs and combos rolling.

### Felt Tip Pen — Common · 0⚡ · Skill
- **Effect:** Draw 1 card. Gain 1 Flow. (Upgrade: gain 2 Flow.)
- **Source:** Seatbelts track (Edward vibes).
- **Design:** Cheap cycle — replaces itself and feeds Flow so chains don't stall.

### Rain — Common · 1⚡ · Skill
- **Effect:** Gain 5 Block; if you have 3+ Flow, gain 8 instead. (Upgrade: 7 / 11.)
- **Source:** Seatbelts track (the church scene).
- **Design:** First Flow *threshold* payoff — rewards getting into the Flow with efficient block.

### Stray Dog Strut — Common · 1⚡ · Attack  *([REMOVED v0.0.3] unregistered; replaced by Farewell Blues)*
- **Effect:** Deal 6. Draw 1. (Upgrade: 9.)
- **Source:** Session 2 (Ein's intro).
- **Design:** Cheap tempo attack that keeps the hand flowing.

### Rush — Common · 1⚡ · Attack  *(renamed from "Cosmic Dare"; card ID stays `CosmicDare_Spike`)*
- **Effect:** Deal 8. Gain 1 Flow. (Upgrade: 11.)
- **Source:** Cosmic Dare is Faye's OVA song and reads as a defensive/feminine theme; "Rush" keeps the gunslinger-charge fantasy. Art: Spike charging the viewer with speed lines.
- **Design:** The gunslinger common — highest common single-target damage while still building Flow.

### Piano Black — Common · 1⚡ · Skill  *([REMOVED v0.0.3] unregistered; replaced by 24 Hours Open)*
- **Effect:** Gain 4 Block. Draw 1. (Upgrade: 7.)
- **Source:** Seatbelts track.
- **Design:** Block + draw filler — smooths draws while defending.

---

## Uncommons — the engine (leverage Flow)

### Metal Queen — Uncommon · 2⚡ · Attack
- **Effect:** Deal 8, +2 per Flow (does not consume Flow). (Upgrade: 12 base.)
- **Source:** Session 7 (V.T., the trucker).
- **Design:** The primary Flow *payoff* attack — turns banked Flow into repeatable big hits without spending it.

### Jupiter Jazz — Uncommon · 1⚡ · Power
- **Effect:** At end of turn, gain 1 Strength (2 if you have 6+ Flow). (Upgrade: 2 / 3.)
- **Source:** Sessions 10–11 (Gren's saxophone).
- **Design:** The scaling engine — rewards sustaining high Flow with snowballing Strength.

### Waltz for Venus — Uncommon · 1⚡ · Skill
- **Effect:** Gain 8 Block. Draw 1. (Upgrade: 11.)
- **Source:** Session 8.
- **Design:** Efficient defense + card flow; the reliable block uncommon.

### Honky Tonk Women — Uncommon · 1⚡ · Skill
- **Effect:** Gain 2 Flow. Draw 1. (Upgrade: 3 Flow.)
- **Source:** Session 3 (Faye, the casino).
- **Design:** Flow burst + draw — accelerates the engine.

### Wild Horses — Uncommon · 1⚡ · Skill · Retain  *([REMOVED v0.0.3] unregistered; kept as the Swordfish II relic)*
- **Effect:** Retain. Gain 8 Block. Gain 2 Flow. (Upgrade: 11 Block.)
- **Source:** Session 19 (repairing the Swordfish II).
- **Design:** A retained setup piece — hold it, bank block + Flow, then unload on the kill turn.

### Sympathy for the Devil — Uncommon · 1⚡ · Attack
- **Effect:** Deal 9. Heal 2 HP; if you have 5+ Flow, heal 5 instead. (Upgrade: 12.)
- **Source:** Session 6 (Wen, the immortal child).
- **Design:** Sustain attack — lifesteal that scales with Flow, offsetting the deck's self-damage.

### Cowboy Funk — Uncommon · 1⚡ · Attack
- **Effect:** Deal 5 twice; if you have 3+ Flow, deal 5 three times. (Upgrade: 7.)
- **Source:** Session 22 (Cowboy Andy duel).
- **Design:** Multi-hit that *adds a hit* when flowing — big Strength/scaling payoff.

### Speak Like a Child — Uncommon · 1⚡ · Skill
- **Effect:** Draw 2 cards. Gain 1 Flow. (Upgrade: draw 3.)
- **Source:** Session 18 (the Betamax tape).
- **Design:** The draw engine — keeps the chain going and digs for payoffs.

### Mushroom Hunting — Uncommon · 0⚡ · Skill · Exhaust
- **Effect:** Gain 3 Flow. Draw 1 card. Exhaust. (Upgrade: 4 Flow.)
- **Source:** Session 17 (the psychedelic mushrooms).
- **Design:** A one-shot Flow spike — instantly arms a finisher turn.

### Green Bird — Uncommon · 2⚡ · Skill
- **Effect:** Gain 12 Block. Gain 1 Dexterity. (Upgrade: 16 Block.)
- **Source:** Seatbelts track (the church ascension).
- **Design:** Big defensive payoff with permanent scaling (Dexterity) for block decks.

### Gotta Knock a Little Harder — Uncommon · 1⚡ · Attack
- **Effect:** Deal 8 damage; doubled (16) while at or below half HP. (Upgrade: 11 / 22.)
- **Source:** "Gotta Knock a Little Harder" — the *Knockin' on Heaven's Door* movie ending theme.
- **Design:** Comeback attack — synergizes with the deck's self-damage/death theme (Bang., Carry That Weight, Space Lion).

---

## Rares — showpieces / win conditions

### Bang. — Rare · 2⚡ · Attack · Exhaust
- **Effect:** Deal 9, +3 per Flow（power applys). Lose all Flow. At the start of your next turn, lose half your current HP. Exhaust.
  - **Upgrade:** more damage per Flow, and instead of half-HP — **if any enemy is alive next turn, you die.**
- **Source:** Spike's iconic finger-gun "Bang." — the show's ending.
- **Design:** The deck's **win condition.** Everything builds Flow so this lands lethal; the "Carry That Weight" cost (and true die-next-turn upgrade) makes it a real, faithful gamble. Backed by the *See You Space Cowboy* relic.

### Ballad of Fallen Angels — Rare · 2⚡ · Attack (AoE)
- **Effect:** Deal 8 to ALL enemies. Gain 2 Flow. (Upgrade: 11.)
- **Source:** Session 5 (the cathedral shootout — Spike falls through the rose window).
- **Design:** The AoE rare — clears rooms and still feeds Flow.

### Tank! — Rare · 2⚡ · Power
- **Effect:** At the start of each turn, gain 2 Flow. (Upgrade: 3.)
- **Source:** The opening theme.
- **Design:** The Flow *engine* power — sustained momentum that makes Heavy Metal Queen / Bang. scale every turn.

### The Real Folk Blues — Rare · 3⚡ · Attack (AoE)
- **Effect:** Deal 7 to ALL enemies. Draw 2 cards. Gain 2 Flow. (Upgrade: 10.)
- **Source:** The finale (the ending theme).
- **Design:** A big swing-and-refuel — AoE damage while restocking your hand and Flow for the next turn.

### Pierrot le Fou — Rare · 2⚡ · Attack · Exhaust  *([REMOVED v0.0.3] unregistered; glass-cannon slot now Ave Maria)*
- **Effect:** Deal 20. Lose 4 HP. Exhaust. (Upgrade: 26.)
- **Source:** Session 20 (Mad Pierrot / Tongpu).
- **Design:** Glass-cannon burst — huge single-target damage with a recoil cost (and synergy with Space Lion).

### Space Lion — Rare · 1⚡ · Power
- **Effect:** Whenever you lose HP, gain 2 Strength. (Upgrade: 3.)
- **Source:** Seatbelts track (Gren's death — the show's most elegiac piece).
- **Design:** Turns the deck's self-damage theme (Bang., Pierrot, Carry That Weight) into a comeback engine.

### Jamming with Edward — Rare · 1⚡ · Skill · Exhaust
- **Effect:** Gain Flow equal to the number of cards in your hand. Draw 2. Exhaust. (Upgrade: draw 3.)
- **Source:** Session 9 (Ed hacks into the Bebop).
- **Design:** Explosive Flow burst from a full hand — the fastest way to arm a lethal Bang.

### Ask DNA — Rare · 1⚡ · Skill · Exhaust
- **Effect:** Your next Attack this turn is played twice. Exhaust. (Upgrade: no longer Exhausts.)
- **Source:** "Ask DNA" — the *Knockin' on Heaven's Door* movie opening theme.
- **Design:** DNA = replication — a combo enabler that doubles a finisher (Bang., Heavy Metal Queen, Pierrot le Fou).

---

## New cards (v0.0.3)

> These 14 replace the 5 removed cards and deepen the curve (more commons, a debuff, a recursion piece, Flow→Block defense, a non-suicidal Flow finisher, and two engine powers). Class/ID in parentheses; iconic titles stay English in both locales.

### Commons (workhorse refill)

- **Memory** *(`Memory_Spike`)* — 1⚡ Skill — Gain 5 Block, gain 1 Flow. (Up: 8.) **Source:** the gentle opening-track ED. **Design:** defensive Flow-builder; fills Cat Blues' slot.
- **Farewell Blues** *(`FarewellBlues_Spike`)* — 1⚡ Attack — Deal 6, draw 1. (Up: 9.) **Source:** the upbeat dixieland send-off. **Design:** tempo attack + cycle; fills Stray Dog Strut's slot.
- **24 Hours Open** *(`TwentyFourHoursOpen_Spike`)* — 1⚡ Skill — Gain 4 Block, draw 1. (Up: 7.) **Source:** the all-night diner/convenience-store vibe. **Design:** block + draw filler; fills Piano Black's slot.
- **Fantaisie Sign** *(`FantaisieSign_Spike`)* — 0⚡ Skill — Gain 2 Flow. (Up: also draw 1.) **Source:** Seatbelts track. **Design:** zero-cost Flow accelerant — arms threshold/finisher turns cheaply.

### Uncommons (utility + engine)

- **Asteroid Blues** *(`AsteroidBlues_Spike`)* — 1⚡ Attack — Deal 9; if it's the first card you play this turn, gain 2 Flow. (Up: 12.) **Source:** Session 1, the explosive debut. **Design:** rewards leading with it — opener tempo.
- **My Funny Valentine** *(`MyFunnyValentine_Spike`)* — 1⚡ Skill · Retain — Gain Block equal to 2× your Flow. (Up: 3×.) **Source:** Session 7, Faye's lost past. **Design:** the defensive Flow payoff — hold it, then convert banked Flow into a wall. Respects Dexterity/Frail.
- **Bohemian Rhapsody** *(`BohemianRhapsody_Spike`)* — 1⚡ Skill — Scry 3, gain 2 Flow. (Up: Scry 4.) **Source:** Session 13, the chessmaster's gambit. **Design:** deck-smoothing Flow-builder (pairs with Ein).
- **Words That We Couldn't Say** *(`WordsWeCouldntSay_Spike`)* — 1⚡ Attack — Deal 6, apply 2 Weak, gain 1 Flow. (Up: 9.) **Source:** Seatbelts track. **Design:** the deck's first proper debuff attack.
- **Goodnight Julia** *(`GoodnightJulia_Spike`)* — 1⚡ Skill — Lose 3 HP, gain 3 Flow, draw 1. (Up: 4 Flow.) **Source:** Julia's death in the finale. **Design:** aggressive Flow burst that *feeds* the self-damage engine (Space Lion).
- **Call Me Call Me** *(`CallMeCallMe_Spike`)* — 1⚡ Skill — Add a copy of a random card from your discard to your hand, gain 1 Flow. (Up: 0⚡.) **Source:** Seatbelts track (longing/recall). **Design:** recursion — get a key card back (another Bang. setup).

### Rares (showpieces)

- **The Egg and You** *(`TheEggAndYou_Spike` / `EggAndYouPower`)* — 2⚡ Power — At end of turn, gain Block equal to your Flow. (Up: 1⚡.) **Source:** ref. **Design:** turns the whole Flow engine into defense (Metallicize-style; distinct from Tank's Flow-gen and Jupiter Jazz's Strength).
- **Road to the West** *(`RoadToTheWest_Spike`)* — 2⚡ Attack — Deal 8 + 2 per Flow, then lose all Flow. (Up: 3 per Flow.) **Source:** the *Knockin' on Heaven's Door* climax track. **Design:** the *safe* Flow cash-out — a non-suicidal alternative to Bang.
- **Ave Maria** *(`AveMaria_Spike`)* — 2⚡ Attack · Exhaust — Deal 18; if you have 5+ Flow, deal 30 instead. (Up: 24 / 36.) **Source:** the assassin's hymn — art nods to a certain barcoded hitman (Agent 47). **Design:** the precision nuke; fills Pierrot le Fou's glass-cannon slot, gated by Flow instead of HP.
- **Blue** *(`Blue_Spike` / `BluePower`)* — 3⚡ Power — Whenever you play a card, gain 1 Flow. (Up: 2⚡.) **Source:** the show's true finale theme. **Design:** the capstone engine — every card flows; build-around for Bang./Road to the West.

---

## Quick appendix — Relics & Powers (for context)

**Relics** (`src/main/java/relics/`, text in `Spike_relics-*.json`):
- **Marlboro** (Starter) — start each combat with 2 Flow. *He smokes; lighting up = getting in the zone.*
- **Jericho 941** (Common) — first Attack each turn grants 1 Flow. *His pistol.*
- **Ein** (Uncommon) — Scry 3 at combat start. *The data dog.*
- **Big Shot** (Shop) — +25 Gold per combat win. *The bounty-hunter TV show.*
- **See You Space Cowboy** (Rare) — gain 1 Buffer each combat (negates the next HP loss). *Insurance for Bang.'s death cost.*
- **Swordfish II** (Boss) — start each combat with +1 Energy and 2 Flow. *His ship.*

**Custom powers** (`src/main/java/powers/`): `FlowPower` (势), `CarryThatWeightPower` (Bang.'s cost), `JupiterJazzPower`, `TankPower`, `SpaceLionPower`, `EggAndYouPower` (Flow→Block at end of turn), `BluePower` (Flow per card played). *(EggAndYou/Blue power icons are placeholders — copied from Tank/Flow — pending dedicated art.)*

---

## Art Sources

**Art rule:** episode screenshots / OST/OST2 cover frames are used directly (resize + crop only). ComfyUI generation is used only when no suitable source frame exists. No img2img unless explicitly requested.

| Card | Source |
|------|--------|
| Straight Lead (Strike) | Generated: Spike's JKD one-inch punch |
| Shoot | Episode frame: Spike firing upward on a train |
| Dodge (Defend) | Episode frame: Spike ducking under a fist (rain fight, JKD) |
| Jeet Kune Do | Generated: JKD stance |
| Don't Bother None | Episode frame: Spike reclining / smoking |
| Spokey Dokey | Generated: Spike with harmonica, hands visible |
| Rush (CosmicDare) | Generated: Spike charging with speed lines |
| Memory | GIF frame: red rose on wet cobblestones (ED sequence) |
| Farewell Blues | Generated |
| 24 Hours Open | Episode frame: convenience store confrontation |
| Fantaisie Sign | OST 2 "No Disc" cover — Faye's face (right-crop, text removed) |
| Asteroid Blues | Episode frame: Spike in sombrero (Session 1, Tijuana) |
| My Funny Valentine | Generated |
| Bohemian Rhapsody | Generated |
| Words That We Couldn't Say | Generated |
| Goodnight Julia | Generated: Julia in rain, long blonde hair |
| Call Me Call Me | Episode frame: Ed's "Bye Bye" floor scrawl |
| Metal Queen | Generated |
| Jupiter Jazz | Generated |
| Waltz for Venus | Generated |
| Honky Tonk Women | Generated |
| Sympathy for the Devil | Generated |
| Cowboy Funk | Generated: western duel standoff, Monument Valley |
| Speak Like a Child | Generated |
| Mushroom Hunting | Generated |
| Green Bird | Generated |
| Knock a Lil Harder | Generated |
| Bang. | Generated |
| Ballad of Fallen Angels | Generated |
| Tank! | Generated |
| The Real Folk Blues | GIF frame: Spike eye close-up (finale) |
| Space Lion | Generated |
| Jamming with Edward | Generated |
| Ask DNA | Generated: teal DNA double helix on black |
| The Egg and You | Generated |
| Road to the West | Generated |
| Ave Maria | Generated: hitman in cathedral, stained glass |
| Blue | Generated |
