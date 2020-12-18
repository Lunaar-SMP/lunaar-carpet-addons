package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientWorld.class)
public class ClientWorldMixin
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
