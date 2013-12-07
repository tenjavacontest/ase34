package de.ase34.aquariums;

import org.apache.commons.lang.Validate;
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
        Validate.notNull(rootBlock);
        Validate.notNull(owner);
        this.rootBlock = rootBlock;
        this.owner = owner;
        this.fishes = HashMultiset.create();
    }

    public Aquarium(Location rootBlock, String owner, Multiset<Fish> fishes) {
        Validate.notNull(rootBlock);
        Validate.notNull(owner);
        Validate.notNull(fishes);
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

    /**
     * Spawns all not yet spawned fishes in the aquarium
     */
    public void populate() {
        for (Fish fish : fishes) {
            if (fish.getEntity() == null) {
                fish.createEntity(rootBlock);
            }
        }
    }

    /**
     * Removes all living fishes in the aquarium.
     */
    public void clear() {
        for (Fish fish : fishes) {
            if (fish.getEntity() != null) {
                fish.kill();
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fishes == null) ? 0 : fishes.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((rootBlock == null) ? 0 : rootBlock.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Aquarium)) {
            return false;
        }
        Aquarium other = (Aquarium) obj;
        if (fishes == null) {
            if (other.fishes != null) {
                return false;
            }
        } else if (!fishes.equals(other.fishes)) {
            return false;
        }
        if (owner == null) {
            if (other.owner != null) {
                return false;
            }
        } else if (!owner.equals(other.owner)) {
            return false;
        }
        if (rootBlock == null) {
            if (other.rootBlock != null) {
                return false;
            }
        } else if (!rootBlock.equals(other.rootBlock)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aquarium [rootBlock=" + rootBlock + ", owner=" + owner + ", fishes=" + fishes + "]";
    }

}
