package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.mixins.accessors.EntityAccessorMixin;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin
        implements EntityAccessorMixin
{
    @Shadow
    public abstract ItemStack getStack();

    @Inject(
            method = "damage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ItemEntity;remove()V"
            )
    )
    public void onKilled(CallbackInfoReturnable<Boolean> cir) {
        if (CarpetLunaarSettings.shulkerBoxItemsDropContents
                && (this.getStack().getItem() instanceof BlockItem)
                && (((BlockItem) this.getStack().getItem()).getBlock() instanceof ShulkerBoxBlock)) {
            NbtCompound nbtCompound = this.getStack().getTag();
            if (nbtCompound != null) {
                NbtList nbtList = nbtCompound.getCompound("BlockEntityTag").getList("Items", 10);
                Stream<ItemStack> stream = nbtList.stream().map(NbtCompound.class::cast).map(ItemStack::fromNbt);
                World world = this.accessorGetWorld();
                if (!world.isClient) {
                    stream.forEach((itemStack) ->
                            world.spawnEntity(new ItemEntity(world, this.invokerGetX(), this.invokerGetY(),
                                    this.invokerGetZ(), itemStack))
                    );
                }
            }
        }
    }
}
