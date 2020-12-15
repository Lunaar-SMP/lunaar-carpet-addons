package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.world.PortalForcer;
import net.minecraft.world.poi.PointOfInterest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Mixin(PortalForcer.class)
public class PortalForcerMixin
{
    @Redirect(
            method = "method_30483",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/stream/Stream;filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;"))
    private Stream<PointOfInterest> dummyFilter(Stream<PointOfInterest> pointOfInterestStream,
                                                Predicate<PointOfInterest> pointOfInterestPredicate) {
        return CarpetLunaarSettings.oldNetherPortalPoi ?
                pointOfInterestStream :
                pointOfInterestStream.filter(pointOfInterestPredicate);
    }
}
