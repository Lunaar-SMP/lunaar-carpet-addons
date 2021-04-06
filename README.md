
Carpet Lunaar Addons
====================

[![Build Status](https://github.com/Lunaar-SMP/lunaar-carpet-addons/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/Lunaar-SMP/lunaar-carpet-addons/actions/workflows/gradle.yml)

To get a build of the latest commit, go to [the build workflow](https://github.com/Lunaar-SMP/lunaar-carpet-addons/actions/workflows/gradle.yml) and click on the latest run to download the jar (under "Artifacts")

## Rules
### teleportToPoiWithoutPortals
Re-adds teleporting to portal POIs without portal blocks
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `EXPERIMENTAL`, `LUNAAR`

### forceLoadEnderPearls
Prevents Ender Pearls from being deleted when moving into unloaded chunks
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `EXPERIMENTAL`, `LUNAAR`

### drownedUseEnchantedTridents
Allows tridents thrown by drowned use the enchantments on the trident held by said drowned
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `LUNAAR`

### voidedLoyaltyTridentsReturn
Backports returning Loyalty tridents to their owner when thrown into the void, from Combat Test 4
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `BACKPORT`, `COMBAT`, `LUNAAR`

### impalingAffectsMobsInWater
Backports applying the Impaling enchantment on any mob that is in water or rain, from Combat Test 4
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `BACKPORT`, `COMBAT`, `LUNAAR`

### shulkerBoxItemsDropContents
Backports dropping the contents of a Shulker Box item when its item entity is destroyed, from the 1.17 snapshots
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `BACKPORT`, `1.17`, `LUNAAR`

### creativeOneHitKill
Allows players in Creative mode to kill entities in one hit
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `CREATIVE`, `LUNAAR`

### phantomsCapped
Prevents phantoms from spawning if mobcap is full
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `SURVIVAL`, `EXPERIMENTAL`, `LUNAAR`

### capIgnoresDeathAnimation
Mobs in death animation do not count towards the mob cap
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `CREATIVE`, `EXPERIMENTAL`, `LUNAAR`