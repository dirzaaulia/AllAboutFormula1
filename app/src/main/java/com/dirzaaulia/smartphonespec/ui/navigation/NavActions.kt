package com.dirzaaulia.smartphonespec.ui.navigation

import androidx.navigation.NavHostController

class NavActions(navHostController: NavHostController) {

  val navigateToDetail: (String) -> Unit = { slug ->
    NavScreen.Detail.apply {
      navHostController.navigate(
        routeWithArgument.replace("{$argument0}", slug)
      )
    }
  }
}