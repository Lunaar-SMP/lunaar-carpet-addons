package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HostileEntity.class)
public class HostileEntityMixin
{
    @ModifyArg(
            method = "isSpawnDark",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Random;nextInt(I)I",
                    ordinal = 1
            ),
            index = 0
    )
    private static int maximumLightLevel(int level) {
        return CarpetLunaarSettings.maxHostileSpawnLightLevel + 1;
    }
}
