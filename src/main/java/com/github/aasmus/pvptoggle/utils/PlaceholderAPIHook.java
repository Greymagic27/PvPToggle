package com.github.aasmus.pvptoggle.utils;

import com.github.aasmus.pvptoggle.PvPToggle;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;


public class PlaceholderAPIHook extends PlaceholderExpansion {

    private final PvPToggle plugin;

    public PlaceholderAPIHook(PvPToggle plugin) {
        this.plugin = plugin;
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        //Placeholder: %pvptoggle_pvp_state%
        if (identifier.equals("pvp_state")) {
            return PvPToggle.instance.players.get(player.getUniqueId()) ? "&aOff" : "&cOn";
        }
        //Placeholder: %pvptoggle_pvp_symbol%
        if (identifier.equals("pvp_symbol")) {
            return PvpToggle.instance.players.get(player.getUniqueId()) ? "" : "&6^";
        }
        //Placeholder: %pvptoggle_pvp_state_clean%
        if (identifier.equals("pvp_state_clean")) {
            return PvpToggle.instance.players.get(player.getUniqueId()) ? "false" : "true";
        }

        return null;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "PvPToggle";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }


    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

}
