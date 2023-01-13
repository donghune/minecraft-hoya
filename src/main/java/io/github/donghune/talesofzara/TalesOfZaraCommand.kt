package io.github.donghune.talesofzara

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TalesOfZaraCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as Player
        when (label) {
            "토끼" -> {
                TalesOfZara.setPlayerClass(player, TalesOfZara.Role.RABBIT)
                Bukkit.broadcastMessage(player.displayName + " 님의 직업을 토끼로 설정하였습니다.")
            }
            "거북이" -> {
                TalesOfZara.setPlayerClass(player, TalesOfZara.Role.TURTLE)
                Bukkit.broadcastMessage(player.displayName + " 님의 직업을 거북이로 설정하였습니다.")
            }
            "시작" -> TalesOfZara.start()
            "종료" -> TalesOfZara.stop()
            "시작설정" -> TalesOfZara.startLocation = player.location
            "아이템" -> when (args[0]) {
                "Bat" -> player.inventory.addItem(TalesOfZaraItem.Bat().toItemStack())
                "FireBat" -> player.inventory.addItem(TalesOfZaraItem.FireBat().toItemStack())
                "Explorer" -> player.inventory.addItem(TalesOfZaraItem.Explorer().toItemStack())
                "Liver" -> player.inventory.addItem(TalesOfZaraItem.Liver(player).toItemStack())
                "Jump" -> player.inventory.addItem(TalesOfZaraItem.Jump().toItemStack())
                "Fascination" -> player.inventory.addItem(TalesOfZaraItem.Fascination().toItemStack())
            }
            else -> return false
        }
        return true
    }
}