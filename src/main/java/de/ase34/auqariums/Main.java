package de.ase34.auqariums;

import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public Set<Aquarium> aquariums;

    @Override
    public void onDisable() {
        getLogger().info(getDescription().getFullName() + " disabled!");
    }

    @Override
    public void onEnable() {
        getLogger().info(getDescription().getFullName() + " enabled!");
    }

}
