package de.ase34.zombiewave;

import net.minecraft.server.v1_6_R3.EntityZombie;
import net.minecraft.server.v1_6_R3.World;

import org.bukkit.util.Vector;

public class Zombie extends EntityZombie {

    private Vector direction;

    public Zombie(Vector direction, World world) {
        super(world);
        this.direction = direction;
    }

    @Override
    public void l_() {
        this.motX = direction.getX();
        this.motY = direction.getY();
        this.motZ = direction.getZ();
    }

}
