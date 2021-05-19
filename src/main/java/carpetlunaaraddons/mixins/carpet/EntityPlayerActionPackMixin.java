package carpetlunaaraddons.mixins.carpet;


import carpet.helpers.EntityPlayerActionPack;
import carpetlunaaraddons.helpers.EntityPlayerActionPackInterface;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.TreeMap;

@Mixin(EntityPlayerActionPack.class)
public class EntityPlayerActionPackMixin implements EntityPlayerActionPackInterface {

    @Shadow @Final
    private Map<EntityPlayerActionPack.ActionType, EntityPlayerActionPack.Action> actions;

    @Shadow private BlockPos currentBlock;
    @Shadow private int blockHitDelay;
    @Shadow private boolean isHittingBlock;
    @Shadow private float curBlockDamageMP;

    @Shadow private boolean sneaking;
    @Shadow private boolean sprinting;
    @Shadow private float forward;
    @Shadow private float strafing;

    @Shadow private int itemUseCooldown;

    @Override
    public String toBotString(){
        String s = ""
        // + currentBlock.getX() + ":" + currentBlock.getY() + ":" + currentBlock.getZ()
        // + ":" + blockHitDelay
        // + ":" + (isHittingBlock ? "t" : "f")
        // + ":" + curBlockDamageMP
        + ":" + (sneaking ? "t" : "f")
        + ":" + (sprinting ? "t" : "f")
        + ":" + forward
        + ":" + strafing;
        // + ":" + itemUseCooldown;

        EntityPlayerActionPack.Action attack = actions.get(EntityPlayerActionPack.ActionType.ATTACK);
        EntityPlayerActionPack.Action use = actions.get(EntityPlayerActionPack.ActionType.USE);
        EntityPlayerActionPack.Action jump = actions.get(EntityPlayerActionPack.ActionType.JUMP);

        if(attack != null){
            s += ":" + "t"
              + ":" + attack.interval;
        }else {
            s += ":" + "f"
                    + ":0";
        }

        if(use != null){
            s += ":" + "t"
                    + ":" + use.interval;
        }else {
            s += ":" + "f"
                    + ":0";
        }

        if(jump != null){
            s += ":" + "t"
                    + ":" + jump.interval;
        }else {
            s += ":" + "f"
                    + ":0";
        }


        return s;
    }

    @Override
    public void fromString(String s){
        String[] args = s.split(":");
        sneaking = args[1].equals("t");
        sprinting = args[2].equals("t");
        forward = Float.parseFloat(args[3]);
        strafing = Float.parseFloat(args[4]);
        if(args[5].equals("t")){
            actions.put(EntityPlayerActionPack.ActionType.ATTACK, EntityPlayerActionPack.Action.interval(Integer.parseInt(args[6])));
        }
        if(args[7].equals("t")){
            actions.put(EntityPlayerActionPack.ActionType.ATTACK, EntityPlayerActionPack.Action.interval(Integer.parseInt(args[8])));
        }
        if(args[9].equals("t")){
            actions.put(EntityPlayerActionPack.ActionType.ATTACK, EntityPlayerActionPack.Action.interval(Integer.parseInt(args[10])));
        }
    }

}
