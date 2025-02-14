package su.molnikas.mcapi.controllers

import io.javalin.http.Context
import su.molnikas.mcapi.services.WhitelistService

class WhitelistController(private val whitelistService: WhitelistService) {
    fun getWhitelist(ctx: Context) {
        ctx.json(whitelistService.getWhitelistStatus())
    }

    fun toggleWhitelist(ctx: Context) {
        ctx.json(whitelistService.toggleWhiteList())
    }
}