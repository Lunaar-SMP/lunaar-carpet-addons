package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.LoyaltyEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;
import java.util.stream.Collectors;

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
		if (CarpetLunaarSettings.drownedUseEnchantedTridents) {
			Map<Enchantment,Integer> map = EnchantmentHelper.get(owner.getStackInHand(Hand.MAIN_HAND)).entrySet()
					.stream().filter((entry) -> !(entry.getKey() instanceof LoyaltyEnchantment))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			EnchantmentHelper.set(map,stack);
		}
		return new TridentEntity(world, owner, stack);
	}
}
