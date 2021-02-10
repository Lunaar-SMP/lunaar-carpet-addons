package carpetlunaaraddons;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */
public class CarpetLunaarSettings
{
    public static final String LUNAAR = "Lunaar";

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
}
