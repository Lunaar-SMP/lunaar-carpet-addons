package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.server.world.ServerChunkManager;
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
        return CarpetLunaarSettings.forceLoadEnderPearls ?
                (entity.teleportRequested() || (entity instanceof EnderPearlEntity)) :
                entity.teleportRequested();
    }

    @Redirect(
            method = "tickEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerChunkManager;shouldTickEntity(Lnet/minecraft/entity/Entity;)Z"
            )
    )
    public boolean onShouldTickEntity(ServerChunkManager serverChunkManager, Entity entity) {
        if (CarpetLunaarSettings.forceLoadEnderPearls && (entity instanceof EnderPearlEntity))
            return true;
        return serverChunkManager.shouldTickEntity(entity);
    }
}
