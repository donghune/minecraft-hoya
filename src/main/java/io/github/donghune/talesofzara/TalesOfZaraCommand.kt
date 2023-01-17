package io.github.donghune.talesofzara

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TalesOfZaraCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("toz, talesofzara")
            sender.sendMessage("토끼 (rabbit)")
            sender.sendMessage("거북이 (turtle)")
            sender.sendMessage("시작 (start)")
            sender.sendMessage("종료 (stop)")
            sender.sendMessage("시작설정 (setStartLocation)")
            sender.sendMessage("아이템 (item) Bat")
            sender.sendMessage("아이템 (item) FireBat")
            sender.sendMessage("아이템 (item) Explorer")
            sender.sendMessage("아이템 (item) Liver")
            sender.sendMessage("아이템 (item) Jump")
            sender.sendMessage("아이템 (item) Fascination")
            return false
        }

        when (args[0]) {
            "토끼", "rabbit" -> {
                val player = sender as Player
                TalesOfZara.setPlayerClass(player, TalesOfZara.Role.RABBIT)
                Bukkit.broadcastMessage(player.displayName + " 님의 직업을 토끼로 설정하였습니다.")
            }
            "거북이", "turtle" -> {
                val player = sender as Player
                TalesOfZara.setPlayerClass(player, TalesOfZara.Role.TURTLE)
                Bukkit.broadcastMessage(player.displayName + " 님의 직업을 거북이로 설정하였습니다.")
            }
            "시작", "start" -> TalesOfZara.start()
            "종료", "stop" -> TalesOfZara.stop()
            "시작설정", "setStartLocation" -> {
                val player = sender as Player
                TalesOfZara.startLocation = player.location
            }
            "아이템", "item" -> {
                val player = sender as Player
                when (args[1]) {
                    "Bat" -> player.inventory.addItem(TalesOfZaraItem.Bat().toItemStack())
                    "FireBat" -> player.inventory.addItem(TalesOfZaraItem.FireBat().toItemStack())
                    "Explorer" -> player.inventory.addItem(TalesOfZaraItem.Explorer().toItemStack())
                    "Liver" -> player.inventory.addItem(TalesOfZaraItem.Liver(player).toItemStack())
                    "Jump" -> player.inventory.addItem(TalesOfZaraItem.Jump().toItemStack())
                    "Fascination" -> player.inventory.addItem(TalesOfZaraItem.Fascination().toItemStack())
                }
            }
            else -> return false
        }
        return true
    }
}