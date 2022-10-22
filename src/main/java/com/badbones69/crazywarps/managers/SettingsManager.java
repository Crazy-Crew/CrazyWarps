package com.badbones69.crazywarps.managers;

import com.badbones69.crazywarps.CrazyWarps;
import com.badbones69.crazywarps.api.CrazyUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SettingsManager {

    CrazyUtils crazyUtils = CrazyWarps.getCrazyAPI();
    CrazyWarps crazyWarps = CrazyWarps.getCrazyWarps();

    FileConfiguration configFileConfiguration; //configFileConfiguration
    File configFile; //configFile

    FileConfiguration dataFileConfiguration; //dataFileConfiguration
    File dataFile; //dataFile

    public void setup(Plugin p) {

        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }

        configFile = new File(p.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                File en = new File(p.getDataFolder(), "/config.yml");
                InputStream E = getClass().getResourceAsStream("/config.yml");
                copyFile(E, en);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        configFileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        dataFile = new File(p.getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            try {
                File en = new File(p.getDataFolder(), "/data.yml");
                InputStream E = getClass().getResourceAsStream("/data.yml");
                copyFile(E, en);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dataFileConfiguration = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getData() {
        return dataFileConfiguration;
    }

    public void saveData() {
        try {
            dataFileConfiguration.save(dataFile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
        }
    }

    public void reloadData() {
        dataFileConfiguration = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getConfig() {
        return configFileConfiguration;
    }

    public void saveConfig() {
        try {
            configFileConfiguration.save(configFile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }

    public void reloadConfig() {
        configFileConfiguration = YamlConfiguration.loadConfiguration(configFile);
    }

    public PluginDescriptionFile getDesc() {
        return crazyWarps.getDescription();
    }

    public static void copyFile(InputStream inputStream, File file) throws Exception { // https://bukkit.org/threads/extracting-file-from-jar.16962/
        InputStream fileInputStream = inputStream;
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fileInputStream.read(buf)) != -1) {
                fileOutputStream.write(buf, 0, i);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
}
