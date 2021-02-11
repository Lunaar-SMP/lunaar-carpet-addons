package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.mixins.accessors.EntityAccessorMixin;
import carpetlunaaraddons.mixins.accessors.PersistentProjectileEntityAccessorMixin;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
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

	protected TridentEntityMixin(EntityType<? extends PersistentProjectileEntity> type, double x, double y, double z, World world) {
		super(type, x, y, z, world);
	}

	protected TridentEntityMixin(EntityType<? extends PersistentProjectileEntity> type, LivingEntity owner, World world) {
		super(type, owner, world);
	}

	@Accessor("LOYALTY") abstract TrackedData<Byte> getLOYALTY();

	@Redirect(
			method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/data/DataTracker;set(Lnet/minecraft/entity/data/TrackedData;Ljava/lang/Object;)V",
					ordinal = 0
			)
	)
	public void loyaltySetter(DataTracker tracker, TrackedData<Object> trackedData, Object object,
	                          World world, LivingEntity owner, ItemStack stack) {
		if (CarpetLunaarSettings.drownedUseEnchantedTridents && owner instanceof DrownedEntity) return;
		tracker.set(trackedData,object);
	}

	@Override
	protected void destroy() {
		int i = this.getDataTracker().get(this.getLOYALTY());
		if ((i > 0) && CarpetLunaarSettings.voidedLoyaltyTridentsReturn)
			this.invokerSetNoClip(true);
		else
			super.destroy();
	}
}
