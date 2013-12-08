package de.ase34.aquariumdecorator;

import net.minecraft.server.v1_7_R1.Entity;
import net.minecraft.server.v1_7_R1.EntityPlayer;
import net.minecraft.server.v1_7_R1.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_7_R1.PacketPlayOutEntityVelocity;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class FishLocationUpdater implements Runnable {

    private Main plugin;

    public FishLocationUpdater(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (int i = 0; i < plugin.getFishes().size(); i++) {
                Fish fish = plugin.getFishes().get(i);

                if (fish.getEntity() == null || !fish.getEntity().isValid()) {
                    plugin.getFishes().remove(i);
                    i--;
                    continue;
                }

                Entity entity = ((CraftItem) fish.getEntity()).getHandle();

                EntityPlayer eplayer = ((CraftPlayer) player).getHandle();
                eplayer.playerConnection.sendPacket(new PacketPlayOutEntityVelocity(entity));
                eplayer.playerConnection.sendPacket(new PacketPlayOutEntityTeleport(entity));
            }
        }
    }
}
