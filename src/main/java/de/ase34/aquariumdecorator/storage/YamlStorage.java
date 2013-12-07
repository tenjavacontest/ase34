package de.ase34.aquariumdecorator.storage;

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
        // TODO Auto-generated method stub

    }

    @Override
    public List<Fish> load() {
        // TODO Auto-generated method stub
        return null;
    }

}
