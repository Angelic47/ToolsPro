package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

/**
 * Created by Pub4Game on 04.02.2016.
 */
public class GetPosCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public GetPosCommand(ToolsPro plugin) {
        super("getpos", Message.CMD_GETPOS_DESCRIPTION, Message.CMD_GETPOS_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.getpos");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.getpos.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p != null) {
                        sender.sendMessage(TextFormat.AQUA + p.getDisplayName() + TextFormat.GREEN +  Message.CMD_GETPOS_PLAYER + TextFormat.BLUE + p.getLevel().getName() + "\n" + TextFormat.YELLOW + Message.CMD_GETPOS_COORDINATES + TextFormat.GREEN + "X: " + p.getFloorX() + ", " + "Y: " + p.getFloorY() + ", " + "Z: " + p.getFloorZ());
                    } else {
                        Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aGetPos&7]", 'c');
                    }
                } else {
                    sender.sendMessage(Message.YOU_DONT_HAVE_PERMISSION.getText('c'));
                }
            } else {
                if (sender instanceof Player) {
                    sender.sendMessage(TextFormat.GREEN + Message.CMD_GETPOS_SENDER + TextFormat.BLUE + ((Player) sender).getLevel().getName() + "\n" + TextFormat.YELLOW + Message.CMD_GETPOS_COORDINATES +  TextFormat.GREEN + "X: " + ((Player) sender).getFloorX() + ", " + "Y: " + ((Player) sender).getFloorY() + ", " + "Z: " + ((Player) sender).getFloorZ());
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aGetPos&7]", 'c');
                }
            }
        }
        return true;
    }
}
