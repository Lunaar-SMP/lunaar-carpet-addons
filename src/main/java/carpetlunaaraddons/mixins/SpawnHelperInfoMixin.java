package carpetlunaaraddons.mixins;

import carpetlunaaraddons.helpers.SpawnHelperInfoDuckInterface;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.math.GravityField;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SpawnHelper.Info.class)
public abstract class SpawnHelperInfoMixin implements SpawnHelperInfoDuckInterface
{
    @Shadow
    @Final
    private int spawningChunkCount;

    @Shadow
    @Final
    private Object2IntOpenHashMap<SpawnGroup> groupToCount;

    @Shadow
    @Final
    private GravityField densityField;

    @Invoker("<init>")
    static SpawnHelper.Info newInfo(int chunkCount, Object2IntOpenHashMap<SpawnGroup> spawnGroupMap, GravityField gravityField) {
        throw new AssertionError();
    }

    public SpawnHelper.Info copy() {
        return newInfo(this.spawningChunkCount, this.groupToCount, this.densityField);
    }
}
