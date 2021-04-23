package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlEntityMixin
    implements EntityLike
{
    @Override
    public boolean isPlayer() {
        return CarpetLunaarSettings.forceLoadEnderPearls;
    }
}
