package carpetlunaaraddons.helpers;

import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class TridentDispenserBehavior extends ProjectileDispenserBehavior
{

    @Override
    protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
        if (stack.damage(1, world.getRandom(), null))
            stack.decrement(1);

        TridentEntity trident = new TridentEntity(EntityType.TRIDENT, world);
        trident.updatePosition(position.getX(), position.getY(), position.getZ());
        trident.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        ((TridentEntityDuckInterface) trident).setStack(stack);
        return trident;
    }

}
