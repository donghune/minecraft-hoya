package io.github.donghune.talesofzara

import org.bukkit.Location
import java.time.LocalTime

data class LiverData(
    val location: Location,
) {
    val hideTime: LocalTime = LocalTime.now()
}