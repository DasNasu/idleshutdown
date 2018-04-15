package me.dasnasu.idleshutdown;

import java.io.IOException;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class IdleShutdown extends JavaPlugin{
	private static int counter;
	private static int timer;
	
	public void onEnable() {
		this.startTimer();
	}
	
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(cmd.getName().equals("rebootbukkit") && args.length == 0) {
			broadcast("The Server will be stopped in 15 Seconds.");
			try {
				Runtime.getRuntime().exec("cmd.exe /k \"start start_delayed.bat\"");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					timer++;
					if(timer >= 15) {
						timer = 0;
						Bukkit.shutdown();
					}
				}
			}, 20, 20);
			return true;
		}
		else if(cmd.getName().equals("rebootserver") && args.length == 0) {
			broadcast("The Server will be stopped in 15 Seconds.");
			try {
				Runtime.getRuntime().exec("cmd.exe /k \"shutdown -r -f -t 60\"");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					timer++;
					if(timer >= 15) {
						timer = 0;
						Bukkit.shutdown();
					}
				}
			}, 20, 20);
			return true;
		}
		return false;
	}
	
	public void startTimer() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				boolean players = isPlayerOnline();
				if(!players) {
					counter++;
					if(counter >= 300) {
						counter = 0;
						shutdownServer();
					}
				} else {
					counter = 0;
				}
			}
		}, 20, 20);
	}
	
	public static boolean isPlayerOnline() {
		Collection<? extends Player> players = Bukkit.getOnlinePlayers();
		if(players.size() != 0) {
			return true;
		}
		return false;
	}
	
	public void broadcast(String message) {
		for(Player players:Bukkit.getOnlinePlayers()) {
			System.out.println(message);
			players.sendMessage(ChatColor.RED+"[Server] "+message+"\n");
		}
	}
	
	public static void shutdownServer() {
		try {
			Runtime.getRuntime().exec("cmd.exe /k \"shutdown -s -f -t 60\"");
			Bukkit.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}