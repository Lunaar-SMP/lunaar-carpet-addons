package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.helpers.GetAttackDamageHelper;
import carpetlunaaraddons.mixins.accessors.EntityAccessorMixin;
import carpetlunaaraddons.mixins.accessors.PersistentProjectileEntityAccessorMixin;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin
        extends PersistentProjectileEntity
        implements EntityAccessorMixin, PersistentProjectileEntityAccessorMixin
{
    protected TridentEntityMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Accessor("LOYALTY")
    abstract TrackedData<Byte> getLOYALTY();

    @Redirect(
            method = "onEntityHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F"
            )
    )
    public float alternateGetAttackDamage(ItemStack stack, EntityGroup group, EntityHitResult hitResult) {
        return CarpetLunaarSettings.impalingAffectsMobsInWater ?
                GetAttackDamageHelper.getAttackDamage(stack, (LivingEntity) hitResult.getEntity()) :
                EnchantmentHelper.getAttackDamage(stack, ((LivingEntity) hitResult.getEntity()).getGroup());
    }

    @Override
    protected void destroy() {
        int i = this.accessorGetDataTracker().get(this.getLOYALTY());
        if (CarpetLunaarSettings.voidedLoyaltyTridentsReturn && (i > 0))
            this.invokerSetNoClip(true);
        else
            super.destroy();
    }
}
