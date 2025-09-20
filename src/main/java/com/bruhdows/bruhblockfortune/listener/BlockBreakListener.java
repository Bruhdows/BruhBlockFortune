package com.bruhdows.bruhblockfortune.listener;

import com.bruhdows.bruhblockfortune.BruhBlockFortune;
import com.bruhdows.bruhblockfortune.object.Exp;
import lombok.RequiredArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class BlockBreakListener implements Listener {

    private final BruhBlockFortune plugin;
    private static final String PLAYER_PLACED_KEY = "player_placed";

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        event.getBlock().setMetadata(PLAYER_PLACED_KEY, new FixedMetadataValue(plugin, true));
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;

        Material blockType = event.getBlock().getType();
        ItemStack tool = player.getInventory().getItemInMainHand();

        boolean isPlayerPlaced = event.getBlock().hasMetadata(PLAYER_PLACED_KEY);

        handleExpDrop(event, blockType, isPlayerPlaced);

        if (plugin.getPluginConfig().getFortuneBlocks().contains(blockType) && !isPlayerPlaced) {
            handleFortuneMultiplier(event, tool, blockType);
        }
    }

    private void handleFortuneMultiplier(BlockBreakEvent event, ItemStack tool, Material blockType) {
        if (tool.getType() == Material.AIR || !tool.hasItemMeta()) return;
        if (!tool.getItemMeta().hasEnchant(Enchantment.FORTUNE)) return;

        int fortuneLevel = tool.getItemMeta().getEnchantLevel(Enchantment.FORTUNE);
        int multiplier = calculateFortuneMultiplier(fortuneLevel);

        if (multiplier > 1) {
            event.setDropItems(false);
            ItemStack drop = new ItemStack(blockType, multiplier);

            Location dropLocation = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
            event.getBlock().getWorld().dropItemNaturally(dropLocation, drop);
        }
    }

    private int calculateFortuneMultiplier(int fortuneLevel) {
        return 1 + ThreadLocalRandom.current().nextInt(fortuneLevel + 1);
    }

    private void handleExpDrop(BlockBreakEvent event, Material blockType, boolean isPlayerPlaced) {
        if (isPlayerPlaced) return;

        Exp exp = plugin.getPluginConfig().getExpBlocks().get(blockType);
        if (exp == null) return;

        int expAmount = calculateExpAmount(exp.getMin(), exp.getMax());
        if (expAmount > 0) {
            if (plugin.getPluginConfig().isXpTelekenisis()) {
                Player player = event.getPlayer();
                player.giveExp(expAmount);
            } else {
                Location expLocation = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
                ExperienceOrb expOrb = expLocation.getWorld().spawn(expLocation, ExperienceOrb.class);
                expOrb.setExperience(expAmount);
            }
        }
    }

    private int calculateExpAmount(int min, int max) {
        if (min >= max) return min;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
