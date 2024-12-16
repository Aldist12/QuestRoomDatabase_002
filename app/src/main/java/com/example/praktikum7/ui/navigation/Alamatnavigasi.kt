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
object DestinasiUpdate : Alamatnavigasi {
    override val route = "update"
    const val NIM = "nim"
    val routesWithArg = "$route/{$NIM}"
}