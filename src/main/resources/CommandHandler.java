package carpetlunaaraddons;

import carpet.CarpetServer;
import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

import java.util.*;

public class CommandHandler {

    public static HashSet<UUID> playersWatching = new HashSet<>();

    public static void sendToPlayer(UUID playerUUID, String msg) {
        CarpetServer.minecraft_server.getPlayerManager().getPlayer(playerUUID).sendMessage(new LiteralText(msg), MessageType.SYSTEM,playerUUID);
    }
}