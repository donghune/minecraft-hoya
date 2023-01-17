package io.github.donghune.talesofzara

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class TalesOfZaraListener : Listener {
    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {

        if (event.player.inventory.itemInMainHand == null) {
            return
        }

        if (!event.player.inventory.itemInMainHand.hasItemMeta()) {
            return
        }

        if (!event.player.inventory.itemInMainHand.itemMeta.hasDisplayName()) {
            return
        }

        if (!(event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK)) {
            return
        }

        when (event.player.inventory.itemInMainHand.itemMeta.displayName) {
            TalesOfZaraItem.Bat().name -> {
                TalesOfZaraItem.Bat().action(event.player)
            }
            TalesOfZaraItem.FireBat().name -> {
                TalesOfZaraItem.FireBat().action(event.player)
            }
            TalesOfZaraItem.Explorer().name -> {
                TalesOfZaraItem.Explorer().action(event.player)
            }
            TalesOfZaraItem.Liver(event.player).name -> {
                TalesOfZaraItem.Liver(event.player).action(event.player)
            }
            TalesOfZaraItem.Jump().name -> {
                TalesOfZaraItem.Jump().action(event.player)
            }
            TalesOfZaraItem.Fascination().name -> {
                TalesOfZaraItem.Fascination().action(event.player)
            }
        }
    }
}