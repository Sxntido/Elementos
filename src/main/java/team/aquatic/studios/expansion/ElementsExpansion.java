package team.aquatic.studios.expansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import team.aquatic.studios.tools.Utils;

import java.util.Arrays;
import java.util.List;

public class ElementsExpansion extends PlaceholderExpansion {

    private final Plugin plugin;
    private final List<String> permisos = Arrays.asList(
            "academy.elemento.hielo",
            "academy.elemento.luz",
            "academy.elemento.magma",
            "academy.elemento.oscuridad"
    );

    public ElementsExpansion(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "academy";
    }

    @Override
    public String getAuthor() {
        return "Sxntido";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player jugador, String identifier) {
        if (jugador == null) {
            return "";
        }

        if (identifier.equals("elemento")) {
            String simbolo = "";

            if (hasSpecificPermission(jugador, "academy.elemento.hielo")) {
                simbolo = Utils.translateColor(Utils.translateHexColorCodes("&#8ECAFF[❄]"));
            } else if (hasSpecificPermission(jugador, "academy.elemento.luz")) {
                simbolo = Utils.translateColor(Utils.translateHexColorCodes("&#F2FF7E[☀]"));
            } else if (hasSpecificPermission(jugador, "academy.elemento.magma")) {
                simbolo = Utils.translateColor(Utils.translateHexColorCodes("&#7F1A1A[\uD83D\uDD25]"));
            } else if (hasSpecificPermission(jugador, "academy.elemento.oscuridad")) {
                simbolo = Utils.translateColor(Utils.translateHexColorCodes("&#3B3B3B[☠]"));
            }

            if (simbolo.isEmpty()) {
                simbolo = Utils.translateColor(Utils.translateHexColorCodes("&#FB3E3E❌"));
            }

            return simbolo;
        }

        return null;
    }

    private boolean hasSpecificPermission(Player jugador, String permiso) {
        return jugador.hasPermission(permiso) && !jugador.isOp() && !jugador.hasPermission("*");
    }
}
