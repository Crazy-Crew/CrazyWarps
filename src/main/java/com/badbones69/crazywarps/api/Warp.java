package com.badbones69.crazywarps.api;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;

public class Warp {

    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
        this.volume = 1.0;
        this.pitch = 1.0;
        this.cooldown = 0;
        this.delay = 0;
        this.sound = Sound.ENTITY_ENDERMAN_TELEPORT;
        this.particle = Particle.CLOUD;
        this.xOffset = 0.0;
        this.yOffset = 0.0;
        this.zOffset = 0.0;
    }

    public Warp(Location location, String name, String permission, Particle particle, Sound sound, Category category, Integer cooldown, Integer delay, double xOffset, double yOffset, double zOffset, double volume, double pitch) {
        this.location = location;
        this.name = name;
        this.permission = permission;
        this.particle = particle;
        this.sound = sound;
        this.category = category;
        this.cooldown = cooldown;
        this.delay = delay;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.volume = volume;
        this.pitch = pitch;
    }

    private Location location;
    private Boolean hasParticle, hasSound, isCategorized;
    private String name, permission;
    private Particle particle;
    private Sound sound;
    private Category category;
    private Integer cooldown, delay;

    private double xOffset, yOffset, zOffset, volume, pitch;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Particle getParticle() {
        return particle;
    }

    public void setParticle(Particle particle) {
        this.particle = particle;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getHasParticle() {
        return hasParticle;
    }

    public void setHasParticle(Boolean hasParticle) {
        this.hasParticle = hasParticle;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }

    public double getzOffset() {
        return zOffset;
    }

    public void setzOffset(double zOffset) {
        this.zOffset = zOffset;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public Boolean getHasSound() {
        return hasSound;
    }

    public void setHasSound(Boolean hasSound) {
        this.hasSound = hasSound;
    }

    public Boolean getCategorized() {
        return isCategorized;
    }

    public void setCategorized(Boolean categorized) {
        isCategorized = categorized;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

}
