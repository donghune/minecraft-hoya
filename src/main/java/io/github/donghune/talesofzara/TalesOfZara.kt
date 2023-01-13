package io.github.donghune.talesofzara

import org.bukkit.Location
import org.bukkit.entity.Player
import java.util.*

object TalesOfZara {
    enum class Role {
        TURTLE, RABBIT
    }

    val playerRole = HashMap<UUID, Role>()
    val rabbitLiver = HashMap<UUID, LiverData>()
    var startLocation: Location? = null
    val gameTask = TalesOfZaraTask()

    fun setPlayerClass(player: Player, role: Role) {
        playerRole.put(player.uniqueId, role)
    }

    fun getPlayerRole(player: Player): Role? {
        return playerRole[player.uniqueId]
    }

    fun start() {
        gameTask.start()
    }

    fun stop() {
        gameTask.stop()
    }
}