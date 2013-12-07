package de.ase34.auqariums.storage;

import java.util.Set;

import de.ase34.auqariums.Aquarium;

public interface AbstractStorage {

    public Set<Aquarium> load();

    public void save(Set<Aquarium> aquariums);

}
