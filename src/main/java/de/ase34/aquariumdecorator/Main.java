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

        // TODO kill all fishes
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
        getServer().getPluginManager().registerEvents(new PickupListener(this), this);

        // tasks
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new FishLocationUpdater(this), 1, 1);

        // commands
        getCommand("place").setExecutor(new PlaceCommand(this));
        // TODO 2nd command

        // TODO spawn all fishes
    }

    public List<Fish> getFishes() {
        return fishes;
    }

    public List<String> getPickupers() {
        return pickupers;
    }

}
