package de.ase34.aquariumdecorator;

import net.minecraft.server.v1_6_R3.EntityItem;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_6_R3.inventory.CraftItemStack;

public class FloatingItem extends EntityItem {

    public FloatingItem(Location loc, org.bukkit.inventory.ItemStack stack) {
        super(((CraftWorld) loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ(), CraftItemStack.asNMSCopy(stack));
    }

    @Override
    public void l_() {
        this.motY = 0;
        this.motX = 0;
        this.motZ = 0;
    }

}