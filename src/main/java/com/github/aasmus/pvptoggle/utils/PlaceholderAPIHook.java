package com.github.aasmus.pvptoggle.utils;

import com.github.aasmus.pvptoggle.PvPToggle;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private final PvPToggle plugin;

    public PlaceholderAPIHook(PvPToggle plugin) {
        this.plugin = plugin;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NonNull String identifier) {
        if (player == null) return "";

        //Placeholder: %pvptoggle_pvp_state%
        return switch (identifier) {
            case "pvp_state" -> PvPToggle.instance.players.get(player.getUniqueId()) ? "&aOff" : "&cOn";

            //Placeholder: %pvptoggle_pvp_symbol%
            case "pvp_symbol" -> PvPToggle.instance.players.get(player.getUniqueId()) ? "" : "&6^";

            //Placeholder: %pvptoggle_pvp_state_clean%
            case "pvp_state_clean" -> PvPToggle.instance.players.get(player.getUniqueId()) ? "false" : "true";
            default -> null;
        };

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
    public @NonNull String getIdentifier() {
        return "PvPToggle";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getPluginMeta().getAuthors().toString();
    }


    @Override
    public @NonNull String getVersion() {
        return plugin.getPluginMeta().getVersion();
    }

}
