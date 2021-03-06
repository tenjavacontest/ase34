package de.ase34.aquariumdecorator;

import net.minecraft.server.v1_7_R1.EntityItem;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R1.inventory.CraftItemStack;

/**
 * Extends the {@link EntityItem} class, but the item does not move.
 */
public class FloatingItem extends EntityItem {

    public FloatingItem(Location loc, org.bukkit.inventory.ItemStack stack) {
        super(((CraftWorld) loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ(), CraftItemStack.asNMSCopy(stack));
    }

    @Override
    public void h() {
        // Movement
        this.motY = 0;
        this.motX = 0;
        this.motZ = 0;
    }

}