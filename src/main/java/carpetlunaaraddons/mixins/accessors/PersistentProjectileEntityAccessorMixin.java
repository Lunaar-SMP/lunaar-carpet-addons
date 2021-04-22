package carpetlunaaraddons.mixins.accessors;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PersistentProjectileEntity.class)
public interface PersistentProjectileEntityAccessorMixin
{
    @Invoker("setNoClip")
    void invokerSetNoClip(boolean noClip);
}
