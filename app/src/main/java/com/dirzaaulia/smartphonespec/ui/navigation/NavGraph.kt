package com.dirzaaulia.smartphonespec.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dirzaaulia.smartphonespec.ui.detail.Detail
import com.dirzaaulia.smartphonespec.ui.latest.Latest
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@Composable
fun NavGraph(
  navHostController: NavHostController,
) {

  val actions = remember(navHostController) { NavActions(navHostController) }

  AnimatedNavHost(
    navController = navHostController,
    startDestination = NavScreen.Latest.route
  ) {
    composable(
      route = NavScreen.Latest.route,
      enterTransition = {
        fadeIn()
      },
      exitTransition = {
        fadeOut()
      }
    ) {
      Latest(
        navigateToDetail = actions.navigateToDetail,
      )
    }
    composable(
      route = NavScreen.Detail.routeWithArgument,
      arguments = listOf(
        navArgument(NavScreen.Detail.argument0) {
          type = NavType.StringType
        },
      ),
      enterTransition = {
        fadeIn()
      },
      exitTransition = {
        fadeOut()
      }
    ) { backStackEntry ->
      backStackEntry.arguments.let { bundle ->
        bundle?.let { argument ->
          Detail(
            slug = argument.getString(NavScreen.Detail.argument0).toString(),
          )
        }
      }
    }
  }
}