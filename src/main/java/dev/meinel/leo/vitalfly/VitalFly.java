/*
 * File: VitalFly.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitalfly;

import dev.meinel.leo.vitalfly.commands.VitalFlyCmd;
import dev.meinel.leo.vitalfly.commands.VitalFlySpeedCmd;
import dev.meinel.leo.vitalfly.files.Messages;
import dev.meinel.leo.vitalfly.listeners.PlayerChangedWorld;
import dev.meinel.leo.vitalfly.listeners.PlayerGamemodeChange;
import dev.meinel.leo.vitalfly.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VitalFly
        extends JavaPlugin {

    private Messages messages;

    @Override
    public void onEnable() {
        registerListeners();
        registerCommands();
        saveDefaultConfig();
        messages = new Messages();
        Bukkit.getLogger()
                .info("VitalFly v" + this.getDescription()
                        .getVersion() + " enabled");
        Bukkit.getLogger()
                .info("Copyright (C) 2022 Leopold Meinel");
        Bukkit.getLogger()
                .info("This program comes with ABSOLUTELY NO WARRANTY!");
        Bukkit.getLogger()
                .info("This is free software, and you are welcome to redistribute it under certain conditions.");
        Bukkit.getLogger()
                .info("See https://github.com/LeoMeinel/VitalFly/blob/main/LICENSE for more details.");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger()
                .info("VitalFly v" + this.getDescription()
                        .getVersion() + " disabled");
    }

    private void registerListeners() {
        getServer().getPluginManager()
                .registerEvents(new PlayerChangedWorld(), this);
        getServer().getPluginManager()
                .registerEvents(new PlayerGamemodeChange(), this);
        getServer().getPluginManager()
                .registerEvents(new PlayerJoin(), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("fly"))
                .setExecutor(new VitalFlyCmd());
        Objects.requireNonNull(getCommand("flyspeed"))
                .setExecutor(new VitalFlySpeedCmd());
    }

    public Messages getMessages() {
        return messages;
    }
}
