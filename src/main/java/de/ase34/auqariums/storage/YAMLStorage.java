package de.ase34.auqariums.storage;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import de.ase34.auqariums.Aquarium;
import de.ase34.auqariums.Fish;
import de.ase34.auqariums.Fish.FishType;

public class YAMLStorage implements AbstractStorage {

    private YamlConfiguration config;

    public YAMLStorage(YamlConfiguration config) {
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
        // TODO Auto-generated method stub

    }

}
