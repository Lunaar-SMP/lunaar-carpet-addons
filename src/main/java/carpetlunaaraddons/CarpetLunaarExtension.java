package carpetlunaaraddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;

public class CarpetLunaarExtension implements CarpetExtension
{
    public static void earlyRiser() {
        String name = FabricLoader.getInstance().getMappingResolver()
                .mapClassName("intermediary", "net.minecraft.class_1311");
        ClassTinkerers.enumBuilder(name, String.class, int.class, boolean.class, boolean.class, int.class)
                .addEnum("PHANTOM", "phantom", 15, false, false, 128).build();
    }

    public static void initializeExtension() {
        CarpetServer.manageExtension(new CarpetLunaarExtension());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetLunaarSettings.class);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        CarpetLunaarCommands.register(dispatcher);
    }
}
