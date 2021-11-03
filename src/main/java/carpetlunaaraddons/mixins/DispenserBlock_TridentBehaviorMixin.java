package carpetlunaaraddons.mixins;


import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.helpers.TridentDispenserBehavior;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = DispenserBlock.class, priority = 999)
public class DispenserBlock_TridentBehaviorMixin {

    private static final DispenserBehavior SHOOT_TRIDENT = new TridentDispenserBehavior();

    @Inject(
            method = "dispense",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/DispenserBlock;getBehaviorForItem(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/block/dispenser/DispenserBehavior;"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    private void dispenseCustomBehavior(ServerWorld serverWorld, BlockPos pos, CallbackInfo ci, BlockPointerImpl blockPointerImpl, DispenserBlockEntity dispenserBlockEntity, int i, ItemStack itemStack) {
        if(CarpetLunaarSettings.dispensersShootTridents && itemStack.getItem() == Items.TRIDENT){
            dispenserBlockEntity.setStack(i, SHOOT_TRIDENT.dispense(blockPointerImpl,itemStack));
            ci.cancel();
        }
    }
}
