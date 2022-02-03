package com.tamrielnetwork.survivalfly.listeners;

import com.tamrielnetwork.survivalfly.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangeWorld implements Listener {
    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        if (!event.getPlayer().hasPermission("survivalfly.fly") || !event.getPlayer().hasPermission("survivalfly.fly.worldchange")) {
            return;
        }
        event.getPlayer().setAllowFlight(true);
        Utils.sendMessage(event.getPlayer(),"now-flying");

    }

}
