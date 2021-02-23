package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.helpers.GetAttackDamageHelper;
import carpetlunaaraddons.mixins.accessors.EntityAccessorMixin;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin
	implements EntityAccessorMixin
{
	@Shadow @Final public PlayerAbilities abilities;

	@Shadow public abstract boolean damage(DamageSource source, float amount);

	@Shadow public abstract SoundCategory getSoundCategory();

	@Redirect(
			method = "attack",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F",
					ordinal = 0
			)
	)
	public float alternateGetAttackDamage(ItemStack stack, EntityGroup group, Entity target) {
		if (CarpetLunaarSettings.impalingAffectsMobsInWater)
			return GetAttackDamageHelper.getAttackDamage(stack, (LivingEntity)target);
		else
			return EnchantmentHelper.getAttackDamage(stack, ((LivingEntity)target).getGroup());
	}

	@Inject(
			method = "attack",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/entity/Entity;handleAttack(Lnet/minecraft/entity/Entity;)Z",
					shift = At.Shift.BY,
					by = -2
			),
			cancellable = true
	)
	public void creativeKill(Entity target, CallbackInfo ci) {
		if (this.abilities.creativeMode
				&& !this.accessorGetWorld().isClient // this is to prevent a bug with ender dragons
				&& CarpetLunaarSettings.creativeOneHitKill) {
			if (target instanceof EnderDragonPart) {
				Arrays.stream(((EnderDragonPart) target).owner.getBodyParts()).forEach(Entity::kill);
				((EnderDragonPart) target).owner.kill();
			} else {
				target.kill();
			}
			this.accessorGetWorld().playSound(null, this.invokerGetX(), this.invokerGetY(), this.invokerGetZ(),
					SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, this.getSoundCategory(), 1.0F, 1.0F);
			ci.cancel();
		}
	}
}
