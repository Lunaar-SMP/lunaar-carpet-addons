package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarExtension;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin
{
    @Inject(
            method = "shutdown",
            at = @At("TAIL")
    )
    private void onShutdownComplete(CallbackInfo ci) {
        CarpetLunaarExtension.onServerDoneClosing();
    }
}
