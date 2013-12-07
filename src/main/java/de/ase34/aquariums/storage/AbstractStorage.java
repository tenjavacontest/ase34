package de.ase34.aquariums.storage;

import java.util.Set;

import de.ase34.aquariums.Aquarium;

public interface AbstractStorage {

    public Set<Aquarium> load();

    public void save(Set<Aquarium> aquariums);

}
