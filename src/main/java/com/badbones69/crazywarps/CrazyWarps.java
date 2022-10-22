package com.badbones69.crazywarps;

import com.badbones69.crazywarps.api.CrazyUtils;
import com.badbones69.crazywarps.managers.SettingsManager;
import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import com.jeff_media.updatechecker.UserAgentBuilder;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class CrazyWarps extends JavaPlugin {

    private static CrazyWarps crazyWarps;
    private static CrazyUtils crazyUtils;
    private static SettingsManager settings;

    private static final String resourceID = "12696";

    @Override
    public void onEnable() {
        settings = new SettingsManager();
        settings.setup(this);

        Metrics metrics = new Metrics(this, 12696);
        updateCheck();
    }

    public static CrazyUtils getCrazyAPI() {
        return crazyUtils;
    }

    public static CrazyWarps getCrazyWarps() {
        return crazyWarps;
    }

    public static SettingsManager getSettingsManager() {
        return settings;
    }

    public void updateCheck() {
        new UpdateChecker(this, UpdateCheckSource.SPIGOT, resourceID)
                .setDonationLink("https://opencollective.com/crazycrew")
                .setDownloadLink("https://www.spigotmc.org/resources/category-warps.12696/")
                .setNotifyByPermissionOnJoin("crazywarps.update")
                .setUserAgent(new UserAgentBuilder().addPluginNameAndVersion())
                .checkEveryXHours(24)
                .checkNow();
    }
}