package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.utils.DummyGetUtil;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.PortalForcer;
import net.minecraft.world.PortalUtil;
import net.minecraft.world.poi.PointOfInterest;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Mixin(PortalForcer.class)
public class PortalForcerMixin
{
    @Shadow
    @Final
    private ServerWorld world;

    @Redirect(
            method = "method_30483",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/stream/Stream;filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;"
            )
    )
    private Stream<PointOfInterest> dummyFilter(Stream<PointOfInterest> pointOfInterestStream,
                                                Predicate<PointOfInterest> pointOfInterestPredicate) {
        return CarpetLunaarSettings.teleportToPoiWithoutPortals ?
                pointOfInterestStream :
                pointOfInterestStream.filter(pointOfInterestPredicate);
    }

    @Redirect(
            method = "method_30479", // This is a lambda
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;"
            )
    )
    private Comparable<?> dummyGet(BlockState blockState, Property<Direction.Axis> property) {
        return DummyGetUtil.dummyGetMethod(blockState, property);
    }

    @Redirect(
            method = "method_30479", // This is a lambda
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/world/PortalUtil.getLargestRectangle(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction$Axis;ILnet/minecraft/util/math/Direction$Axis;ILjava/util/function/Predicate;)Lnet/minecraft/world/PortalUtil$Rectangle;"
            )
    )
    private PortalUtil.Rectangle dummyReturn(BlockPos blockPos, Direction.Axis axis1, int i,
                                             Direction.Axis axis2, int j, Predicate<BlockPos> predicate) {
        if (CarpetLunaarSettings.teleportToPoiWithoutPortals
                && !this.world.getBlockState(blockPos).contains(Properties.HORIZONTAL_AXIS))
            // The width and height arguments are just random numbers I came up with at the spot,
            // please feel free to make a pull request if you think there are better values for these - Copetan
            return new PortalUtil.Rectangle(blockPos, 1, 1);
        return PortalUtil.getLargestRectangle(blockPos, axis1, i, axis2, j, predicate);
    }
}
