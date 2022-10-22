package com.badbones69.crazywarps.api;

import com.badbones69.crazywarps.CrazyWarps;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.Random;

public class TeleportingParticles {

    public CrazyWarps crazyWarps = CrazyWarps.getCrazyWarps();
    public CrazyUtils crazyUtils = CrazyWarps.getCrazyAPI();

    private void TeleportAway(Player player) {
        Location loc = player.getLocation();
        if (crazyWarps.getConfig().getBoolean("Settings.WarpExplosion")) {
            ParticleEffect.FLAME.display(0, 0, 0, 1, 200, player.getLocation().add(0, 1, 0), 100);
            ParticleEffect.EXPLOSION_HUGE.display(0, 0, 0, 0, 2, player.getLocation().add(0, 1, 0), 100);
            ParticleEffect.CLOUD.display((float) .4, (float) .5, (float) .4, 1, 30, player.getLocation().add(0, 1, 0), 100);
        }
        if (crazyUtils.getVersion() >= 191) {
            player.playSound(loc, Sound.valueOf("ENTITY_ENDERMEN_TELEPORT"), 1, 0);
        } else {
            player.playSound(loc, Sound.valueOf("ENDERMAN_TELEPORT"), 1, 0);
        }
    }

    private void Random(Player player) {
        Random number = new Random();
        int chance;
        for (int counter = 1; counter <= 1; counter++) {
            chance = 1 + number.nextInt(9);
            if (chance == 1) Sparkles(player);
            if (chance == 2) Smoke(player);
            if (chance == 3) Spell(player);
            if (chance == 4) Enchant(player);
            if (chance == 5) Village(player);
            if (chance == 6) Magic(player);
            if (chance == 7) Specks(player);
            if (chance == 8) Music(player);
            if (chance == 9) RainbowCloud(player);
        }
    }

    private void Sparkles(Player player) {
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(.6, 2, 0), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(.55, 2, .23), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(.45, 2, .45), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(.23, 2, .55), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(0, 2, .6), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(-.23, 2, .55), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(-.45, 2, .45), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(-.55, 2, .23), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(-.6, 2, 0), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(-.55, 2, -.23), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(-.45, 2, -.45), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(-.23, 2, -.55), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(0, 2, -.6), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(.23, 2, -.55), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(.45, 2, -.45), 100);
        ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, player.getLocation().add(.55, 2, -.23), 100);
    }

    private void Smoke(Player player) {
        ParticleEffect.SMOKE_LARGE.display
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(.6, 0, 0), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(.55, 0, .23), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(.45, 0, .45), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(.23, 0, .55), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(0, 0, .6), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(-.23, 0, .55), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(-.45, 0, .45), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(-.55, 0, .23), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(-.6, 0, 0), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(-.55, 0, -.23), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(-.45, 0, -.45), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(-.23, 0, -.55), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(0, 0, -.6), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(.23, 0, -.55), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(.45, 0, -.45), 100);
        ParticleEffect.SMOKE_LARGE.display(0, 0, 0, 0, 1, player.getLocation().add(.55, 0, -.23), 100);
    }

    private void Spell(Player player) {
        Random r = new Random();
        int color1 = r.nextInt(255);
        int color2 = r.nextInt(255);
        int color3 = r.nextInt(255);
        ParticleEffect.SPELL_MOB.display((float) .3, (float) 0, (float) .3, color1, 40, player.getLocation().add(0, 0, 0), 100);
        ParticleEffect.SPELL_MOB.display((float) .3, (float) 0, (float) .3, color2, 40, player.getLocation().add(0, 0, 0), 100);
        ParticleEffect.SPELL_MOB.display((float) .3, (float) 0, (float) .3, color3, 40, player.getLocation().add(0, 0, 0), 100);
    }

    private void Enchant(Player player) {
        ParticleEffect.ENCHANTMENT_TABLE.display(0, 0, 0, 5, 500, player.getLocation().add(0, 2, 0), 100);
    }

    private void Village(Player player) {
        ParticleEffect.VILLAGER_HAPPY.display((float) .4, (float) .4, (float) .4, 1, 25, player.getLocation().add(0, 1, 0), 100);
        ParticleEffect.VILLAGER_HAPPY.display((float) .4, (float) .4, (float) .4, 1, 25, player.getLocation().add(0, 1, 0), 100);
    }

    private void Magic(Player player) {
        ParticleEffect.CRIT_MAGIC.display((float) .4, (float) .4, (float) .4, 0, 60, player.getLocation().add(0, 1.2, 0), 100);
    }

    private void Specks(Player player) {
        ParticleEffect.TOWN_AURA.display((float) .4, (float) .5, (float) .4, 0, 500, player.getLocation().add(0, 1, 0), 100);
    }

    private void Music(Player player) {
        Random r = new Random();
        int color1 = r.nextInt(255);
        int color2 = r.nextInt(255);
        int color3 = r.nextInt(255);
        ParticleEffect.NOTE.display((float) .4, (float) .5, (float) .4, color1, 3, player.getLocation().add(0, .5, 0), 100);
        ParticleEffect.NOTE.display((float) .4, (float) .5, (float) .4, color2, 3, player.getLocation().add(0, .5, 0), 100);
        ParticleEffect.NOTE.display((float) .4, (float) .5, (float) .4, color3, 3, player.getLocation().add(0, .5, 0), 100);
    }

    private void RainbowCloud(Player player) {
        Random r = new Random();
        int color1 = r.nextInt(255);
        int color2 = r.nextInt(255);
        int color3 = r.nextInt(255);
        int color4 = r.nextInt(255);
        int color5 = r.nextInt(255);
        int color6 = r.nextInt(255);
        ParticleEffect.REDSTONE.display((float) .4, (float) .5, (float) .4, color1, 15, player.getLocation().add(0, 1, 0), 100);
        ParticleEffect.REDSTONE.display((float) .4, (float) .5, (float) .4, color2, 15, player.getLocation().add(0, 1, 0), 100);
        ParticleEffect.REDSTONE.display((float) .4, (float) .5, (float) .4, color3, 15, player.getLocation().add(0, 1, 0), 100);
        ParticleEffect.REDSTONE.display((float) .4, (float) .5, (float) .4, color4, 15, player.getLocation().add(0, 1, 0), 100);
        ParticleEffect.REDSTONE.display((float) .4, (float) .5, (float) .4, color5, 15, player.getLocation().add(0, 1, 0), 100);
        ParticleEffect.REDSTONE.display((float) .4, (float) .5, (float) .4, color6, 15, player.getLocation().add(0, 1, 0), 100);
    }
}