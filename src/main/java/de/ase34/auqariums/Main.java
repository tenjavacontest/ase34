package de.ase34.auqariums;

import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;

import de.ase34.auqariums.commands.AddAquariumCommandExecutor;
import de.ase34.auqariums.storage.AbstractStorage;
import de.ase34.auqariums.storage.YAMLStorage;

public class Main extends JavaPlugin {

    private Set<Aquarium> aquariums;
    private AbstractStorage storage;

    @Override
    public void onDisable() {
        getLogger().info(getDescription().getFullName() + " disabled!");
    }

    @Override
    public void onEnable() {
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
