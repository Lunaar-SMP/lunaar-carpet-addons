package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

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

    @ModifyConstant(
            method = "getPathfindingFavor",
            constant = @Constant(floatValue = 0.5F)
    )
    public float minimumLightLevelFloat(float original) {
        return CarpetLunaarSettings.maxHostileSpawnLightLevel / 15.0F;
    }
}
