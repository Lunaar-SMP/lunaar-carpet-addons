package carpetlunaaraddons.helpers;

import net.minecraft.world.SpawnHelper;

public class ChunkManagerHelper
{
    private static SpawnHelper.Info info;
    private static int spawningChunkCount;

    public static void putInfoAndSpawningChunkCount(SpawnHelper.Info info, int spawningChunkCount) {
        ChunkManagerHelper.info = info;
        ChunkManagerHelper.spawningChunkCount = spawningChunkCount;
    }

    public static SpawnHelper.Info getInfo() {
        return info;
    }

    public static int getSpawningChunkCount() {
        return spawningChunkCount;
    }
}
