package carpetlunaaraddons.helpers;

import net.minecraft.world.SpawnHelper;

public class ChunkManagerHelper
{
	private static SpawnHelper.Info info;
	private static int spawningChunkCount;

	public static SpawnHelper.Info getInfo() {
		return info;
	}

	public static void putInfo(SpawnHelper.Info info) {
		ChunkManagerHelper.info = info;
	}

	public static int getSpawningChunkCount() {
		return spawningChunkCount;
	}

	public static void putSpawningChunkCount(int spawningChunkCount) {
		ChunkManagerHelper.spawningChunkCount = spawningChunkCount;
	}
}
