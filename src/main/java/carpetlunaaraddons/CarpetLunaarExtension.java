package carpetlunaaraddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.ServerCommandSource;

public class CarpetLunaarExtension implements CarpetExtension, ModInitializer
{
    @Override
    public void onInitialize() {
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
