package de.ase34.aquariumdecorator.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.Configuration;

import de.ase34.aquariumdecorator.Fish;

public class YamlStorage implements Storage {

    private Configuration config;

    public YamlStorage(Configuration config) {
        this.config = config;
    }

    @Override
    public void save(List<Fish> fishes) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        for (Fish fish : fishes) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("type", fish.getType().toString());

            HashMap<String, Object> location = new HashMap<String, Object>();
            location.put("world", fish.getLocation().getWorld().getName());
            location.put("x", fish.getLocation().getX());
            location.put("y", fish.getLocation().getY());
            location.put("z", fish.getLocation().getZ());

            map.put("location", location);
            list.add(map);
        }

        config.set("fishes", list);
    }

    @Override
    public List<Fish> load() {
        // TODO add code
        return null;
    }

}
