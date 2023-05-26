/*
 * Copyright 2023 Dirza Aulia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dirzaaulia.smartphonespec.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dirzaaulia.smartphonespec.feature.latest.navigation.latestNavigationRoute
import com.dirzaaulia.smartphonespec.feature.latest.navigation.latestScreen
import com.dirzaaulia.smartphonespec.search.navigation.searchScreen

@Composable
fun SmartphoneSpecNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = latestNavigationRoute,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        latestScreen(onItemClick = {})
        searchScreen(
            onItemClick = {},
            upPress = { navController.navigateUp() }
        )
    }
}
