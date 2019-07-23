package gmail.fopypvp174.cmterrenos.commands.subcommands;

import gmail.fopypvp174.cmterrenos.CmTerrenos;
import gmail.fopypvp174.cmterrenos.entities.HouseEntity;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Set;

public class CmdFriend extends SubCommand {

    public CmdFriend(CmTerrenos cmTerrenos) {
        super(cmTerrenos);
    }

    @Override
    public void onCommand(CommandSender sender, ArrayList<String> args) {
        if (args.size() != 2) {
            sender.sendMessage(getCmTerrenos().getFileConfig().getMessage("Comando_Desconhecido"));
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(super.getCmTerrenos().getFileConfig().getMessage("Somente_Jogador"));
            return;
        }
        Player player = Bukkit.getPlayer(sender.getName());

        HouseEntity house = getCmTerrenos().getHouseCache().getHouse(player.getName());

        if (house == null) {
            player.sendMessage(getCmTerrenos().getFileConfig().getMessage("Nao_Tem_Terreno"));
            return;
        }
        Set<String> friends = house.getFriends();
        if (args.get(0).equalsIgnoreCase("add") ||
                args.get(0).equalsIgnoreCase("adicionar")) {
            if (friends.add(args.get(1))) {
                player.sendMessage(getCmTerrenos().getFileConfig().getMessage("Amigo_Adicionado").replace("%p", args.get(1)));
            } else {
                player.sendMessage(getCmTerrenos().getFileConfig().getMessage("Amigo_Ja_Adicionado").replace("%p", args.get(1)));
            }
        } else if (args.get(0).equalsIgnoreCase("rem") ||
                args.get(0).equalsIgnoreCase("remover")) {
            if (friends.remove(args.get(1))) {
                player.sendMessage(getCmTerrenos().getFileConfig().getMessage("Amigo_Removido").replace("%p", args.get(1)));
            } else {
                player.sendMessage(getCmTerrenos().getFileConfig().getMessage("Amigo_Ja_Removido").replace("%p", args.get(1)));
            }
        } else {
            sender.sendMessage(getCmTerrenos().getFileConfig().getMessage("Comando_Desconhecido"));
            return;
        }
        house.setFriends(friends);
    }

    @Override
    public String name() {
        return "friend";
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[]{"amigos", "amigo"};
    }
}
