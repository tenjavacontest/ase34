package de.ase34.aquariumdecorator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import de.ase34.aquariumdecorator.commands.PlaceCommand;
import de.ase34.aquariumdecorator.storage.Storage;
import de.ase34.aquariumdecorator.storage.YamlStorage;

public class Main extends JavaPlugin {

    private List<Fish> fishes;
    private List<String> pickupers;
    private Storage storage;

    @Override
    public void onDisable() {
        storage.save(fishes);

        saveConfig();
    }

    @Override
    public void onEnable() {
        // config initialization
        saveDefaultConfig();

        // storage setup
        storage = new YamlStorage(getConfig());

        // variables
        fishes = storage.load();
        pickupers = new ArrayList<String>();

        // event handlers
        // TODO add code

        // tasks
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new FishLocationUpdater(this), 0, 1);

        // commands
        getCommand("pickup").setExecutor(new PlaceCommand(this));
    }

    public List<Fish> getFishes() {
        return fishes;
    }

    public List<String> getPickupers() {
        return pickupers;
    }

}
