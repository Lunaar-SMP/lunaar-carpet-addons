package carpetlunaaraddons;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */
public class CarpetLunaarSettings {
    public static final String LUNAAR = "Lunaar";

    @Rule(
            desc="Re-adds teleporting to portal POIs without portal blocks",
            extra = {"Update suppressor go brrr"},
            category = {SURVIVAL, EXPERIMENTAL, LUNAAR})
    public static boolean teleportToPoiWithoutPortals = false;

}
