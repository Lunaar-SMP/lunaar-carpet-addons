package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(HostileEntity.class)
public class HostileEntityMixin
{
    @Inject(
            method = "isSpawnDark",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/ServerWorldAccess;toServerWorld()Lnet/minecraft/server/world/ServerWorld;"
            ),
            cancellable = true
    )
    private static void checkBlockLightLevel(ServerWorldAccess world, BlockPos pos, Random random,
                                             CallbackInfoReturnable<Boolean> cir) {
        if (CarpetLunaarSettings.maxHostileSpawnLightLevel < 7
                && world.getLightLevel(LightType.BLOCK, pos) > CarpetLunaarSettings.maxHostileSpawnLightLevel) {
            cir.setReturnValue(false);
        }
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
