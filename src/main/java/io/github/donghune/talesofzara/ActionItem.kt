package io.github.donghune.talesofzara

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

abstract class ActionItem(
    val name: String,
    val description: String,
    val icon: Material,
) {
    open fun action(player: Player) {}
    fun toItemStack(): ItemStack {
        val arrayList = ArrayList<String>()
        arrayList.add(description)
        val itemStack = ItemStack(icon)
        itemStack.amount = 1
        val itemMeta = itemStack.itemMeta
        itemMeta.displayName = name
        itemMeta.lore = arrayList
        itemStack.itemMeta = itemMeta
        return itemStack
    }
}