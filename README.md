
Carpet Lunaar Addons
====================

[![Build Status](https://github.com/Lunaar-SMP/lunaar-carpet-addons/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/Lunaar-SMP/lunaar-carpet-addons/actions/workflows/gradle.yml)

To get a build of the latest commit, go to [the build workflow](https://github.com/Lunaar-SMP/lunaar-carpet-addons/actions/workflows/gradle.yml)
and click on the latest run (make sure to check what branch it ran on) to download the jar (under "Artifacts")

## Disclaimer
If a carpet rule exists in this mod, it does not necessarily mean we use it on the Lunaar server! I mostly just add
features to lunaar-carpet if a member asks me about it or if I find it useful, not exclusively for use on the server.

## Rules
### teleportToPoiWithoutPortals
Re-adds teleporting to portal POIs without portal blocks
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `EXPERIMENTAL`, `LUNAAR`

### forceLoadEnderPearls
Prevents ender pearls from getting deleted when they move into unloaded chunks
<br/>This also means that ender pearls load chunks
<br/>Merged with `keepEnderPearlsTicked` from
[carpet-addons](https://github.com/whoImT/carpet-addons) by [whoImT](https://github.com/whoImT)
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `EXPERIMENTAL`, `LUNAAR`

### drownedUseEnchantedTridents
Allows tridents thrown by drowned use the enchantments on the trident held by said drowned
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `FEATURE`, `LUNAAR`

### voidedLoyaltyTridentsReturn
Backports returning Loyalty tridents to their owner when thrown into the void
<br/>From Combat Test 4
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `BACKPORT`, `COMBAT`, `LUNAAR`

### impalingAffectsMobsInWater
Backports applying the Impaling enchantment on any mob that is in water or rain
<br/>From Combat Test 4
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `BACKPORT`, `COMBAT`, `LUNAAR`

### shulkerBoxItemsDropContents
Backports dropping the contents of a Shulker Box item when its item entity is destroyed
<br/>From the 1.17 snapshots
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `BACKPORT`, `1.17`, `LUNAAR`

### creativeOneHitKill
Allows players in Creative mode to kill entities in one hit
<br/>If the player is sneaking, other entities around the target get killed too
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `CREATIVE`, `LUNAAR`

### phantomsCapped
Prevents phantoms from spawning if mobcap is full
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `FEATURE`, `EXPERIMENTAL`, `LUNAAR`

### capIgnoresDeathAnimation
Mobs in death animation do not count towards the mob cap
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `CREATIVE`, `FEATURE`, `EXPERIMENTAL`, `LUNAAR`

### filterBotsInScores
Bots don't appear on scoreboards and do not count in the total if they're not in a team
<br/>This means that normal players need to be in a team!
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `FEATURE`, `LUNAAR`

### totalScore
The scoreboard total appears on the scoreboard
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `FEATURE`, `LUNAAR`

### dispensersShootTridents
Dispensers can shoot tridents
<br/>Backported from Combat Test 4
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `BACKPORT`, `DISPENSER`, `COMBAT`, `LUNAAR`

### doOverspawning
Reimplements mob overspawning
<br />From 1.15.2
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `CREATIVE`, `LUNAAR`

## License
This entire project is licensed under [LGPL-3.0](LICENSE), with portions of the code (specifically code for the
`filterBotsInScores` and the `totalScore` carpet rules) based off of code made by [@JohanVonElectrum](https://github.com/JohanVonElectrum),
which is licensed under the [MIT license](LICENSE_MIT).