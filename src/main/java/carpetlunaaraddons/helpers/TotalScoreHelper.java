package carpetlunaaraddons.helpers;

import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.command.ServerCommandSource;

public class TotalScoreHelper {
    public static int getTotal(ServerCommandSource source, ScoreboardObjective objective, boolean bots) {
        int i = 0;
        for (ScoreboardPlayerScore score: source.getMinecraftServer().getScoreboard().getAllPlayerScores(objective)) {
            if (!bots && source.getMinecraftServer().getScoreboard().getPlayerTeam(score.getPlayerName()) == null)
                continue;
            i += score.getScore();
        }
        return i;
    }
}
