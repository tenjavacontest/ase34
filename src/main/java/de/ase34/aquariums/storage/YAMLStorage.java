package de.ase34.aquariums.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import de.ase34.aquariums.Aquarium;
import de.ase34.aquariums.Fish;
import de.ase34.aquariums.Fish.FishType;

public class YAMLStorage implements AbstractStorage {

    private Configuration config;

    public YAMLStorage(Configuration config) {
        this.config = config;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Aquarium> load() {
        Set<Aquarium> set = new HashSet<Aquarium>();

        for (Object obj : config.getList("aquariums")) {
            HashMap<String, Object> section = (HashMap<String, Object>) obj;
            System.out.println(section.toString());

            Multiset<Fish> fishes = HashMultiset.create();
            for (String fish : (List<String>) section.get("fishes")) {
                fishes.add(new Fish(FishType.valueOf(fish)));
            }

            HashMap<String, Object> location = (HashMap<String, Object>) section.get("rootBlock");
            World world = Bukkit.getWorld((String) location.get("world"));
            double x = (Double) location.get("x");
            double y = (Double) location.get("y");
            double z = (Double) location.get("z");

            Aquarium aquarium = new Aquarium(new Location(world, x, y, z), (String) section.get("owner"), fishes);
            set.add(aquarium);
        }

        return set;
    }

    @Override
    public void save(Set<Aquarium> aquariums) {
        List<HashMap<String, Object>> sections = new ArrayList<HashMap<String, Object>>();

        for (Aquarium aquarium : aquariums) {
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("owner", aquarium.getOwner());

            ArrayList<Object> fishes = new ArrayList<Object>();
            for (Fish fish : aquarium.getFishes()) {
                fishes.add(fish.getType().toString());
            }
            map.put("fishes", fishes);

            HashMap<String, Object> location = new HashMap<String, Object>();
            location.put("world", aquarium.getRootBlock().getWorld().getName());
            location.put("x", aquarium.getRootBlock().getX());
            location.put("y", aquarium.getRootBlock().getY());
            location.put("z", aquarium.getRootBlock().getZ());

            map.put("rootBlock", location);

            sections.add(map);
        }

        config.set("aquariums", sections);
    }

}
