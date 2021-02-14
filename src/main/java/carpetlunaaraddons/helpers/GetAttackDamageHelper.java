package carpetlunaaraddons.helpers;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.ImpalingEnchantment;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.mutable.MutableFloat;

public class GetAttackDamageHelper
{
	public static float getAttackDamage(ItemStack stack, LivingEntity target) {
		MutableFloat mutableFloat = new MutableFloat();
		EnchantmentHelper.forEachEnchantment((enchantment, level) -> {
			if (enchantment instanceof ImpalingEnchantment)
				mutableFloat.add((target.getGroup() == EntityGroup.AQUATIC) || (target.isTouchingWaterOrRain())
						? (float)level * 2.5F : 0.0F);
			else
				mutableFloat.add(enchantment.getAttackDamage(level,target.getGroup()));
		},stack);
		return mutableFloat.floatValue();
	}
}
