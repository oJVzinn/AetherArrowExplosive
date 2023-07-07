package dev.ojvzinn.arrowexplosive;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    private float explosionForce = 0.0F;

    @Override
    public void onLoad() {
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        this.explosionForce = (float) getConfig().getDouble("force-explosion");

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onArrowShot(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile.getShooter() != null) {
            Player player = (Player) event.getEntity().getShooter();
            if (player.hasPermission("aetherarrow.explosive")) {
                projectile.getLocation().getWorld().createExplosion(projectile.getLocation(), this.explosionForce, true);
            }
        }
    }

}
