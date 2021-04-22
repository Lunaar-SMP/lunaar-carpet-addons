package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.math.GravityField;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(SpawnHelper.class)
public class SpawnHelperMixin
{
    private static Entity entity;

    @Inject(
            method = "setupSpawn",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Iterator;next()Ljava/lang/Object;",
                    shift = At.Shift.BY,
                    by = 3
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void entityGetter(int spawningChunkCount, Iterable<Entity> entities,
                                     SpawnHelper.ChunkSource chunkSource, CallbackInfoReturnable<SpawnHelper.Info> cir,
                                     GravityField gravityField, Object2IntOpenHashMap<SpawnGroup> object2IntOpenHashMap,
                                     Iterator<Entity> var5, Entity entity) {
        if (CarpetLunaarSettings.capIgnoresDeathAnimation)
            SpawnHelperMixin.entity = entity;
    }

    @ModifyVariable(
            method = "setupSpawn",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/entity/EntityType;getSpawnGroup()Lnet/minecraft/entity/SpawnGroup;"
            )
    )
    private static SpawnGroup fakeSpawnGroup(SpawnGroup spawnGroup) {
        if (CarpetLunaarSettings.capIgnoresDeathAnimation && !entity.isAlive())
            return SpawnGroup.MISC;
        return spawnGroup;
    }
}
