package io.github.donghune.talesofzara

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.time.Duration
import java.time.LocalTime
import java.util.*

class TalesOfZaraTask : GameTask(60 * 45) {
    private val bossBar = Bukkit.createBossBar("[별주부전] l 용왕의 생명 00분 00초 남음", BarColor.BLUE, BarStyle.SOLID)

    override fun onStart() {
        Bukkit.broadcastMessage("별주부전 이야기가 시작됩니다")

        // 기본템 지급
        TalesOfZara.playerRole.forEach { uuid, role ->
            val player: Player = Bukkit.getPlayer(uuid)
            when (role) {
                TalesOfZara.Role.RABBIT -> {
                    player.inventory.addItem(TalesOfZaraItem.FireBat().toItemStack())
                    player.inventory.addItem(TalesOfZaraItem.Liver(player).toItemStack())
                    player.inventory.addItem(TalesOfZaraItem.Jump().toItemStack())
                }
                TalesOfZara.Role.TURTLE -> {
                    player.inventory.addItem(TalesOfZaraItem.Bat().toItemStack())
                    player.inventory.addItem(TalesOfZaraItem.Explorer().toItemStack())
                    player.inventory.addItem(TalesOfZaraItem.Fascination().toItemStack())
                }
            }
        }

        // 시작 장소로 이동
        for (uuid in TalesOfZara.playerRole.keys) {
            Bukkit.getPlayer(uuid).teleport(TalesOfZara.startLocation)
        }
    }

    override fun onDuring(seconds: Int) {
        if (seconds % 20 * 60 * 10 == 0) {
            Bukkit.broadcastMessage("토끼들의 수면시간 입니다.")
            TalesOfZara.playerRole.forEach { uuid, role ->
                if (role == TalesOfZara.Role.RABBIT) {
                    val player: Player = Bukkit.getPlayer(uuid)
                    player.addPotionEffect(
                        PotionEffect(
                            PotionEffectType.SLOW,
                            20 * 60,
                            Int.MAX_VALUE,
                            false,
                            false
                        )
                    )
                }
            }
        }

        // 보스바
        for (uuid in TalesOfZara.playerRole.keys) {
            val player = Bukkit.getPlayer(uuid)
            bossBar.addPlayer(player)
            bossBar.title =
                "[별주부전] l 용왕의 생명 %02d분 %02d초 남음".format(
                    60 * 45 - seconds / 60,
                    (60 * 45 - seconds) % 60
                )
        }

        // 액션바
        TalesOfZara.playerRole.forEach { uuid, role ->
            val player: Player = Bukkit.getPlayer(uuid)
            if (role == TalesOfZara.Role.RABBIT) {
                if (TalesOfZara.rabbitLiver.containsKey(player.uniqueId)) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent("[간 소지 중]"))
                } else {
                    val hideTime = TalesOfZara.rabbitLiver[player.uniqueId]!!.hideTime
                    player.spigot().sendMessage(
                        ChatMessageType.ACTION_BAR,
                        TextComponent(
                            "[생존 시간 :%02d 분 %02d 초]".format(
                                Duration.between(hideTime.plusMinutes(5), LocalTime.now()).toMinutes(),
                                Duration.between(hideTime.plusMinutes(5), LocalTime.now()).seconds
                            )
                        )
                    )
                }
            }
        }

        // 토끼 간 체크
        TalesOfZara.playerRole.forEach { uuid, role ->
            val player: Player = Bukkit.getPlayer(uuid)
            if (role == TalesOfZara.Role.RABBIT) {
                if (TalesOfZara.rabbitLiver.containsKey(player.uniqueId)) {
                    if (!LocalTime.now().isBefore(TalesOfZara.rabbitLiver[uuid]!!.hideTime.plusMinutes(5))) {
                        TalesOfZara.playerRole.remove(uuid)
                        player.sendMessage("간을 빼놓고 5분동안 돌아다녀서 사망하셨습니다.")
                    }
                }
            }
        }
    }

    override fun onStop() {
        Bukkit.broadcastMessage("게임이 종료되었습니다.")
        TalesOfZara.playerRole.forEach { uuid, _ ->
            val player: Player = Bukkit.getPlayer(uuid)
            player.inventory.clear()
        }
    }
}