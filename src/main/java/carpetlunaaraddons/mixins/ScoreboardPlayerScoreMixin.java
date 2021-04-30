package carpetlunaaraddons.mixins;

import carpetlunaaraddons.CarpetLunaarSettings;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ScoreboardPlayerScore.class)
public class ScoreboardPlayerScoreMixin
{
    @Redirect(
            method = "setScore",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/scoreboard/Scoreboard;updateScore(Lnet/minecraft/scoreboard/ScoreboardPlayerScore;)V"
            )
    )
    public void updateScoreRedirect(Scoreboard scoreboard, ScoreboardPlayerScore score) {
        if (!CarpetLunaarSettings.filterBotsInScores
                || (score.getScoreboard().getPlayerTeam(score.getPlayerName()) != null))
            scoreboard.updateScore(score);
    }
}
