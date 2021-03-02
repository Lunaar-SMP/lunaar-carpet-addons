package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import carpetlunaaraddons.helpers.ChunkManagerHelper;
import net.minecraft.server.world.ChunkTicketManager;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ServerChunkManager.class)
public abstract class ServerChunkManagerMixin
{
	@Shadow @Final private ChunkTicketManager ticketManager;

	@Shadow protected abstract void ifChunkLoaded(long pos, Consumer<WorldChunk> chunkConsumer);

	@Inject(
			method = "tickChunks",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V",
					ordinal = 1,
					shift = At.Shift.AFTER
			)
	)
	public void helperPutter(CallbackInfo ci) {
		if (CarpetLunaarSettings.phantomsCapped) {
			ChunkManagerHelper.putTicketManager(this.ticketManager);
			ChunkManagerHelper.putChunkSource(this::ifChunkLoaded);
		}
	}
}
