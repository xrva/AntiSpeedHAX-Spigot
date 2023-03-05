package revulose.revuac;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RevuAC extends JavaPlugin implements Listener {

    private final double MAX_SPEED = 0.4; // The maximum speed that a player can move

    @Override
    public void onEnable() {
        getLogger().info("RevuAC has been enabled!");
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("RevuAC has been disabled!");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double speed = player.getVelocity().length();

        if (speed > MAX_SPEED) {
            event.setCancelled(true);
            player.sendMessage("RevuAC: You have been caught using a speed hack!");
            player.kickPlayer("RevuAC: You have been caught using a speed hack!");
            for (Player admin : Bukkit.getOnlinePlayers()) {
                if (admin.hasPermission("revuac.alert")) {
                    admin.sendMessage("RevuAC: Player " + player.getName() + " has been caught using a speed hack!");
                }
            }
        }
    }
}