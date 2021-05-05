package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.helpers.TridentEntityDuckInterface;
import carpetlunaaraddons.mixins.accessors.EntityAccessorMixin;
import carpetlunaaraddons.mixins.accessors.PersistentProjectileEntityAccessorMixin;
import carpetlunaaraddons.utils.GetAttackDamageUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin
        extends PersistentProjectileEntity
        implements EntityAccessorMixin, PersistentProjectileEntityAccessorMixin, TridentEntityDuckInterface
{
    @Shadow
    @Final
    private static TrackedData<Boolean> ENCHANTED;
    @Shadow
    private ItemStack tridentStack;

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
                GetAttackDamageUtil.getAttackDamage(stack, (LivingEntity) hitResult.getEntity()) :
                EnchantmentHelper.getAttackDamage(stack, ((LivingEntity) hitResult.getEntity()).getGroup());
    }

    @Override
    protected void tickInVoid() {
        int i = this.accessorGetDataTracker().get(this.getLOYALTY());
        if (CarpetLunaarSettings.voidedLoyaltyTridentsReturn && (i > 0))
            this.invokerSetNoClip(true);
        else
            super.tickInVoid();
    }

    public void setStack(ItemStack stack) {
        if (stack.getItem() == Items.TRIDENT || stack.isEmpty()) {
            this.tridentStack = stack.copy();
            this.dataTracker.set(ENCHANTED, stack.hasGlint());
        }
    }
}
