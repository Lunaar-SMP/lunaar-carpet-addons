package carpetlunaaraddons.mixins;

import carpetlunaaraddons.helpers.TridentDispenserBehavior;
import net.minecraft.Bootstrap;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public class BootstrapMixin
{
    @Inject(
            method = "initialize",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/dispenser/DispenserBehavior;registerDefaults()V",
                    shift = At.Shift.AFTER
            )
    )
    private static void registerTridentBehavior(CallbackInfo ci) {
        DispenserBlock.registerBehavior(Items.TRIDENT, new TridentDispenserBehavior());
    }
}
