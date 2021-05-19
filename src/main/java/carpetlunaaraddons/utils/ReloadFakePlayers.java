package carpetlunaaraddons.utils;

import carpet.fakes.ServerPlayerEntityInterface;
import carpet.patches.EntityPlayerMPFake;
import carpet.patches.NetworkManagerFake;
import carpetlunaaraddons.helpers.EntityPlayerActionPackInterface;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket;
import net.minecraft.network.packet.s2c.play.EntitySetHeadYawS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class ReloadFakePlayers {

    public static EntityPlayerMPFake create(MinecraftServer server, String botString) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        String username = botString.split(":")[0];
        ServerWorld worldIn  = server.getOverworld();
        ServerPlayerInteractionManager interactionManagerIn = new ServerPlayerInteractionManager(worldIn);
        GameProfile gameprofile = server.getUserCache().findByName(username);
        if(gameprofile == null){
            return null;
        }
        if (gameprofile.getProperties().containsKey("textures"))
        {
            gameprofile = SkullBlockEntity.loadProperties(gameprofile);
        }
        Constructor<EntityPlayerMPFake> constructor =  (Constructor<EntityPlayerMPFake>) EntityPlayerMPFake.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        EntityPlayerMPFake instance = constructor.newInstance(server,worldIn,gameprofile,interactionManagerIn, false);
        server.getPlayerManager().loadPlayerData(instance);
        server.getPlayerManager().onPlayerConnect(new NetworkManagerFake(NetworkSide.SERVERBOUND), instance);
        instance.teleport(instance.getServerWorld(),instance.getX(),instance.getY(),instance.getZ(),instance.yaw,instance.pitch);
        ((EntityPlayerActionPackInterface)((ServerPlayerEntityInterface)instance).getActionPack()).fromString(botString);
        instance.removed = false;
        instance.stepHeight = 0.6f;
        server.getPlayerManager().sendToDimension(new EntitySetHeadYawS2CPacket(instance, (byte) (instance.headYaw * 256 / 360)), instance.getServerWorld().getRegistryKey());
        server.getPlayerManager().sendToDimension(new EntityPositionS2CPacket(instance), instance.getServerWorld().getRegistryKey());
        instance.getServerWorld().getChunkManager().updateCameraPosition(instance);
        instance.getDataTracker().set(PlayerEntity.PLAYER_MODEL_PARTS,(byte) 0x7f);
        return instance;
    //return null;
    }

}
