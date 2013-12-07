package de.ase34.auqariums.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import de.ase34.auqariums.Aquarium;
import de.ase34.auqariums.Fish;
import de.ase34.auqariums.Fish.FishType;

public class YAMLStorage implements AbstractStorage {

    private Configuration config;

    public YAMLStorage(Configuration config) {
        this.config = config;
    }

    @Override
    public Set<Aquarium> load() {
        Set<Aquarium> set = new HashSet<Aquarium>();

        for (Object obj : config.getList("aquariums")) {
            ConfigurationSection section = (ConfigurationSection) obj;

            Multiset<Fish> fishes = HashMultiset.create();
            for (String fish : section.getStringList("fishes")) {
                fishes.add(new Fish(FishType.valueOf(fish)));
            }

            World world = Bukkit.getWorld(section.getString("rootBlock.world"));
            double x = section.getDouble("rootBlock.x");
            double y = section.getDouble("rootBlock.y");
            double z = section.getDouble("rootBlock.z");

            Aquarium aquarium = new Aquarium(new Location(world, x, y, z), section.getString("owner"), fishes);
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
