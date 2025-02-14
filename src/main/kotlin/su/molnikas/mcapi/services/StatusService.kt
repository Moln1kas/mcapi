package su.molnikas.mcapi.services

import org.bukkit.Bukkit

class StatusService {
    fun getServerStatus(): Map<String, Any> {
        val server = Bukkit.getServer()
        val onlinePlayers = server.onlinePlayers.map { it.name }

        return mapOf(
            "players_count" to onlinePlayers.size,
            "players" to onlinePlayers
        )
    }
}