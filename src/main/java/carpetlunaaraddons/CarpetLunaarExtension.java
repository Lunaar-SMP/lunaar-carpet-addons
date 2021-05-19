package carpetlunaaraddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.fakes.ServerPlayerEntityInterface;
import carpet.helpers.EntityPlayerActionPack;
import carpet.patches.EntityPlayerMPFake;
import carpetlunaaraddons.helpers.EntityPlayerActionPackInterface;
import carpetlunaaraddons.utils.ReloadFakePlayers;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.WorldSavePath;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.List;

public class CarpetLunaarExtension implements CarpetExtension
{
    public static void noop() { }
    static {
        CarpetServer.manageExtension(new CarpetLunaarExtension());
    }

    @Override
    public void onGameStarted() {
        // let's /carpet handle our few simple settings
        CarpetServer.settingsManager.parseSettingsClass(CarpetLunaarSettings.class);

        // set-up a snooper to observe how rules are changing in carpet
        CarpetServer.settingsManager.addRuleObserver( (serverCommandSource, currentRuleState, originalUserTest) ->
        {

        });
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        // reloading of /carpet settings is handled by carpet
        // reloading of own settings is handled as an extension, since we claim own settings manager
    }


    @Override
    public void registerLoggers() {

    }

    public void onServerLoadedWorlds(MinecraftServer server) {
        loadBots(server);
    }

    @Override
    public void onServerClosed(MinecraftServer server) {
        saveFakePlayerData(server);

    }

    private void loadBots(MinecraftServer server){
        try{
            BufferedReader b = Files.newBufferedReader(server.getSavePath(WorldSavePath.ROOT).resolve("bots.conf"));
            String line = "";
            while ((line = b.readLine()) != null)
            {
                ReloadFakePlayers.create(server,line);
            }
            b.close();

        } catch (IOException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    private void saveFakePlayerData(MinecraftServer server){
        List<ServerPlayerEntity> playerList = server.getPlayerManager().getPlayerList();
        try(BufferedWriter fw = Files.newBufferedWriter(server.getSavePath(WorldSavePath.ROOT).resolve("bots.conf"))) {
            for (ServerPlayerEntity player : playerList) {
                //System.out.println(player.getName().getString());
                if (player instanceof EntityPlayerMPFake) {
                    EntityPlayerActionPack e = ((ServerPlayerEntityInterface) player).getActionPack();
                    String s = player.getName().getString() + ((EntityPlayerActionPackInterface) e).toBotString();
                    fw.write(s);
                    fw.newLine();
                    //System.out.println(player.getName().getString() + ((EntityPlayerActionPackInterface) e).toBotString());
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


}
