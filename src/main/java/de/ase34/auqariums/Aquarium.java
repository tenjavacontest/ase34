package de.ase34.auqariums;

import org.bukkit.Location;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Represents an area in the world filled with water that represents an aquarium.
 */
public class Aquarium {

    private Location rootBlock;
    private String owner;
    private Multiset<Fish> fishes;

    public Aquarium(Location rootBlock, String owner) {
        this.rootBlock = rootBlock;
        this.owner = owner;
        this.fishes = HashMultiset.create();
    }

    public Aquarium(Location rootBlock, String owner, Multiset<Fish> fishes) {
        this.rootBlock = rootBlock;
        this.owner = owner;
        this.fishes = fishes;
    }

    public Location getRootBlock() {
        return rootBlock;
    }

    public String getOwner() {
        return owner;
    }

    public Multiset<Fish> getFishes() {
        return fishes;
    }

}
