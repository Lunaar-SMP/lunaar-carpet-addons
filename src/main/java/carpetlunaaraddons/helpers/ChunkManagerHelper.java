package carpetlunaaraddons.helpers;

import net.minecraft.server.world.ChunkTicketManager;
import net.minecraft.world.SpawnHelper;

public class ChunkManagerHelper
{
	private static ChunkTicketManager ticketManager;
	private static SpawnHelper.ChunkSource chunkSourceMethod;

	public static void putTicketManager(ChunkTicketManager externalTicketManager) {
		ticketManager = externalTicketManager;
	}

	public static ChunkTicketManager getTicketManager() {
		return ticketManager;
	}

	public static void putChunkSource(SpawnHelper.ChunkSource chunkSource) {
		chunkSourceMethod = chunkSource;
	}

	public static SpawnHelper.ChunkSource getChunkSource() {
		return chunkSourceMethod;
	}
}
