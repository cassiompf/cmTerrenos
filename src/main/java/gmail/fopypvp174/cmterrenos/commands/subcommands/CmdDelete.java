package gmail.fopypvp174.cmterrenos.commands.subcommands;

import gmail.fopypvp174.cmterrenos.CmTerrenos;
import gmail.fopypvp174.cmterrenos.entities.HouseEntity;
import org.bukkit.command.CommandSender;

public class CmdDelete extends SubCommand {

    public CmdDelete(CmTerrenos cmTerrenos) {
        super(cmTerrenos);
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission("terreno.delete")) {
            sender.sendMessage(getCmTerrenos().getFileConfig().getMessage("Perm_Staff"));
            return;
        }

        if (args.length != 1) {
            sender.sendMessage(getCmTerrenos().getFileConfig().getMessage("Comando_Desconhecido"));
            return;
        }

        HouseEntity house = getCmTerrenos().getHouseCache().getHouse(args[0]);
        if (house == null) {
            sender.sendMessage(getCmTerrenos().getFileConfig().getMessage("Player_Sem_Terreno").replace("%p", args[1]));
            return;
        }

        getCmTerrenos().getHouseCache().removeTerrain(house.getDono());
        new Thread(() -> getCmTerrenos().getDatabaseDAO().deleteTerrain(house)).start();
    }

    @Override
    public String name() {
        return "deletar";
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[]{"delete", "del"};
    }
}
