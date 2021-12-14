package carpetlunaaraddons;

import carpet.settings.ParsedRule;
import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

public final class CarpetLunaarObservers
{
    private static final Logger LOGGER = LogManager.getLogger();

    public void phantomsUniqueCapObserver(ServerCommandSource source, ParsedRule<?> rule, String newValue) {
        if (rule.name.equals("phantomsUniqueCap")) {
            String name = FabricLoader.getInstance().getMappingResolver()
                    .mapFieldName("intermediary", "net.minecraft.class_1299", "field_6094", "Lnet/minecraft/class_1311;");
            SpawnGroup spawnGroup = Boolean.parseBoolean(newValue) ?
                    ClassTinkerers.getEnum(SpawnGroup.class, "PHANTOM") : SpawnGroup.MONSTER;

            try {
                Field field = EntityType.class.getDeclaredField(name);
                field.setAccessible(true);
                field.set(EntityType.PHANTOM, spawnGroup);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
