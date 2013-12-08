package de.ase34.aquariumdecorator.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.ase34.aquariumdecorator.Fish;
import de.ase34.aquariumdecorator.Main;

public class PlaceCommand implements CommandExecutor {

    private Main plugin;

    public PlaceCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can place fishes!");
            return true;
        }

        Player player = (Player) sender;

        ItemStack itemInHand = player.getInventory().getItemInHand();
        if (itemInHand.getType() != Material.RAW_FISH) {
            sender.sendMessage(ChatColor.RED + "Only fishes can be placed!");
            return true;
        }

        Location loc = player.getLocation().add(0, 0.1, 0);
        if (args.length > 0) {
            loc = loc.add(0, Double.parseDouble(args[0]), 0);
        }

        if (loc.getBlock().getType() != Material.STATIONARY_WATER) {
            sender.sendMessage(ChatColor.RED + "Fishes need to be placed in water!");
            return true;
        }

        Fish fish = new Fish(itemInHand, loc);
        plugin.getFishes().add(fish);
        fish.createEntity();
        itemInHand.setAmount(itemInHand.getAmount() - 1);

        sender.sendMessage(ChatColor.YELLOW + "Placed a fish!");
        return true;
    }

}
