package io.github.donghune.talesofzara

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class TalesOfZaraItem {
    class Bat : ActionItem(
        name = "방망이",
        description = "거북이가 토끼를 잡을 때 사용하는 아이템",
        icon = Material.DIAMOND_SWORD
    )

    class FireBat : ActionItem(
        name = "불방망이",
        description = "토끼가 거북이를 죽일때 사용하는 검",
        icon = Material.IRON_SWORD
    )

    class Explorer : ActionItem(
        name = "탐색기",
        description = "토끼간 간을 숨긴 위치를 폭죽으로 알려준다 (만약 간을 토끼가 소지시 토끼가 발광 효과(10초)) [쿨타임 : 5분]",
        icon = Material.DIAMOND
    ) {
        private var potionEffect = PotionEffect(PotionEffectType.GLOWING, 20 * 10, 1, false, false)
        override fun action(player: Player) {
            TalesOfZara.playerRole.forEach { uuid: UUID, role: TalesOfZara.Role ->
                if (role != TalesOfZara.Role.RABBIT) {
                    return@forEach
                }
                if (TalesOfZara.rabbitLiver.containsKey(uuid)) {
                    Bukkit.getPlayer(uuid).addPotionEffect(potionEffect)
                } else {
                    val liverData = TalesOfZara.rabbitLiver[uuid]
                    val firework = liverData!!.location.world.spawn<Firework>(liverData.location, Firework::class.java)
                    val fireworkMeta = firework.fireworkMeta
                    fireworkMeta.power = 3
                    firework.fireworkMeta = fireworkMeta
                }
            }
        }
    }

    class Liver(player: Player) : ActionItem(
        name = "${player.displayName} 간",
        description = "토끼 간은 각자의 “마크닉네임 간” 이런 식으로 표시",
        icon = Material.GOLD_INGOT
    ) {
        override fun action(player: Player) {
            val block = player.getTargetBlock(null, 3)
            if (block == null || block.type == Material.AIR) {
                return
            }
            player.itemOnCursor = null
            TalesOfZara.rabbitLiver.put(player.uniqueId, LiverData(block.location))
            player.sendMessage("간을 숨겼습니다.")
        }
    }

    class Jump : ActionItem(
        name = "점프",
        description = "토끼 아이템 우클릭시 점프강화 및 신속 (지속 15초) [쿨타임: 1분]",
        icon = Material.IRON_INGOT
    ) {
        private var jumpPotionEffect = PotionEffect(PotionEffectType.JUMP, 20 * 15, 1, false, false)
        private var speedPotionEffect = PotionEffect(PotionEffectType.SPEED, 20 * 15, 0, false, false)

        override fun action(player: Player) {
            player.addPotionEffect(jumpPotionEffect)
            player.addPotionEffect(speedPotionEffect)
        }
    }

    class Fascination : ActionItem(
        name = "매혹",
        description = "바로보는 방향에 사람이 맞을 경우 멈춤 (거리 10칸, 3초간 멈춤) [쿨타임 : 1분]",
        icon = Material.COAL
    ) {
        override fun action(player: Player) {
        }
    }
}