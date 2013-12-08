package de.ase34.aquariumdecorator.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ase34.aquariumdecorator.Main;

public class PickupCommand implements CommandExecutor {

    private Main plugin;

    public PickupCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You won't need this!");
            return true;
        }

        String name = ((Player) sender).getName();
        if (plugin.getPickupers().contains(name)) {
            plugin.getPickupers().remove(name);

            sender.sendMessage(ChatColor.YELLOW + "You are now unable to pickup placed fishes!");
            return true;
        } else {
            plugin.getPickupers().add(name);

            sender.sendMessage(ChatColor.YELLOW + "You can now pickup placed fishes! Use '/pickup' again to disable.");
            return true;
        }
    }

}
