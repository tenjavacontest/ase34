package de.ase34.aquariumdecorator;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftItem;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * Utility class to create a floating fish.
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
    private Location loc;
    private Item entity;

    public Fish(FishType type, Location loc) {
        Validate.notNull(type);
        Validate.notNull(loc);
        this.type = type;
        this.loc = loc;
    }

    public Fish(ItemStack stack, Location loc) throws IllegalArgumentException {
        Validate.isTrue(stack.getType() == Material.RAW_FISH, "The item must be a fish!");

        FishType type = FishType.getFishType(stack.getDurability());
        Validate.notNull(type, "There is no fish with durability " + stack.getDurability() + "!");

        Validate.notNull(loc);

        this.type = type;
        this.loc = loc;
    }

    public FishType getType() {
        return type;
    }

    public Location getLocation() {
        return loc;
    }

    /**
     * Creates this fish at the specified location.
     */
    public void createEntity() {
        ItemStack stack = new ItemStack(Material.RAW_FISH, 1, type.getData());
        FloatingItem floatingItem = new FloatingItem(loc, stack);

        CraftItem item = new CraftItem(((CraftServer) Bukkit.getServer()), floatingItem);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(floatingItem);
        this.entity = item;
    }

    public Item getEntity() {
        return entity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loc == null) ? 0 : loc.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        if (!(obj instanceof Fish)) {
            return false;
        }
        Fish other = (Fish) obj;
        if (loc == null) {
            if (other.loc != null) {
                return false;
            }
        } else if (!loc.equals(other.loc)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }
}
