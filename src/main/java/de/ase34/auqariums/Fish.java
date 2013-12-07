package de.ase34.auqariums;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a fish in an {@link Aquarium}.
 */
public class Fish {

    public enum FishType {
        FISH(0), SALOMON(1), CLOWNFISH(2), PUFFERFISH(3);

        private short data;

        private FishType(int data) {
            this.data = (short) data;
        }

        public short getData() {
            return data;
        }

        /**
         * Gets the {@link FishType} with the specified data.
         * 
         * @param data
         *            The data value
         * @return A {@link FishType}, or null if noone exists with the specified data.
         */
        public static FishType getFishType(short data) {
            for (FishType type : values()) {
                if (type.data == data) {
                    return type;
                }
            }
            return null;
        }
    }

    private FishType type;
    private Entity entity;

    public Fish(FishType type) {
        this.type = type;
    }

    public Fish(ItemStack stack) throws IllegalArgumentException {
        Validate.isTrue(stack.getType() == Material.RAW_FISH, "The item must be a fish!");

        FishType type = FishType.getFishType(stack.getDurability());
        Validate.notNull(type, "There is no fish with durability " + stack.getDurability() + "!");

        this.type = type;
    }

    public FishType getType() {
        return type;
    }

    public Entity getEntity() {
        return entity;
    }

    public void kill() {
        if (entity != null) {
            entity.remove();
        }
        entity = null;
    }

    public Entity createEntity(Location loc) {
        return loc.getWorld().dropItem(loc, new ItemStack(Material.RAW_FISH, 1, type.getData()));
    }
}
