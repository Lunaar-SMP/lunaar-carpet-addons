package carpetlunaaraddons.helpers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ImpalingEnchantment;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.mutable.MutableFloat;

public class GetAttackDamageHelper
{
	public static float getAttackDamage(ItemStack stack, LivingEntity target) {
		MutableFloat mutableFloat = new MutableFloat();
		forEachEnchantment((enchantment, level) -> {
			if (enchantment instanceof ImpalingEnchantment)
				mutableFloat.add((target.getGroup() == EntityGroup.AQUATIC) || (target.isTouchingWaterOrRain())
						? (float)level * 2.5F : 0.0F);
			else
				mutableFloat.add(enchantment.getAttackDamage(level,target.getGroup()));
		},stack);
		return mutableFloat.floatValue();
	}

	private static void forEachEnchantment(GetAttackDamageHelper.Consumer consumer, ItemStack stack) {
		if (!stack.isEmpty()) {
			ListTag listTag = stack.getEnchantments();

			for(int i = 0; i < listTag.size(); ++i) {
				String string = listTag.getCompound(i).getString("id");
				int j = listTag.getCompound(i).getInt("lvl");
				Registry.ENCHANTMENT.getOrEmpty(Identifier.tryParse(string)).ifPresent((enchantment) -> {
					consumer.accept(enchantment, j);
				});
			}

		}
	}

	@FunctionalInterface
	interface Consumer {
		void accept(Enchantment enchantment, int level);
	}
}
