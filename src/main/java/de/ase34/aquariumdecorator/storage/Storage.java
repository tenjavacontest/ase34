package de.ase34.aquariumdecorator.storage;

import java.util.List;

import de.ase34.aquariumdecorator.Fish;

public interface Storage {

    public void save(List<Fish> fishes);

    public List<Fish> load();

}
