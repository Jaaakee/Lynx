package net.projectlynx.lynx.commands;

import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommand;

public abstract class PlayerRequiredComand extends SimpleCommand {

    protected PlayerRequiredComand(String sublabel) {
        super(sublabel);

        setMinArguments(1);
        setUsage("<target>");
    }

    @Override
    protected void onCommand() {

        // TODO: Get OfflinePlayer Async
    }

    protected abstract void onCommandFor(Player player);
}
