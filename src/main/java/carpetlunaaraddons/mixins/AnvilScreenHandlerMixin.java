package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin
{
    @ModifyVariable(
            method = "setNewItemName",
            at = @At("HEAD"),
            ordinal = 0
    )
    private String setNewItemName(String string) {
        if (CarpetLunaarSettings.anvilColorFormatting && string.matches(".*&([0123456789abcdefklmnor]).*"))
            return string.replace("&", "\u00A7");

        return string;
    }

}

