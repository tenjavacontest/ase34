package de.ase34.aquariumdecorator;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private List<Fish> fishes;

    @Override
    public void onDisable() {
        // TODO Auto-generated method stub
        super.onDisable();
    }

    @Override
    public void onEnable() {
        // TODO Auto-generated method stub
        super.onEnable();
    }

    public List<Fish> getFishes() {
        return fishes;
    }

}
