package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.helpers.ChunkManagerHelper;
import carpetlunaaraddons.helpers.SpawnHelperInfoDuckInterface;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.world.SpawnDensityCapper;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerChunkManager.class)
public abstract class ServerChunkManagerMixin
{
    @Redirect(
            method = "tickChunks",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/SpawnHelper;setupSpawn(ILjava/lang/Iterable;Lnet/minecraft/world/SpawnHelper$ChunkSource;Lnet/minecraft/world/SpawnDensityCapper;)Lnet/minecraft/world/SpawnHelper$Info;"
            )
    )
    public SpawnHelper.Info infoSetter(int spawningChunkCount, Iterable<Entity> entities,
                                       SpawnHelper.ChunkSource chunkSource, SpawnDensityCapper spawnDensityCapper) {
        SpawnHelper.Info info = SpawnHelper.setupSpawn(spawningChunkCount, entities, chunkSource, spawnDensityCapper);
        if (CarpetLunaarSettings.phantomsCapped)
            ChunkManagerHelper.putInfoAndSpawningChunkCount(((SpawnHelperInfoDuckInterface) info).copy(), spawningChunkCount);
        return info;
    }
}
