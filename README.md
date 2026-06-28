# The Space Cowboy

A **Slay the Spire** mod adding **Spike Spiegel** from *Cowboy Bebop* as a fully playable character.

Mod ID: `Spike_Spiegel` · Version: 0.0.3 · Language: EN + Simplified Chinese

---

## Installation

**Requirements:** Slay the Spire, [ModTheSpire](https://github.com/kiooeht/ModTheSpire), [BaseMod](https://github.com/daviscook477/BaseMod), [STSLib](https://github.com/kiooeht/StSLib)

1. Build the jar (see below) or grab `Spike_Spiegel.jar` from the repo root.
2. Drop it into your Slay the Spire `mods/` folder.
3. Launch via the ModTheSpire loader with BaseMod and STSLib enabled.

### Building from source

Requires Java 8 and Maven. The three dependencies are `system`-scoped — they resolve from local paths, not Maven Central. Set them in `~/.m2/settings.xml`:

```xml
<profile>
  <id>sts</id>
  <properties>
    <path-mts>/path/to/ModTheSpire.jar</path-mts>
    <path-bm>/path/to/BaseMod.jar</path-bm>
    <path-md>/path/to/desktop-1.0.jar</path-md>
  </properties>
</profile>
```

Then:

```bash
mvn clean package
# → target/Spike_Spiegel.jar
```

---

## Character Overview

Spike Spiegel is a bounty hunter, ex-Red Dragon syndicate enforcer, and practitioner of Jeet Kune Do — Bruce Lee's fluid, adaptive martial art. His playstyle reflects that: **build momentum, then cash it in for a decisive, all-or-nothing strike.**

- **75 HP · 3 energy**
- Starting deck: 4× Straight Lead, 1× Shoot, 4× Dodge, 1× Jeet Kune Do
- Starting relic: **Marlboro** (begin each combat with 2 Flow)

---

## Core Mechanic — Flow (势)

**Flow** is Spike's combat resource — a momentum counter that starts at 0 each fight and has no hard cap.

> *"Be like water."*

- **Builders** (most commons): grant Flow when played.
- **Threshold payoffs** (uncommons): do something better once you reach 3, 5, or 6 Flow.
- **Consumers** (rares): spend all Flow for massive burst — chiefly `Bang.` and `Road to the West`.
- **Passive scalers**: powers like `Tank!`, `Jupiter Jazz`, and `Blue` that turn sustained Flow into Strength or free Block every turn.

The loop: **commons build Flow and keep you alive → uncommons leverage it → rares cash it in.**

---

## Cards

Full design notes and numbers: [docs/CARD_DESIGN.md](docs/CARD_DESIGN.md)

### Basics (starting deck)
| Card | Cost | Type | Effect |
|------|------|------|--------|
| Straight Lead | 1 | Attack | Deal 6. |
| Shoot | 1 | Attack | Deal 6. Apply 1 Vulnerable. |
| Dodge | 1 | Skill | Gain 5 Block. |
| Jeet Kune Do | 1 | Attack | Deal 5. Gain 1 Flow. |

### Commons
| Card | Cost | Type | Effect |
|------|------|------|--------|
| Don't Bother None | 1 | Attack | Deal 7. Gain 1 Flow. |
| Bad Dog No Biscuits | 1 | Attack | Deal 3 twice. |
| Spokey Dokey | 0 | Attack | Deal 4. |
| Felt Tip Pen | 0 | Skill | Draw 1. Gain 1 Flow. |
| Rain | 1 | Skill | Gain 5 Block; 8 if you have 3+ Flow. |
| Rush | 1 | Attack | Deal 8. Gain 1 Flow. |
| Memory | 1 | Skill | Gain 5 Block. Gain 1 Flow. |
| Farewell Blues | 1 | Attack | Deal 6. Draw 1. |
| 24 Hours Open | 1 | Skill | Gain 4 Block. Draw 1. |
| Fantaisie Sign | 0 | Skill | Gain 2 Flow. |

### Uncommons
| Card | Cost | Type | Effect |
|------|------|------|--------|
| Heavy Metal Queen | 2 | Attack | Deal 8 +2 per Flow (doesn't consume). |
| Jupiter Jazz | 1 | Power | End of turn: gain 1 Str (2 if 6+ Flow). |
| Waltz for Venus | 1 | Skill | Gain 8 Block. Draw 1. |
| Honky Tonk Women | 1 | Skill | Gain 2 Flow. Draw 1. |
| Sympathy for the Devil | 1 | Attack | Deal 9. Heal 2 HP (5 if 5+ Flow). |
| Cowboy Funk | 1 | Attack | Deal 5 twice; three times if 3+ Flow. |
| Speak Like a Child | 1 | Skill | Draw 2. Gain 1 Flow. |
| Mushroom Hunting | 0 | Skill | Gain 3 Flow. Draw 1. Exhaust. |
| Green Bird | 2 | Skill | Gain 12 Block. Gain 1 Dexterity. |
| Gotta Knock a Little Harder | 1 | Attack | Deal 8; doubled at or below half HP. |
| Asteroid Blues | 1 | Attack | Deal 9; gain 2 Flow if first card this turn. |
| My Funny Valentine | 1 | Skill | Retain. Gain Block equal to 2× your Flow. |
| Bohemian Rhapsody | 1 | Skill | Scry 3. Gain 2 Flow. |
| Words That We Couldn't Say | 1 | Attack | Deal 6. Apply 2 Weak. Gain 1 Flow. |
| Goodnight Julia | 1 | Skill | Lose 3 HP. Gain 3 Flow. Draw 1. |
| Call Me Call Me | 1 | Skill | Add a random discard copy to hand. Gain 1 Flow. |

### Rares
| Card | Cost | Type | Effect |
|------|------|------|--------|
| Bang. | 2 | Attack | Deal 9 +3/Flow. Lose all Flow. Lose half HP next turn. Exhaust. |
| Ballad of Fallen Angels | 2 | Attack | Deal 8 to ALL. Gain 2 Flow. |
| Tank! | 2 | Power | Start of turn: gain 2 Flow. |
| The Real Folk Blues | 3 | Attack | Deal 7 to ALL. Draw 2. Gain 2 Flow. |
| Space Lion | 1 | Power | Whenever you lose HP, gain 2 Strength. |
| Jamming with Edward | 1 | Skill | Gain Flow = hand size. Draw 2. Exhaust. |
| Ask DNA | 1 | Skill | Next Attack plays twice. Exhaust. |
| The Egg and You | 2 | Power | End of turn: gain Block = your Flow. |
| Road to the West | 2 | Attack | Deal 8 +2/Flow. Lose all Flow. |
| Ave Maria | 2 | Attack | Deal 18; 30 if 5+ Flow. Exhaust. |
| Blue | 3 | Power | Whenever you play a card, gain 1 Flow. |

---

## Relics

| Relic | Tier | Effect |
|-------|------|--------|
| Marlboro | Starter | At combat start, gain 2 Flow. |
| Jericho 941 | Common | Your first Attack each turn grants 1 Flow. |
| Ein | Uncommon | At combat start, Scry 3. |
| Big Shot | Shop | Win a battle: gain 25 Gold. |
| See You Space Cowboy | Rare | At combat start, gain 1 Buffer. |
| Swordfish II | Boss | At combat start, gain 1 Energy and 2 Flow. |

---

## Disclaimer

This is an **unofficial fan mod** made purely for personal enjoyment. It is not affiliated with, endorsed by, or connected to Sunrise Inc., Bandai Namco, or Mega Crit Games in any way.

*Cowboy Bebop* — its characters, episode titles, music track names, and all associated intellectual property — are owned by **Sunrise Inc.** Card and episode names are used as tribute to the source material under fair use principles for non-commercial fan works. No copyright infringement is intended.

*Slay the Spire* is owned by **Mega Crit Games**. This mod requires a legitimate copy of the game and its official modding tools (ModTheSpire, BaseMod).

**This mod must not be sold, monetized, or bundled in any commercial product.**

---

## License

The **mod code and original assets** (generated card art) are released under a custom non-commercial fan mod license. You may share and adapt for non-commercial purposes with attribution. **Commercial use is strictly prohibited.** See [LICENSE](LICENSE) for the full terms.

Third-party content (episode screenshots, OST cover frames, and all *Cowboy Bebop* IP) remains the property of its respective rights holders and is **not** covered by this license.

---

## Credits

- **Mod author:** Spike (Res)
- **Character art:** usageni
- **Card & relic art:** ComfyUI (anima-base-v1.0) + episode screenshots from *Cowboy Bebop* (Sunrise)
- **Original game:** *Cowboy Bebop* © Sunrise · *Slay the Spire* © Mega Crit Games
- **Framework:** BaseMod, ModTheSpire, STSLib
