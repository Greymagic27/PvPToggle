package com.github.aasmus.pvptoggle.utils;

import com.github.aasmus.pvptoggle.PvPToggle;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

public class Chat {

    // sends message without a parameter
    public static void send(CommandSender sender, String message) {
        String msg = formatMessage(message);
        if (msg.isEmpty()) return;
        sender.sendMessage(Component.text(msg));
    }

    // sends message with a parameter
    public static void send(CommandSender sender, String message, String parameter) {
        String msg = formatMessage(message);
        if (msg.isEmpty()) return;
        String output = msg.replace("<parameter>", parameter);
        sender.sendMessage(Component.text(output));
    }

    // sends message with a parameter and pvp state
    public static void send(CommandSender sender, String message, String parameter, Boolean pvpState) {
        String msg = formatMessage(message);
        if (msg.isEmpty()) return;
        if (parameter == null) parameter = "";
        boolean state = pvpState != null && pvpState;
        String output = msg.replace("<parameter>", parameter).replace("<pvpstate>", state ? "off" : "on");
        sender.sendMessage(Component.text(output));
    }

    private static @NonNull String formatMessage(String message) {
        String msg = PvPToggle.instance.getConfig().getString("MESSAGES." + message);
        if (Objects.requireNonNull(msg).isEmpty()) return "";
        return msg.replace('&', '§');
    }
}
