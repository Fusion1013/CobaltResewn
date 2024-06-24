package se.fusion1013.items.trinket;

import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.scoreboard.ScoreHolder;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.network.ServerPlayerEntity;
import se.fusion1013.Main;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.networking.CobaltNetworkingConstants;

public class MechanicSpectaclesTrinket extends CobaltTrinketItem {


    public MechanicSpectaclesTrinket(CobaltItem.Settings settings, TrinketModifierProvider modifierProvider) {
        super(settings, modifierProvider);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);

        if (entity instanceof ServerPlayerEntity user) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeInt(getScoreboardValue("wf_power", "power"));
            buf.writeInt(getScoreboardValue("wf_pressure", "pressure"));
            ServerPlayNetworking.send(user, CobaltNetworkingConstants.UPDATE_WF_STATUS_S2C, buf);
        }
    }

    private int getScoreboardValue(String objectiveName, String scoreHolderName) {
        var server = Main.server;
        if (server == null) return 0;
        var scoreboard = server.getScoreboard();
        var objective = findObjective(objectiveName, scoreboard);
        var score = scoreboard.getScore(ScoreHolder.fromName(scoreHolderName), objective);
        if (score == null) return 0;
        return score.getScore();
    }

    private ScoreboardObjective findObjective(String name, ServerScoreboard scoreboard) {
        for (ScoreboardObjective objective : scoreboard.getObjectives()) {
            if (objective.getName().equalsIgnoreCase(name)) return objective;
        }
        return null;
    }
}
