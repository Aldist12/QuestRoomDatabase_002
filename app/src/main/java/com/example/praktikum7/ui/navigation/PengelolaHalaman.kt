package com.example.praktikum7.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.praktikum7.ui.View.mahasiswa.DestinasiInsert
import com.example.praktikum7.ui.View.mahasiswa.InsertMhsView
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeMhsView(
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println(
                        "PengelolaHalaman: nim = $nim"
                    )
                },
                onAddMhs = {
                    navController.navigate(DestinasiInsert.route)
                },
                modifier = modifier
            )
        }
        composable(
            DestinasiDetail.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM) {
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            nim?.let { nim ->
                DetailMhsView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            composable(
                route = DestinasiInsert.route
            ) {
                InsertMhsView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.popBackStack()
                    },
                    modifier = modifier,
                )
            }
                @Composable
                fun PengelolaHalaman(
                    navController: NavHostController = rememberNavController(),
                    modifier: Modifier = Modifier
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = DestinasiHome.route
                    ) {
                        composable(
                            route = DestinasiHome.route
                        ) {
                            HomeMhsView(
                                onDetailClick = { nim ->
                                    navController.navigate("${DestinasiDetail.route}/$nim")
                                },
                                onAddMhs = {
                                    navController.navigate(DestinasiInsert.route)
                                },
                                modifier = modifier
                            )
                        }

                        composable(
                            route = DestinasiInsert.route
                        ) {
                            InsertMhsView(
                                onBack = {
                                    navController.popBackStack()
                                },
                                onNavigate = {
                                    navController.popBackStack()
                                },
                                modifier = modifier,
                            )
                        }

                        composable(
                            DestinasiDetail.routesWithArg,
                            arguments = listOf(
                                navArgument(DestinasiDetail.NIM) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val nim = it.arguments?.getString(DestinasiDetail.NIM)
                            nim?.let { nim ->
                                DetailMhsView(
                                    onBack = {
                                        navController.popBackStack()
                                    },
                                    onEditClick = {
                                        navController.navigate("${DestinasiUpdate.route}/$it")
                                    },
                                    modifier = modifier,
                                    onDeleteClick = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }

                        composable(
                            DestinasiUpdate.routesWithArg,
                            arguments = listOf(
                                navArgument(DestinasiUpdate.NIM) {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            UpdateMhsView(
                                onBack = {
                                    navController.popBackStack()
                                },
                                onNavigate = {
                                    navController.popBackStack()
                                },
                                modifier = modifier,
                            )
                        }
                    }
                }

        }

    }
}
