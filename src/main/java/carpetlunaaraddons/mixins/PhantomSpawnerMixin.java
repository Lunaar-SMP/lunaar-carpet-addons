package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.helpers.ChunkManagerHelper;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.gen.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PhantomSpawner.class)
public class PhantomSpawnerMixin
{
    @Inject(
            method = "spawn",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/gen/PhantomSpawner;ticksUntilNextSpawn:I",
                    opcode = 0xB5, // PUTFIELD
                    ordinal = 1,
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    public void mobcapConditional(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals,
                                  CallbackInfoReturnable<Integer> cir) {
        if (CarpetLunaarSettings.phantomsCapped) {
            SpawnHelper.Info info = ChunkManagerHelper.getInfo();
            int i = SpawnGroup.MONSTER.getCapacity()
                    * ChunkManagerHelper.getSpawningChunkCount() / (int) Math.pow(17.0D, 2.0D);
            if (info.getGroupToCount().getInt(SpawnGroup.MONSTER) > i)
                cir.setReturnValue(0);
        }
    }
}
