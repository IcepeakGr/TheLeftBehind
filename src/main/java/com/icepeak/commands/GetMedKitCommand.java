package com.icepeak.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import com.icepeak.items.health.MedKit;

@SuppressWarnings("unused")
public class GetMedKitCommand implements TabExecutor {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        
        if(args.length == 1) {
            List<String> suggestions = new ArrayList<>();
            suggestions.add("1");
            suggestions.add("5");
            suggestions.add("16");
            return StringUtil.copyPartialMatches(args[0], suggestions, new ArrayList<>());
        }

        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       if(!(sender instanceof Player)) {
           sender.sendMessage("This command can only be used by players.");
           return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("theleftbehind.user.medkit")) {
            player.sendMessage("You do not have permission to use this command.");
            return true;
        }

        int amount = 1;

        // Check if an argument for more medkits is provided
        if (args.length > 0) {
            try {
                amount = Integer.parseInt(args[0]);
                if (amount < 1 || amount > 64) {
                    player.sendMessage("§c[!] Please specify a realistic amount between 1 and 64.");
                    return true;
                }
            } catch (NumberFormatException e) {
                player.sendMessage("§c[!] Invalid amount entered. Defaulting to 1 Medkit.");
            }
        }

        player.getInventory().addItem(new MedKit().create(amount));
        player.sendMessage("You have received " + amount + " medkits.");
        return true;
    }

}
