package carpetlunaaraddons.mixins;

import carpetlunaaraddons.helpers.DummyGetHelper;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AreaHelper.class)
public class AreaHelperMixin
{
	@Redirect(
			method = "method_30484",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;"
			)
	)
	private static Comparable<?> dummyGet(BlockState blockState, Property<Direction.Axis> property) {
		return DummyGetHelper.dummyGetMethod(blockState,property);
	}
}
