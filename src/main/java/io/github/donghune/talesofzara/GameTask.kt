package io.github.donghune.talesofzara

import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

abstract class GameTask(
    private val playTime: Int,
) {
    abstract fun onStart()
    abstract fun onDuring(seconds: Int)
    abstract fun onStop()

    private var bukkitTask: BukkitTask? = null
    private var time = 0

    fun start() {
        bukkitTask = Bukkit.getScheduler().runTaskTimer(
            /* plugin = */ TalesOfZaraPlugin.instance,
            /* task = */ {
                when (time) {
                    0 -> onStart()
                    playTime -> {
                        onStop()
                        bukkitTask?.cancel()
                    }
                    else -> onDuring(time)
                }
                time += 1
            },
            /* delay = */ 0L,
            /* period = */ 20L
        )
    }

    fun stop() {
        bukkitTask?.cancel()
        bukkitTask = null
    }
}