package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerWorld.class)
public class ServerWorldMixin
{
	@Redirect(
			method = "checkEntityChunkPos",
			at = @At(
					value = "INVOKE",
					target = "net/minecraft/entity/Entity.teleportRequested()Z"
			)
	)
	private boolean conditionAdder(Entity entity) {
		return CarpetLunaarSettings.forceLoadProjectiles ?
				(entity.teleportRequested() || (entity instanceof ProjectileEntity)) :
				entity.teleportRequested();
	}
}
