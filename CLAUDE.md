# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

"The Space Cowboy" is a **Slay the Spire mod** (mod id `Spike_Spiegel`) that adds Spike Spiegel from Cowboy Bebop as a new playable character. It is written in **Java 8**, built with **Maven**, and runs on the **BaseMod + ModTheSpire** modding framework. There are no tests, no linter, and no CI — it is a small content mod (~7 Java files) that hooks into the base game.

## Build & run

```bash
mvn clean package
```

This produces `target/Spike_Spiegel.jar` (the `finalName` is set in `pom.xml`).

**Critical:** the three dependencies in `pom.xml` are `system`-scoped and resolved from Maven properties, NOT downloaded. The build fails unless these point at the real JARs (from a Slay the Spire install with ModTheSpire/BaseMod):

- `path-mts` → `ModTheSpire.jar`
- `path-bm`  → `BaseMod.jar`
- `path-md`  → `desktop-1.0.jar` (the base game)

Set them in `~/.m2/settings.xml` (a `<profile>` with these `<properties>`) or pass on the command line, e.g. `mvn clean package -Dpath-mts=... -Dpath-bm=... -Dpath-md=...`.

The mod is not run standalone. To test it in-game: build the jar, drop it in the Slay the Spire `mods/` folder, and launch through the ModTheSpire loader. Runtime dependencies are `basemod` and `stslib` (declared in `src/main/resources/ModTheSpire.json`), which must also be installed.

## Architecture

The mod is **event-driven**: `SpikeMod` registers itself with BaseMod and BaseMod calls back into it at well-defined points in the game lifecycle.

- **Entry point — `demoMod/SpikeMod.java`.** Annotated `@SpireInitializer`; ModTheSpire calls its static `initialize()` (do not remove it). The constructor subscribes to BaseMod and registers the custom card color via `BaseMod.addColor(...)`. The class implements many BaseMod `*Subscriber` interfaces — most callbacks are empty stubs left as extension points. The ones that matter:
  - `receiveEditCharacters()` → `BaseMod.addCharacter(new Spike(...), ...)`
  - `receiveEditCards()` → calls `loadCardsToAdd()` then `BaseMod.addCard(...)` for each
  - `receiveEditRelics()` → `BaseMod.addRelicToCustomPool(new Marlboro(), Spike_COLOR)`
  - `receiveEditStrings()` → loads localization JSON for the current language (see below)

- **Type registration — `pathes/`** (note: deliberately spelled "pathes"; it is referenced in imports, do not rename). Holds `@SpireEnum` fields that ModTheSpire injects into the game's enums at load time: `AbstractCardEnum.Spike_COLOR` (a `CardColor`) and `ClassEnum.Spike_CLASS` (a `PlayerClass`). Custom cards/characters reference these enums instead of hardcoded values.

- **Character — `characters/Spike.java`** extends `CustomPlayer`. Defines starting HP/gold/energy, `getStartingDeck()` (7× `Bang_Spike`), `getStartingRelics()` (`Marlboro`), the char-select loadout, sprite/energy-orb asset paths, and colors. Localized strings (title/flavor) are branched inline on `Settings.language`.

- **Card — `cards/Bang_Spike.java`** extends `CustomCard`. Note the pattern: `NAME`/`DESCRIPTION` are pulled from `CardCrawlGame.languagePack.getCardStrings(ID)` in static initializers, so the card's `ID` must match the localization JSON key. `use()` queues game actions (e.g. `DamageAction`).

- **Relic — `relics/Marlboro.java`** extends `CustomRelic` (STARTER tier). Counter-based mechanic driven by lifecycle hooks (`atBattleStart`, `onUseCard`, `onVictory`).

### Localization & assets

- **Localization** lives in `src/main/resources/localization/Spike_*-{en,zh}.json`, keyed by content ID. `SpikeMod.receiveEditStrings()` selects the `-zh` files when `Settings.language == ZHS`, otherwise `-en`, and loads them via `BaseMod.loadCustomStrings(...)`. (Powers/potions/events JSON loading is stubbed out in comments for future use.)
- **Art** lives under `src/main/resources/img/` (`512/` and `1024/` card frames, `char_Spike/`, `cards_Spike/`, `relics_Spike/`, `UI_Spike/`, `charSelect/`). Assets are referenced by relative path string constants and loaded with `Gdx.files.internal(...)` / `ImageMaster.loadImage(...)`.

## Conventions & recipes

**The ID, class name, and JSON key for any content must all match** (e.g. card class `Bang_Spike`, `ID = "Bang_Spike"`, JSON key `"Bang_Spike"`). Cards are suffixed `_Spike`.

To add a **card**:
1. Create a class in `cards/` extending `CustomCard` with a unique `ID`.
2. Register it in `SpikeMod.loadCardsToAdd()`.
3. Add a `NAME`/`DESCRIPTION` entry under that ID in **both** `Spike_cards-en.json` and `Spike_cards-zh.json`.
4. Add art to `img/cards_Spike/`; add to `Spike.getStartingDeck()` if it should be in the starting deck.

To add a **relic**: class in `relics/` extending `CustomRelic`, register in `SpikeMod.receiveEditRelics()`, add entries to both `Spike_relics-{en,zh}.json`, add art (and an `outline/` variant) under `img/relics_Spike/`.

Bump `version` in `src/main/resources/ModTheSpire.json` when shipping changes.

## Notes / gotchas

- Several subscriber methods and the `recyclecards` static list / `receivePostEnergyRecharge` logic in `SpikeMod` are partial works-in-progress; don't assume they're load-bearing.
- `loadCardsToAdd()` registers multiple copies of `Bang_Spike` into the card pool — registration only needs one; this redundancy is harmless. The starting deck count is controlled separately in `Spike.getStartingDeck()`.
