package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
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
		return CarpetLunaarSettings.forceLoadEnderPearls ?
				(entity.teleportRequested() || (entity instanceof EnderPearlEntity)) :
				entity.teleportRequested();
	}
}
