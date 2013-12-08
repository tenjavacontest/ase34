package de.ase34.aquariumdecorator;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupListener implements Listener {

    private Main plugin;

    public PickupListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPickup(PlayerPickupItemEvent ev) {
        if (((CraftItem) ev.getItem()).getHandle() instanceof FloatingItem) {
            if (ev.getItem().getItemStack().getType() == Material.RAW_FISH) {
                if (!plugin.getPickupers().contains(ev.getPlayer().getName())) {
                    ev.setCancelled(true);
                }
            }
        }
    }

}
