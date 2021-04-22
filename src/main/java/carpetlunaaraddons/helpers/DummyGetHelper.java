package carpetlunaaraddons.helpers;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;

public class DummyGetHelper
{
    public static Comparable<?> dummyGetMethod(BlockState blockState, Property<Direction.Axis> property) {
        if (CarpetLunaarSettings.teleportToPoiWithoutPortals && !blockState.contains(Properties.HORIZONTAL_AXIS))
            return Direction.Axis.Z;
        return blockState.get(property);
    }
}
