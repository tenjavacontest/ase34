package de.ase34.aquariums;

import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;

import de.ase34.aquariums.commands.AddAquariumCommandExecutor;
import de.ase34.aquariums.storage.AbstractStorage;
import de.ase34.aquariums.storage.YAMLStorage;

public class Main extends JavaPlugin {

    private Set<Aquarium> aquariums;
    private AbstractStorage storage;

    @Override
    public void onDisable() {
        // Save aquariums
        storage.save(aquariums);
        saveConfig();

        getLogger().info(getDescription().getFullName() + " disabled!");
    }

    @Override
    public void onEnable() {
        // Initialize config
        saveDefaultConfig();

        // Initialize variables
        storage = new YAMLStorage(getConfig());

        // Load aquariums
        aquariums = storage.load();

        for (Aquarium aquarium : aquariums) {
            aquarium.populate();
        }

        // Register commands
        getCommand("aquarium-add").setExecutor(new AddAquariumCommandExecutor(this));

        getLogger().info(getDescription().getFullName() + " enabled!");
    }

    public Set<Aquarium> getAquariums() {
        return aquariums;
    }

    public AbstractStorage getStorage() {
        return storage;
    }

}
