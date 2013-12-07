package de.ase34.aquariumdecorator;

import net.minecraft.server.v1_6_R3.Entity;
import net.minecraft.server.v1_6_R3.EntityPlayer;
import net.minecraft.server.v1_6_R3.Packet28EntityVelocity;
import net.minecraft.server.v1_6_R3.Packet34EntityTeleport;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class FishLocationUpdater implements Runnable {

    private Main plugin;

    public FishLocationUpdater(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (Fish fish : plugin.getFishes()) {
                Entity entity = ((CraftItem) fish.getEntity()).getHandle();

                EntityPlayer eplayer = ((CraftPlayer) player).getHandle();
                eplayer.playerConnection.sendPacket(new Packet28EntityVelocity(entity));
                eplayer.playerConnection.sendPacket(new Packet34EntityTeleport(entity));
            }
        }
    }

}
