package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ItemDBCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public ItemDBCommand(ToolsPro plugin) {
        super("itemdb", Message.CMD_ITEMDB_DESCRIPTION, "/itemdb");
        this.setPermission("toolspro.commands.itemdb");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (sender instanceof Player) {
                Item item = ((Player) sender).getInventory().getItemInHand();
                String m;
                //m = "&bThis item " + (this.plugin.isRepairable(item) ? "has &c" + item.getDamage() + "&b" + " points of damage" : "metadata is &c" + item.getDamage());
                m = this.plugin.isRepairable(item) ? Message.CMD_ITEMDB_REPAIRABLE.getText(item.getDamage()) : Message.CMD_ITEMDB_DATA.getText(item.getDamage());
                if (args.length >= 1) {
                    switch (args[0]) {
                        case "name":
                            //m = "&bThis item is named: &c" + item.getName();
                            m = Message.CMD_ITEMDB_NAMED.getText(item.getName());
                            break;
                        case "id":
                            //m = "&bThis item ID is: &c" + item.getId();
                            m = Message.CMD_ITEMDB_ID.getText(item.getName());
                            break;
                        case "durability":
                        case "dura":
                        case "metadata":
                        case "meta":
                            //m = "&bThis item " + (this.plugin.isRepairable(item) ? "has &c" + item.getDamage() + "&b" + " points of damage" : "metadata is &c" + item.getDamage());
                            m = this.plugin.isRepairable(item) ? Message.CMD_ITEMDB_REPAIRABLE.getText(item.getDamage()) : Message.CMD_ITEMDB_DATA.getText(item.getDamage());
                            break;
                    }
                }
                sender.sendMessage(TextFormat.colorize(m));
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aItemDB&7]", 'c');
            }
        }
        return true;
    }
}
