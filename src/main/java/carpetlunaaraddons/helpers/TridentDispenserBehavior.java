package carpetlunaaraddons.helpers;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BiFunction;

public class TridentDispenserBehavior extends ProjectileDispenserBehavior
{
    static BiFunction<BlockPointer, ItemStack, ItemStack> reflectedMethodFunction;

    static {
        try {
            ItemDispenserBehavior behavior = new ItemDispenserBehavior();
            Class<?> itemDispenserBehaviorClass = behavior.getClass();
            Class<?>[] args = new Class[]{BlockPointer.class, ItemStack.class};
            String name = FabricLoader.getInstance().getMappingResolver()
                    .mapMethodName("intermediary", "net.minecraft.class_2347", "method_10135",
                            "(Lnet/minecraft/class_2342;Lnet/minecraft/class_1799;)Lnet/minecraft/class_1799;");
            Method reflectedMethod = itemDispenserBehaviorClass.getDeclaredMethod(name, args);
            reflectedMethod.setAccessible(true);
            reflectedMethodFunction = ((pointer, stack) -> {
                try {
                    return (ItemStack) reflectedMethod.invoke(behavior, pointer, stack);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        return CarpetLunaarSettings.dispensersShootTridents ?
                super.dispenseSilently(pointer, stack) :
                reflectedMethodFunction.apply(pointer, stack);
    }

    @Override
    protected void playSound(BlockPointer pointer) {
        if (CarpetLunaarSettings.dispensersShootTridents)
            super.playSound(pointer);
        else
            pointer.getWorld().syncWorldEvent(1000, pointer.getBlockPos(), 0);
    }

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
