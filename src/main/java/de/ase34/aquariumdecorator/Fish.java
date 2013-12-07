package de.ase34.aquariumdecorator;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_6_R3.CraftServer;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftItem;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a floating fish in an aquarium.
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
     * Creates and returns this fish at the specified location.
     * 
     * @return The spawned item.
     */
    public Entity createEntity() {
        ItemStack stack = new ItemStack(Material.RAW_FISH, 1, type.getData());
        FloatingItem floatingItem = new FloatingItem(loc, stack);

        CraftItem item = new CraftItem(((CraftServer) Bukkit.getServer()), floatingItem);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(floatingItem);
        return item;
    }
}
