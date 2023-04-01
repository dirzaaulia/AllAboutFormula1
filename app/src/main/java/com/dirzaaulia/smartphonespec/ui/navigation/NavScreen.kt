package com.dirzaaulia.smartphonespec.ui.navigation

sealed class NavScreen(val route: String) {
  object Latest : NavScreen("Latest")

  object Detail: NavScreen("Detail") {
    const val routeWithArgument: String = "Detail/{slug}}"
    const val argument0: String = "slug"
  }
}