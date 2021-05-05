
Carpet Lunaar Addons
====================

[![Build Status](https://github.com/Lunaar-SMP/lunaar-carpet-addons/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/Lunaar-SMP/lunaar-carpet-addons/actions/workflows/gradle.yml)

To get a build of the latest commit, go to [the build workflow](https://github.com/Lunaar-SMP/lunaar-carpet-addons/actions/workflows/gradle.yml)
and click on the latest run (make sure to check what branch it ran on) to download the jar (under "Artifacts")

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