package su.molnikas.mcapi.services

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import su.molnikas.mcapi.Mcapi

class WhitelistService() {
    fun getWhitelistStatus(): Map<String, Any> {
        val server = Bukkit.getServer()
        val whitelistEnabled = server.hasWhitelist()
        val whitelistedPlayers = server.whitelistedPlayers.map { it.name }

        return mapOf(
            "enabled" to whitelistEnabled,
            "players_count" to whitelistedPlayers.size,
            "players" to whitelistedPlayers
        )
    }

    fun toggleWhiteList(): Map<String, Any> {
        val server = Bukkit.getServer()
        val state = !server.hasWhitelist()

        Bukkit.getScheduler().runTask(Mcapi.plugin, Runnable {
            server.setWhitelist(state)
        })

        return mapOf(
            "enabled" to state,
            "message" to if(state) "Белый список включен." else "Белый список выключен."
        )
    }
}
