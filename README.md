# AOP:TC4 Compact

[Русский](README.ru.md)

I maintain optional **JEI / EMI** recipe integration and **Jade / WTHIT** block tooltips for **AOP: Thaumcraft 4**, Minecraft **1.21.1 / NeoForge**. Thaumic Tinkerer device support activates when the addon is installed.

## Installation

Install Java 21, NeoForge 21.1.252 or newer within 21.1, AOP: Thaumcraft 4 1.0.0 and at least one supported viewer:

| Integration | Supported versions |
| --- | --- |
| JEI | 19.8.4.110 ≤ version < 20 |
| EMI | 1.1.13 ≤ version < 1.2 |
| Jade | 15.10 ≤ version < 16 |
| WTHIT | 12.5 ≤ version < 13 |

All mods must target Minecraft 1.21.1 / NeoForge. Install their dependencies too. If both recipe viewers are present, EMI is selected. Jade and WTHIT are independent alternatives.

Place `aoptc4-client-compact-0.8.0.jar` in the client's `mods` directory. Replace the older `thaumcraft-viewers` JAR; do not install both. The runtime ID remains `thaumcraft_viewers` to retain configuration compatibility.

Server installation is optional. Installing it there alongside Jade or WTHIT supplies authoritative block data. Without it, tooltips show the synchronized state already available to the client; unsynchronized inventories may be unavailable. JEI/EMI are never needed on a dedicated server. Thaumic Tinkerer is optional on both sides.

Recipes include research requirements and aspect/vis amounts. Configuration is in `thaumcraft_viewers-client.toml`. Unknown aspects remain masked. TC4 tooltips report essentia through public container and transport contracts; Tinkerer tooltips include tablet tools, transvector targets, repair state and magnet modes.

## Building

Install JDK 21 and supply the matching Thaumcraft API and Thaumic Tinkerer JARs as compile-time dependencies:

```powershell
./gradlew.ps1 build -PthaumcraftApiJar=/path/to/thaumcraft-api-1.0.0.jar -PtinkererJar=/path/to/ThaumicTinkerer.jar
```

Alternatively place those JARs in `libs/` with the names specified in `build.gradle`. Linux/macOS: `sh ./gradlew`. Dependencies are not bundled. Tinkerer is needed to compile its optional adapter, not to run the mod.

`build` executes recipe presentation and isolated JEI/EMI linkage checks and compiles native tooltip verification. Run `check -PviewerCompatibility=minimum`, `middle` or `latest` for the supported viewer profiles. Native GameTests require explicit runtime JARs and the corresponding optional integration flags. Checks do not imply that every client/server combination has been visually verified.

## Credits and terms

[Salossov](https://github.com/Salossov) — integration and maintenance, with AI-assisted implementation and verification. [Azanor](https://www.curseforge.com/minecraft/mc-mods/thaumcraft) — Thaumcraft. [Vazkii](https://github.com/Vazkii/ThaumicTinkerer), pixlepix, nekosune and original contributors — Thaumic Tinkerer. Integration APIs belong to the JEI, EMI, Jade and WTHIT projects. See [NOTICE.md](NOTICE.md) for attribution and license scope.

## CI configuration

Repository variables `THAUMCRAFT_REPOSITORY` and `THAUMCRAFT_REF` select the core source (defaults: `Salossov/aop-thaumcraft-4`, `main`). Build against matching core and addon revisions. For private dependencies, the checkout workflow needs a token with read access.
For this repository, set `TINKERER_REPOSITORY` to the published `owner/repository`; `TINKERER_REF` defaults to `main`.
