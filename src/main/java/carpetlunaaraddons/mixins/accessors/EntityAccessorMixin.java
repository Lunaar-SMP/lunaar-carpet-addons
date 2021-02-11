package carpetlunaaraddons.mixins.accessors;

import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessorMixin
{
	@Accessor("dataTracker") DataTracker getDataTracker();
}
