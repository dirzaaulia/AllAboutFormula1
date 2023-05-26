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

package com.dirzaaulia.smartphonespec.feature.latest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dirzaaulia.smartphonespec.core.designsystem.component.DynamicAsyncImage
import com.dirzaaulia.smartphonespec.core.designsystem.icon.Icon.ImageVectorIcon
import com.dirzaaulia.smartphonespec.core.model.Phone
import com.dirzaaulia.smartphonespec.core.ui.CommonPlaceholder
import com.dirzaaulia.smartphonespec.core.ui.ErrorScreen
import com.dirzaaulia.smartphonespec.utilities.Constant

@Composable
internal fun LatestRoute(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
    viewModel: LatestViewModel = hiltViewModel(),
) {
    val latestPhoneUiState:
        LatestPhoneUiState by viewModel.latestPhoneUiState.collectAsStateWithLifecycle()

    LatestScreen(
        modifier = modifier,
        onItemClick = onItemClick,
        latestPhoneUiState = latestPhoneUiState,
        retry = viewModel::setLatestPhoneUiState,
    )
}

@Composable
internal fun LatestScreen(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit,
    latestPhoneUiState: LatestPhoneUiState,
    retry: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
        ) {
            when (latestPhoneUiState) {
                LatestPhoneUiState.Loading ->
                    items(Constant.COMMON_PLACEHOLDER_COUNT) {
                        CommonPlaceholder(
                            modifier = Modifier.padding(8.dp),
                            height = 112.dp,
                            shape = MaterialTheme.shapes.medium,
                        )
                    }

                is LatestPhoneUiState.Success ->
                    items(latestPhoneUiState.phones) { phone ->
                        LatestPhoneItem(
                            phone = phone,
                            onItemClick = onItemClick,
                        )
                    }
                else -> Unit
            }
        }

        if (latestPhoneUiState is LatestPhoneUiState.Error) {
            ErrorScreen(
                errorMessage = latestPhoneUiState.throwable.message.toString(),
                icon = ImageVectorIcon(
                    imageVector = Icons.Rounded.Error,
                ),
                showIcon = true,
                retry = retry,
            )
        }
    }
}

@Composable
internal fun LatestPhoneItem(
    modifier: Modifier = Modifier,
    phone: Phone,
    onItemClick: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .width(220.dp)
            .padding(8.dp)
            .clickable { onItemClick(phone.slug) },
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            DynamicAsyncImage(url = phone.image)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = phone.phoneName,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
