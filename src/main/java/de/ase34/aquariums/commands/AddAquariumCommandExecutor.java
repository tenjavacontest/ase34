package de.ase34.aquariums.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ase34.aquariums.Aquarium;
import de.ase34.aquariums.Main;

public class AddAquariumCommandExecutor extends AquariumCommandExecutor {

    public AddAquariumCommandExecutor(Main plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You have to be a player in order to create a aquarium!");
            return true;
        }

        Player player = (Player) sender;
        Block block = player.getLocation().getBlock();

        if (block.getType() != Material.WATER) {
            player.sendMessage(ChatColor.RED + "Your feets need to be in water!");
            return true;
        }

        Aquarium aquarium = new Aquarium(player.getLocation(), player.getName());
        plugin.getAquariums().add(aquarium);
        player.sendMessage(ChatColor.YELLOW + "You successfully created a new aquarium!");
        return true;
    }

}
