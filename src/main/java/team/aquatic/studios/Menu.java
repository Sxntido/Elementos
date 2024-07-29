package team.aquatic.studios;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import team.aquatic.studios.tools.SkullCreator;
import team.aquatic.studios.tools.Utils;

import java.util.*;

public class Menu implements Listener, CommandExecutor {

    private final Elementos plugin;
    private final Map<Integer, Runnable> slotActions = new HashMap<>();
    private final Map<Player, Boolean> haSeleccionadoElemento = new HashMap<>();

    public Menu(Elementos plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player jugador = (Player) sender;

            if (jugador.hasPermission("academy.elemento.hielo") ||
                    jugador.hasPermission("academy.elemento.luz") ||
                    jugador.hasPermission("academy.elemento.magma") ||
                    jugador.hasPermission("academy.elemento.oscuridad")) {
                jugador.sendMessage(Utils.translateColor("&cYa tienes un elemento seleccionado"));
                return true;
            }

            abrirMenu(jugador);
            return true;
        }
        return false;
    }

    public void abrirMenu(Player jugador) {
        Inventory inventario = Bukkit.createInventory(null, 27, Utils.translateColor("&8Elementos"));

        inventario.setItem(10, crearItemMenu(
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZhYWI1OGZhMDFmY2U5YWY0NjllZDc0N2FlZDgxMWQ3YmExOGM0NzZmNWE3ZjkwODhlMTI5YzMxYjQ1ZjMifX19",
                "&#8CBCFF&lHIELO - &#8CBCFF[&#8ECAFF❄&#90D7FF]",
                new String[]{
                        "&f",
                        "&fEn un reino ancestral, el Elemento Hielo era personificado",
                        "&fpor la diosa Glaciana, cuyo aliento congelaba al más valiente.",
                        "&fSus seguidores dominaban el frío, forjando fortalezas de hielo",
                        "&fy desatando tormentas de nieve.",
                        "&f",
                        "&a¡Selecciona este objeto para ingresar al elemento!"
                }
        ));
        slotActions.put(10, () -> ejecutarAccion(jugador, "academy.elemento.hielo", Sound.ENTITY_ENDER_EYE_DEATH));

        inventario.setItem(12, crearItemMenu(
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTc2ODJmZDE3YWM0NGI4NmE3MDVjYTA0YzkyNjU0ZTFlZDkxYTI0Zjg4MGFmMmUwOTU0Y2E3NjYyZjQ3NTU1MiJ9fX0=",
                "&#FFFE77&lLUZ - &#FFFE77[&#F2FF7E☀&#E5FF85]",
                new String[]{
                        "&f",
                        "&fEn un mundo de magia antigua, el Elemento Luz estaba",
                        "&fencarnado por la diosa Luminara, cuyo resplandor segaba",
                        "&fa la oscuridad. Sus seguidores manipulaban la luz sanando",
                        "&fcon su brillo y ahuyentando las sombras.",
                        "&f",
                        "&a¡Selecciona este objeto para ingresar al elemento!"
                }
        ));
        slotActions.put(12, () -> ejecutarAccion(jugador, "academy.elemento.luz", Sound.ITEM_TRIDENT_RETURN));

        inventario.setItem(14, crearItemMenu(
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzIxZDA5MzBiZDYxZmVhNGNiOTAyN2IwMGU5NGUxM2Q2MjAyOWM1MjRlYTBiMzI2MGM3NDc0NTdiYTFiY2ZhMSJ9fX0=",
                "&#7F2020&lMAGMA - &#7F2020[&#7F1A1A🔥&#7E1313]",
                new String[]{
                        "&f",
                        "&fEn un mundo ardiente reinado en sus profundidades por",
                        "&fel dios Pillow, el Elemento Magma forja con su aliento",
                        "&fabrasador rocas y minerales incandescentes. Sus seguidores",
                        "&ffluyen como ríos de fuego, surcando las entrañas de la",
                        "&ftierra con gran determinación.",
                        "&f",
                        "&a¡Selecciona este objeto para ingresar al elemento!"
                }
        ));
        slotActions.put(14, () -> ejecutarAccion(jugador, "academy.elemento.magma", Sound.ITEM_FIRECHARGE_USE));

        inventario.setItem(16, crearItemMenu(
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQyNmFiODg4Mjk4YWRmMWVmZmQ4MTFjODA3NGRlZjA5NzgwZTdkOWQxMmJhNGM3N2I3M2ZkYTk5ODJkZDBmZSJ9fX0=",
                "&#3B3B3B&lOSCURIDAD - &#3B3B3B[&#3B3B3B☠&#3A3A3A]",
                new String[]{
                        "&f",
                        "&fEn un reino envuelto en sombras, el Elemento Oscuridad",
                        "&fera personificado por la reina Nyx, cuya mirada sumía a los",
                        "&fintrépidos en el abismo. Sus seguidores dominaban las tinieblas",
                        "&ftejiendo ilusiones y convocando criaturas de la noche.",
                        "&f",
                        "&a¡Selecciona este objeto para ingresar al elemento!"
                }
        ));
        slotActions.put(16, () -> ejecutarAccion(jugador, "academy.elemento.oscuridad", Sound.ENTITY_WARDEN_LISTENING_ANGRY));

        jugador.openInventory(inventario);
        jugador.playSound(jugador.getLocation(), Sound.BLOCK_CHEST_OPEN, 2, 2);
        haSeleccionadoElemento.put(jugador, false);
    }

    private ItemStack crearItemMenu(String textura, String nombre, String[] lore) {
        ItemStack item = SkullCreator.itemWithBase64(SkullCreator.createSkull(), textura);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.translateHexColorCodes(Utils.translateColor(nombre)));

        List<String> loreTraducido = new ArrayList<>();
        for (String linea : lore) {
            loreTraducido.add(Utils.translateHexColorCodes(Utils.translateColor(linea)));
        }

        meta.setLore(loreTraducido);
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(Utils.translateColor("&8Elementos"))) return;
        event.setCancelled(true);
        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;

        Player jugador = (Player) event.getWhoClicked();

        if (haSeleccionadoElemento.getOrDefault(jugador, false)) return;

        int slot = event.getSlot();
        Runnable accion = slotActions.get(slot);
        if (accion != null) {
            accion.run();
        }
    }

    private void ejecutarAccion(Player jugador, String permiso, Sound sonido) {
        haSeleccionadoElemento.put(jugador, true);

        jugador.performCommand("dialogues mage");
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + jugador.getName() + " permission set " + permiso);
        jugador.playSound(jugador.getLocation(), sonido, 100, -5);
        jugador.closeInventory();
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player jugador = (Player) event.getPlayer();
        if (!haSeleccionadoElemento.containsKey(jugador)) return;
        if (!haSeleccionadoElemento.get(jugador)) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> abrirMenu(jugador), 1L);
        }
    }
}
