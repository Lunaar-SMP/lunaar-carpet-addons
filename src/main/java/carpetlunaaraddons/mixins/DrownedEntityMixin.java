package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DrownedEntity.class)
public class DrownedEntityMixin
{
	@Redirect(
			method = "attack",
			at = @At(
					value = "NEW",
					target = "net/minecraft/entity/projectile/TridentEntity"
			)
	)
	public TridentEntity dummyTridentEntity(World world, LivingEntity owner, ItemStack stack) {
		if (CarpetLunaarSettings.drownedUseEnchantedTridents)
			stack = owner.getStackInHand(Hand.MAIN_HAND);
		return new TridentEntity(world, owner, stack);
	}
}
