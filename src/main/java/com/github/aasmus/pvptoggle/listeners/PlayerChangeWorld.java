package com.github.aasmus.pvptoggle.listeners;

import com.github.aasmus.pvptoggle.PvPToggle;
import com.github.aasmus.pvptoggle.utils.Chat;
import com.github.aasmus.pvptoggle.utils.Util;
import org.bukkit.GameRules;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.jspecify.annotations.NonNull;

public class PlayerChangeWorld implements Listener {
    @EventHandler
    public void onChangeWorld(@NonNull PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        boolean playerPvpEnabled = !Util.getPlayerState(player.getUniqueId());
        boolean worldPvpEnabled = Boolean.TRUE.equals(world.getGameRuleDefault(GameRules.PVP));

        // If PVP isn't enabled in the world but the player has it enabled, disable it.
        if (!worldPvpEnabled && playerPvpEnabled) {
            Util.setPlayerState(player.getUniqueId(), true);
            Chat.send(player, "PVP_WORLD_CHANGE_DISABLED");
            return;
        }

        // If PVP is required (i.e. the world has PVP enabled, and it is in the blocked worlds) and the player has it disabled, enable it.
        if (worldPvpEnabled && PvPToggle.blockedWorlds.contains(world.getName()) && !playerPvpEnabled) {
            Util.setPlayerState(player.getUniqueId(), false);
            Chat.send(player, "PVP_WORLD_CHANGE_REQUIRED");
            if (PvPToggle.instance.getConfig().getBoolean("SETTINGS.PARTICLES")) {
                Util.particleEffect(player);
            }
            if (PvPToggle.instance.getConfig().getBoolean("SETTINGS.NAMETAG")) {
                Util.ChangeNametag(player, "&c");
            }
        }
    }
}
