package carpetlunaaraddons;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

import java.util.UUID;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class CarpetLunaarCommands {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) { // This is totally not sketch or anything. Totally isnt copy pasted from WeirdAddons
        dispatcher.register(literal("color").requires((player) -> SettingsManager.canUseCommand(player, WeirdAddonsSettings.commandWeird)).
            executes((c) -> {
                WeirdAddonsUtil.sendToPlayer(c.getSource().getPlayer().getUuid(),
                "§l----------: §6§lColors: §r§l :----------" + "\n"
                        + "§aGreen: &a" + "\n"
                        + "§bAqua: &b" + "\n"
                        + "§cRed: &c" + "\n"
                        + "§dPink: &d" + "\n"
                        + "§eYellow: &e" + "\n"
                        + "§fWhite: &f" + "\n"
                        + "§0Black: &0" + "\n"
                        + "§1Dark Blue: &1" + "\n"
                        + "§2Dark Green: &2" + "\n"
                        + "§3Dark Aqua: &3" + "\n"
                        + "§4Dark Red: &4" + "\n"
                        + "§5Dark Purple: &5" + "\n"
                        + "§6Gold: &6" + "\n"
                        + "§7Grey: &7" + "\n"
                        + "§8Dark Grey &8" + "\n"
                        + "§9Indigo: &9§r" + "\n"
                        +"§l----------: §6§lFormats: §r§l :----------§r" + "\n"
                        + "Obfuscate: &k" + "\n"
                        + "§lBold: &l" + "\n"
                        + "§mStrike Through: &m§r" + "\n"
                        + "§nUnderline: &n§r" + "\n"
                        + "§oItalics &o§r" + "\n");}