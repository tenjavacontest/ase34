package de.ase34.aquariumdecorator.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;

import de.ase34.aquariumdecorator.Fish;
import de.ase34.aquariumdecorator.Fish.FishType;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Fish> load() {
        ArrayList<Fish> fishes = new ArrayList<Fish>();

        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) config.get("fishes");
        for (HashMap<String, Object> map : list) {
            FishType type = FishType.valueOf((String) map.get("type"));

            HashMap<String, Object> location = (HashMap<String, Object>) map.get("location");
            double x = (Double) location.get("x");
            double y = (Double) location.get("y");
            double z = (Double) location.get("z");
            World world = Bukkit.getWorld((String) location.get("world"));

            fishes.add(new Fish(type, new Location(world, x, y, z)));
        }

        return fishes;
    }

}
