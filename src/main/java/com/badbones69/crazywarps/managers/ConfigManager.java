package com.badbones69.crazywarps.managers;

import com.badbones69.crazywarps.CrazyWarps;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    CrazyWarps crazyWarps = CrazyWarps.getCrazyWarps();
    FileConfiguration configuration;

    public ConfigManager() {
        configuration = crazyWarps.getConfig();

        // Settings
        setUsePerWarpPermission(configuration.getBoolean("settings.per-permissions.warp"));
        setUsePerCategoryPermission(configuration.getBoolean("settings.per-permissions.category"));
        setWarpDelay(configuration.getInt("settings.timers.delay"));
        setWarpCooldown(configuration.getInt("settings.timers.cooldown"));

        // Messages
        setPrefix(configuration.getString("messages.prefix"));
        setWarpSuccessMessage(configuration.getString("messages.warp-success"));
        setWarpCanceledMessage(configuration.getString("messages.warp-canceled"));

        // Cosmetics
        setUsePerWarpParticles(configuration.getBoolean("cosmetics.independent-effects"));
        setUsePerWarpSounds(configuration.getBoolean("cosmetics.independent-sounds"));

        setGlobalParticle(configuration.getString("cosmetics.global-particles.effect"));
        setUseGlobalParticles(configuration.getBoolean("cosmetics.global-particles.enabled"));
        setUseGlobalSounds(configuration.getBoolean("cosmetics.global-sounds.enabled"));

        setGlobalXOffset(configuration.getDouble("cosmetics.global-particles.x-offset"));
        setGlobalYOffset(configuration.getDouble("cosmetics.global-particles.y-offset"));
        setGlobalZOffset(configuration.getDouble("cosmetics.global-particles.z-offset"));

        setGlobalSound(configuration.getString("cosmetics.global-sounds.sound"));
        setGlobalVolume(configuration.getDouble("cosmetics.global-sounds.volume"));
        setGlobalPitch(configuration.getDouble("cosmetics.global-sounds.pitch"));

    }

    // Settings
    private boolean isPerWarpPermission, isPerCategoryPermission;
    private int warpDelay, warpCooldown;

    // Messages
    private String prefix, warpSuccessMessage, warpCanceledMessage;

    // Cosmetics
    private boolean isPerWarpParticles, isPerWarpSounds, isGlobalParticles, isGlobalSounds;
    private String globalParticle, globalSound;
    private double globalXOffset, globalYOffset, globalZOffset, globalVolume, globalPitch;

    public boolean isPerWarpPermission() {
        return isPerWarpPermission;
    }

    public void setUsePerWarpPermission(boolean perWarpPermission) {
        isPerWarpPermission = perWarpPermission;
    }

    public boolean isPerCategoryPermission() {
        return isPerCategoryPermission;
    }

    public void setUsePerCategoryPermission(boolean perCategoryPermission) {
        isPerCategoryPermission = perCategoryPermission;
    }

    public int getWarpDelay() {
        return warpDelay;
    }

    public void setWarpDelay(int warpDelay) {
        this.warpDelay = warpDelay;
    }

    public int getWarpCooldown() {
        return warpCooldown;
    }

    public void setWarpCooldown(int warpCooldown) {
        this.warpCooldown = warpCooldown;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getWarpSuccessMessage() {
        return warpSuccessMessage;
    }

    public void setWarpSuccessMessage(String warpSuccessMessage) {
        this.warpSuccessMessage = warpSuccessMessage;
    }

    public String getWarpCanceledMessage() {
        return warpCanceledMessage;
    }

    public void setWarpCanceledMessage(String warpCanceledMessage) {
        this.warpCanceledMessage = warpCanceledMessage;
    }

    public boolean isPerWarpParticles() {
        return isPerWarpParticles;
    }

    public void setUsePerWarpParticles(boolean perWarpParticles) {
        isPerWarpParticles = perWarpParticles;
    }

    public boolean isPerWarpSounds() {
        return isPerWarpSounds;
    }

    public void setUsePerWarpSounds(boolean perWarpSounds) {
        isPerWarpSounds = perWarpSounds;
    }

    public boolean isGlobalParticles() {
        return isGlobalParticles;
    }

    public void setUseGlobalParticles(boolean globalParticles) {
        isGlobalParticles = globalParticles;
    }

    public boolean isGlobalSounds() {
        return isGlobalSounds;
    }

    public void setUseGlobalSounds(boolean globalSounds) {
        isGlobalSounds = globalSounds;
    }

    public String getGlobalParticle() {
        return globalParticle;
    }

    public void setGlobalParticle(String globalParticle) {
        this.globalParticle = globalParticle;
    }

    public String getGlobalSound() {
        return globalSound;
    }

    public void setGlobalSound(String globalSound) {
        this.globalSound = globalSound;
    }

    public double getGlobalXOffset() {
        return globalXOffset;
    }

    public void setGlobalXOffset(double globalXOffset) {
        this.globalXOffset = globalXOffset;
    }

    public double getGlobalYOffset() {
        return globalYOffset;
    }

    public void setGlobalYOffset(double globalYOffset) {
        this.globalYOffset = globalYOffset;
    }

    public double getGlobalZOffset() {
        return globalZOffset;
    }

    public void setGlobalZOffset(double globalZOffset) {
        this.globalZOffset = globalZOffset;
    }

    public double getGlobalVolume() {
        return globalVolume;
    }

    public void setGlobalVolume(double globalVolume) {
        this.globalVolume = globalVolume;
    }

    public double getGlobalPitch() {
        return globalPitch;
    }

    public void setGlobalPitch(double globalPitch) {
        this.globalPitch = globalPitch;
    }
}
