package com.example.jimmy_polanco_ap2_p2_.presentacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.jimmy_polanco_ap2_p2_.presentacion.Detail.DetailJugadorScreen
import com.example.jimmy_polanco_ap2_p2_.presentacion.Detail.FormJugadorScreen
import com.example.jimmy_polanco_ap2_p2_.presentacion.List.ListJugadoresScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.ListJugadores.route
    ) {

        composable(Routes.ListJugadores.route) {

            ListJugadoresScreen(
                onAddJugador = {
                    navController.navigate(Routes.FormJugador.createRoute())
                },
                onEditJugador = { jugadorId ->
                    navController.navigate(Routes.FormJugador.createRoute(jugadorId))
                }
            )
        }

        composable(
            route = Routes.DetailJugador.route,
            arguments = listOf(
                navArgument("jugadorId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val jugadorId = backStackEntry.arguments?.getInt("jugadorId") ?: 0

            DetailJugadorScreen(
                jugadorId = jugadorId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.FormJugador.route,
            arguments = listOf(
                navArgument("jugadorId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->

            val jugadorId = backStackEntry.arguments?.getInt("jugadorId") ?: -1

            FormJugadorScreen(
                jugadorId = if (jugadorId == -1) null else jugadorId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}