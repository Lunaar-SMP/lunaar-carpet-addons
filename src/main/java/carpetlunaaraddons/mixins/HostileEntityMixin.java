package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(HostileEntity.class)
public class HostileEntityMixin
{
    @Redirect(
            method = "isSpawnDark",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/ServerWorldAccess;getLightLevel(Lnet/minecraft/world/LightType;Lnet/minecraft/util/math/BlockPos;)I",
                    ordinal = 1
            )
    )
    private static int blockLightLevelRedirect(ServerWorldAccess world, LightType lightType, BlockPos blockPos) {
        return Math.max(world.getLightLevel(lightType, blockPos) - CarpetLunaarSettings.maxHostileSpawnLightLevel, 0);
    }

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
        return CarpetLunaarSettings.maxHostileSpawnLightLevel > 7 ?
                CarpetLunaarSettings.maxHostileSpawnLightLevel + 1 : level;
    }

    @ModifyConstant(
            method = "getPathfindingFavor",
            constant = @Constant(floatValue = 0.5F)
    )
    public float minimumLightLevelFloat(float original) {
        return CarpetLunaarSettings.maxHostileSpawnLightLevel > 7 ?
                CarpetLunaarSettings.maxHostileSpawnLightLevel / 15.0F : original;
    }
}
