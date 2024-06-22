package team.aquatic.studios;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinElementListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.isOp()) {
            if (player.hasPermission("academy.elemento.hielo")) {
                playSound(player, Sound.ENTITY_ENDER_EYE_DEATH);
            } else if (player.hasPermission("academy.elemento.luz")) {
                playSound(player, Sound.ITEM_TRIDENT_RETURN);
            } else if (player.hasPermission("academy.elemento.magma")) {
                playSound(player, Sound.ITEM_FIRECHARGE_USE);
            } else if (player.hasPermission("academy.elemento.oscuridad")) {
                playSound(player, Sound.ENTITY_WARDEN_LISTENING_ANGRY);
            }
        }
    }

    private void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 100, -5);
    }
}
