package com.example.praktikum7.ui.navigation

interface Alamatnavigasi {
    val route: String
}

object DestinasiHome : Alamatnavigasi {
    override val route = "home"
}
object DestinasiDetail : Alamatnavigasi {
    override val route = "detail"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}