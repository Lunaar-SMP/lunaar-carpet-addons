package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.helpers.ChunkManagerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerChunkManager.class)
public abstract class ServerChunkManagerMixin
{
	@Redirect(
			method = "tickChunks",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/SpawnHelper;setupSpawn(ILjava/lang/Iterable;Lnet/minecraft/world/SpawnHelper$ChunkSource;)Lnet/minecraft/world/SpawnHelper$Info;"
			)
	)
	public SpawnHelper.Info infoSetter(int spawningChunkCount, Iterable<Entity> entities,
	                                   SpawnHelper.ChunkSource chunkSource) {
		SpawnHelper.Info info = SpawnHelper.setupSpawn(spawningChunkCount, entities, chunkSource);
		if (CarpetLunaarSettings.phantomsCapped) {
			ChunkManagerHelper.putInfo(info);
			ChunkManagerHelper.putSpawningChunkCount(spawningChunkCount);
		}
		return info;
	}

	@Inject(
			method = "shouldTickEntity",
			at = @At(value = "HEAD"),
			cancellable = true
	)
	private void onShouldTickEntity(Entity entity, CallbackInfoReturnable<Boolean> cir){
		if(CarpetLunaarSettings.forceLoadEnderPearls && entity instanceof EnderPearlEntity)
			cir.setReturnValue(true);
	}
}
