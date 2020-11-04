package net.projectlynx.lynx.commands.bans;

import net.projectlynx.lynx.commands.PlayerRequiredComand;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;

public class BanGUICommand extends PlayerRequiredComand {

    public BanGUICommand() {
        super("bangui");

        setDescription("Opens the Ban GUI for the specified player.");
    }

    @Override
    protected void onCommandFor(Player player) {
        checkConsole();
    }
}
