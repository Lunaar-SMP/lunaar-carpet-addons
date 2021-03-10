package carpetlunaaraddons;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */
public class CarpetLunaarSettings
{
    public static final String LUNAAR = "lunaar";
    public static final String BACKPORT = "backport";
    public static final String COMBAT = "combat";
    public static final String v1_17 = "1.17";

    @Rule(
            desc = "Re-adds teleporting to portal POIs without portal blocks",
            extra = {"Update suppressor go brrr"},
            category = {SURVIVAL, EXPERIMENTAL, LUNAAR}
    )
    public static boolean teleportToPoiWithoutPortals = false;

    @Rule(
            desc = "Prevents Ender Pearls from being deleted when traveling to unloaded chunks",
            category = {SURVIVAL, EXPERIMENTAL, LUNAAR}
    )
    public static boolean forceLoadEnderPearls = false;

    @Rule(
            desc = "Allows tridents thrown by drowned use the enchantments on the trident held by said drowned",
            category = {SURVIVAL, LUNAAR}
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
            desc = "Backports dropping the contents of a Shulker Box item when its item entity is destroyed",
            extra = {"From the 1.17 snapshots"},
            category = {SURVIVAL, BACKPORT, v1_17, LUNAAR}
    )
    public static boolean shulkerBoxItemsDropContents = false;

    @Rule(
            desc = "Allows players in Creative mode to kill entities in one hit",
            category = {CREATIVE, LUNAAR}
    )
    public static boolean creativeOneHitKill = false;

    @Rule(
            desc = "Prevents phantoms from spawning if mobcap is full",
            category = {SURVIVAL, EXPERIMENTAL, LUNAAR}
    )
    public static boolean phantomsCapped = false;
}
