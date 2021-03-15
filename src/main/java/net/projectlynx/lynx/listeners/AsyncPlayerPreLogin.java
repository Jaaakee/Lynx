package net.projectlynx.lynx.listeners;

import net.projectlynx.lynx.storage.PlayerData;
import net.projectlynx.lynx.storage.database.LynxDatabase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.mineacademy.fo.Common;

import java.util.UUID;

public class AsyncPlayerPreLogin implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        final UUID uuid = event.getUniqueId();

        final PlayerData data = PlayerData.getCache(uuid);
        LynxDatabase.getInstance().load(uuid, data);

        if (data.isBanned()) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
            event.setKickMessage("Banned");

            Common.runLater(() -> {
                data.setBanned(false);
            });

            return;
        }

        System.out.println("Loading from Database...");

        Common.runLater(() -> {
            data.setBanned(true);
        });

        // TODO: Check if player is banned
        // TODO: If player is banned, set preloginevent result to KICK_BANNED
        // TODO: Ban message
    }
}