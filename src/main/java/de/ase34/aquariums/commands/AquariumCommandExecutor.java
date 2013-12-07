package de.ase34.aquariums.commands;

import org.bukkit.command.CommandExecutor;

import de.ase34.aquariums.Main;

public abstract class AquariumCommandExecutor implements CommandExecutor {

    protected Main plugin;

    public AquariumCommandExecutor(Main plugin) {
        this.plugin = plugin;
    }

}
