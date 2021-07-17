package carpetlunaaraddons;

import carpet.settings.Rule;
import carpet.settings.Validator;

import static carpet.settings.RuleCategory.*;

public class CarpetLunaarSettings
{
    public static final String LUNAAR = "lunaar";
    public static final String BACKPORT = "backport";
    public static final String COMBAT = "combat";


    @Rule(
            desc = "Re-adds teleporting to portal POIs without portal blocks",
            extra = {"Update suppressor go brrr"},
            category = {EXPERIMENTAL, LUNAAR}
    )
    public static boolean teleportToPoiWithoutPortals = false;

    @Rule(
            desc = "Prevents ender pearls from getting deleted when they move into unloaded chunks",
            extra = {"This also means that ender pearls load chunks",
                    "Merged with keepEnderPearlsTicked from carpet-addons by whoImT"},
            category = {EXPERIMENTAL, LUNAAR}
    )
    public static boolean forceLoadEnderPearls = false;

    @Rule(
            desc = "Allows tridents thrown by drowned use the enchantments on the trident held by said drowned",
            category = {FEATURE, LUNAAR}
    )
    public static boolean drownedUseEnchantedTridents = false;

    @Rule(
            desc = "Backports returning Loyalty tridents to their owner when thrown into the void",
            extra = {"From Combat Test 4"},
            category = {SURVIVAL, BACKPORT, COMBAT, LUNAAR}
    )
    public static boolean voidedLoyaltyTridentsReturn = false;

    @Rule(
            desc = "Backports applying the Impaling enchantment on any mob that is in water or rain",
            extra = {"From Combat Test 4"},
            category = {SURVIVAL, BACKPORT, COMBAT, LUNAAR}
    )
    public static boolean impalingAffectsMobsInWater = false;

    @Rule(
            desc = "Allows players in Creative mode to kill entities in one hit",
            extra = {"If the player is sneaking, other entities around the target get killed too"},
            category = {CREATIVE, LUNAAR}
    )
    public static boolean creativeOneHitKill = false;

    @Rule(
            desc = "Prevents phantoms from spawning if mobcap is full",
            category = {FEATURE, EXPERIMENTAL, LUNAAR}
    )
    public static boolean phantomsCapped = false;

    @Rule(
            desc = "Mobs in death animation do not count towards the mob cap",
            extra = {"Might be a bit janky"},
            category = {CREATIVE, FEATURE, EXPERIMENTAL, LUNAAR}
    )
    public static boolean capIgnoresDeathAnimation = false;

    @Rule(
            desc = "Bots don't appear on scoreboards and do not count in the total if they're not in a team",
            extra = {"Normal players need to be in a team!"},
            category = {FEATURE, LUNAAR}
    )
    public static boolean filterBotsInScores = false;

    @Rule(
            desc = "The scoreboard total appears on the scoreboard",
            category = {FEATURE, LUNAAR}
    )
    public static boolean totalScore = false;

    @Rule(
            desc = "Dispensers can shoot tridents",
            extra = {"Backported from Combat Test 4", "I know bedrock has this too, but we don't talk about bedrock"},
            category = {BACKPORT, DISPENSER, COMBAT, LUNAAR}
    )
    public static boolean dispensersShootTridents = false;

    @Rule(
            desc = "Reimplements mob overspawning",
            extra = {"From 1.15.2"},
            category = {CREATIVE, LUNAAR}
    )
    public static boolean doOverspawning = false;

    @Rule(
            desc = "Allows the use of spigot formatting inside of anvils",
            extra = {"Enable and use the /colors command to see formatting codes you can use", "From FX's WeirdAddons"},
            category = {FEATURE, EXPERIMENTAL, LUNAAR}
    )
    public static boolean anvilColorFormatting = false;

    @Rule(
            desc = "Enables the /colors command",
            extra = {"Use this command to see formatting codes you can use", "From FX's WeirdAddons"},
            validate = Validator._COMMAND_LEVEL_VALIDATOR.class,
            options = {"ops", "true", "false"},
            category = {COMMAND, LUNAAR}
    )
    public static String commandColors = "ops";

    @Rule(
            desc = "Fixes Chunk Regen due to NbtString writeUTF() not respecting readUTF() Limits",
            extra = {"From FX's Carpet-Fixes", "Fixes ChunkRegen & [MC-134892](https://bugs.mojang.com/browse/MC-134892)"},
            category = {BUGFIX, LUNAAR}
    )
    public static boolean chunkRegenFix = false;
}
