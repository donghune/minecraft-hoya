package io.github.donghune.talesofzara

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class TalesOfZaraPlugin : JavaPlugin() {

    override fun onEnable() {
        instance = this
        getCommand("talesofzara").executor = TalesOfZaraCommand()
        getCommand("toz").executor = TalesOfZaraCommand()
        Bukkit.getPluginManager().registerEvents(TalesOfZaraListener(), this)
    }

    override fun onDisable() {}

    companion object {
        var instance: TalesOfZaraPlugin? = null
            private set
    }
}