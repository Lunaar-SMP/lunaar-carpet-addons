package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.nbt.NbtString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.DataOutput;

@Mixin(NbtString.class)
public abstract class NbtStringMixin
{

    private static final Logger LOGGER = LogManager.getLogger();

    @Mutable
    @Shadow
    @Final
    private String value;

    @Inject(
            method = "write(Ljava/io/DataOutput;)V",
            at = @At("HEAD")
    )
    private void respectReadLimitDuringWrite(DataOutput output, CallbackInfo ci) {
        if (CarpetLunaarSettings.chunkRegenFix) {
            int strlen = this.value.length();
            if (strlen > 28501) { //Minimum number that could bypass limit
                int utflen = 0;
                for (int i = 0; i < strlen; i++) {
                    char c = this.value.charAt(i);
                    utflen += ((c >= 0x0001) && (c <= 0x007F)) ? 1 : (c <= 0x07FF) ? 2 : 3;
                    if (utflen > 65535) {
                        this.value = this.value.substring(0, i - 2);
                        LOGGER.debug("Trimming Large String (" + strlen + " -> " + this.value.length() + ")");
                        break;
                    }
                }
            }
        }
    }
}