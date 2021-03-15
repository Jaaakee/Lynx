package net.projectlynx.lynx;

import net.projectlynx.lynx.commands.bans.BanGUICommand;
import net.projectlynx.lynx.listeners.AsyncPlayerPreLogin;
import net.projectlynx.lynx.listeners.PlayerLeave;
import net.projectlynx.lynx.storage.database.LynxDatabase;
import org.mineacademy.fo.plugin.SimplePlugin;

public final class LynxPlugin extends SimplePlugin {

    @Override
    protected void onPluginStart() {
        /* Connect to Database */
        LynxDatabase.getInstance().connectDatabase();

        registerCommand(new BanGUICommand());

        registerEvents(new PlayerLeave());
        registerEvents(new AsyncPlayerPreLogin());
    }

    @Override
    protected void onPluginStop() {
        // Plugin shutdown logic
    }

    @Override
    protected void onPluginReload() {
        // Plugin reload logic
    }
}