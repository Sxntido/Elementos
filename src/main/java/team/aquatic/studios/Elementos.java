package team.aquatic.studios;

import org.bukkit.plugin.java.JavaPlugin;

public final class Elementos extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new JoinElementListener(), this);

        this.getCommand("elementos").setExecutor(new MenuElementos(this));
        getServer().getPluginManager().registerEvents(new MenuElementos(this), this);

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new ElementsExpansion(this).register();
            getLogger().info("PlaceholderAPI encontrado. AcademyElementoExpansion registrada.");
        } else {
            getLogger().warning("PlaceholderAPI no encontrado. No se registró AcademyElementoExpansion.");
        }
    }

    @Override
    public void onDisable() {
    }
}
