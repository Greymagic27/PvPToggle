package com.github.aasmus.pvptoggle.utils;

import com.github.aasmus.pvptoggle.PvPToggle;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class PersistentData {

    private final File dir;

    public PersistentData(@NonNull File file) {
        //noinspection ResultOfMethodCallIgnored
        file.mkdir();
        this.dir = file;
    }

    public void addPlayer(@NonNull Player p) {
        File file = new File(dir.getPath(), p.getUniqueId() + ".yml");
        if (!file.exists()) {
            try {
                FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
                playerData.createSection("PvPState");
                playerData.set("PvPState", PvPToggle.instance.getConfig().getBoolean("SETTINGS.DEFAULT_PVP_OFF"));
                playerData.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void UpdatePlayerPvPState(@NonNull Player p) {
        File file = new File(dir.getPath(), p.getUniqueId() + ".yml");
        try {
            FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);
            playerData.set("PvPState", PvPToggle.instance.players.get(p.getUniqueId()));
            playerData.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean GetPlayerPvPState(@NonNull Player p) {
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(new File(dir.getPath(), p.getUniqueId() + ".yml"));
        return playerData.getBoolean("PvPState");
    }
}
