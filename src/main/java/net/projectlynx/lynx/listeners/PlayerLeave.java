package net.projectlynx.lynx.listeners;

import net.projectlynx.lynx.storage.PlayerData;
import net.projectlynx.lynx.storage.database.LynxDatabase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {

        PlayerData playerData = PlayerData.getCache(event.getPlayer());

        LynxDatabase.getInstance().save(event.getPlayer().getName(), event.getPlayer().getUniqueId(), playerData);
    }
}