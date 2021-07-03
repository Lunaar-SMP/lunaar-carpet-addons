package carpetlunaaraddons.utils;

import carpet.CarpetServer;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;

import java.util.UUID;

public class CommandHandlerUtil
{
    public static void sendToPlayer(UUID playerUUID, String msg) {
        CarpetServer.minecraft_server.getPlayerManager().getPlayer(playerUUID).sendMessage(new LiteralText(msg), MessageType.SYSTEM, playerUUID);
    }
}