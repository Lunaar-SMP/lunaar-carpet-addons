package carpetlunaaraddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.chocohead.mm.api.ClassTinkerers;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.command.ServerCommandSource;

import java.lang.reflect.Field;

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

    public static void onServerDoneClosing() {
        String name = FabricLoader.getInstance().getMappingResolver()
                .mapFieldName("intermediary", "net.minecraft.class_1299", "field_6094", "Lnet/minecraft/class_1311;");
        try {
            Field field = EntityType.class.getDeclaredField(name);
            field.setAccessible(true);
            if (field.get(EntityType.PHANTOM).equals(ClassTinkerers.getEnum(SpawnGroup.class, "PHANTOM"))) {
                field.set(EntityType.PHANTOM, SpawnGroup.MONSTER);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetLunaarSettings.class);

        CarpetLunaarObservers observers = new CarpetLunaarObservers();
        CarpetServer.settingsManager.addRuleObserver(observers::phantomsUniqueCapObserver);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        CarpetLunaarCommands.register(dispatcher);
    }
}
