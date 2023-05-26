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

package com.dirzaaulia.smartphonespec.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dirzaaulia.smartphonespec.core.model.Brand
import com.dirzaaulia.smartphonespec.core.ui.CommonPhoneItem
import com.dirzaaulia.smartphonespec.core.ui.CommonPlaceholder
import com.dirzaaulia.smartphonespec.core.ui.ErrorScreen
import com.dirzaaulia.smartphonespec.feature.latest.R
import com.dirzaaulia.smartphonespec.utilities.Constant
import com.dirzaaulia.smartphonespec.utilities.capitalizeWord
import kotlin.reflect.KMutableProperty0

@Composable
fun SearchRoute(
    onItemClick: (String) -> Unit,
    upPress: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {

    val searchQuery = viewModel.searchQuery
    val searchUiState by viewModel.searchUiState.collectAsStateWithLifecycle()
    val brandsUiState by viewModel.brandsUiState.collectAsStateWithLifecycle()

    SearchScreen(
        searchUiState = searchUiState,
        brandsUiState = brandsUiState,
        searchQuery = searchQuery,
        onItemClick = onItemClick,
        upPress = upPress,
        doSearchQuery = viewModel::searchQuery,
        setSearchUiState = viewModel::setSearchUiState,
        retryBrandsUiState = viewModel::setBrandsUiState,
        retrySearchUiState = viewModel::setSearchUiState
    )
}

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    searchUiState: SearchUiState,
    brandsUiState: BrandsUiState,
    searchQuery: String,
    onItemClick: (String) -> Unit,
    upPress: () -> Unit,
    doSearchQuery: KMutableProperty0<String>,
    setSearchUiState: () -> Unit,
    retryBrandsUiState: () -> Unit,
    retrySearchUiState: () -> Unit,
) {
    Scaffold(
        topBar = {
            SearchBar(
                searchQuery = searchQuery,
                doSearchQuery = doSearchQuery,
                setSearchUiState = setSearchUiState,
                upPress = upPress,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
            ) {
                if (searchQuery.isEmpty())
                    brandUiStateSuccess(
                        brandsUiState = brandsUiState,
                        onItemClick = onItemClick
                    )
                else
                    searchUiStateSuccess(
                        searchUiState = searchUiState,
                        onItemClick = onItemClick
                    )
            }

            if (searchQuery.isEmpty())
                BrandUiStateError(
                    brandsUiState = brandsUiState,
                    retry = retryBrandsUiState
                )
            else
                SearchUiStateError(
                    searchUiState = searchUiState,
                    retry = retrySearchUiState
                )

        }
    }
}

private fun LazyStaggeredGridScope.brandUiStateSuccess(
    brandsUiState: BrandsUiState,
    onItemClick: (String) -> Unit
) {
    when (brandsUiState) {
        BrandsUiState.Loading ->
            items(Constant.COMMON_PLACEHOLDER_COUNT) {
                CommonPlaceholder(
                    modifier = Modifier.padding(8.dp),
                    height = 112.dp,
                    shape = MaterialTheme.shapes.medium,
                )
            }

        is BrandsUiState.Success ->
            items(brandsUiState.brands) { brand ->
                BrandItem(
                    brand = brand,
                    onItemClick = onItemClick,
                )
            }

        else -> Unit
    }
}

@Composable
private fun BrandUiStateError(
    brandsUiState: BrandsUiState,
    retry: () -> Unit
) {
    if (brandsUiState is BrandsUiState.Error) {
        ErrorScreen(
            errorMessage = brandsUiState.throwable.message.toString(),
            icon = com.dirzaaulia.smartphonespec.core.designsystem.icon.Icon.ImageVectorIcon(
                imageVector = Icons.Rounded.Error,
            ),
            showIcon = true,
            retry = retry,
        )
    }
}

private fun LazyStaggeredGridScope.searchUiStateSuccess(
    searchUiState: SearchUiState,
    onItemClick: (String) -> Unit
) {
    when (searchUiState) {
        SearchUiState.Loading ->
            items(Constant.COMMON_PLACEHOLDER_COUNT) {
                CommonPlaceholder(
                    modifier = Modifier.padding(8.dp),
                    height = 112.dp,
                    shape = MaterialTheme.shapes.medium,
                )
            }

        is SearchUiState.Success ->
            items(searchUiState.phones) { phone ->
                CommonPhoneItem(
                    phone = phone,
                    onItemClick = onItemClick,
                )
            }

        else -> Unit
    }
}

@Composable
private fun SearchUiStateError(
    searchUiState: SearchUiState,
    retry: () -> Unit
) {
    if (searchUiState is SearchUiState.Error) {
        ErrorScreen(
            errorMessage = searchUiState.throwable.message.toString(),
            icon = com.dirzaaulia.smartphonespec.core.designsystem.icon.Icon.ImageVectorIcon(
                imageVector = Icons.Rounded.Error,
            ),
            showIcon = true,
            retry = retry,
        )
    }
}

@Composable
fun SearchBar(
    searchQuery: String,
    doSearchQuery: KMutableProperty0<String>,
    upPress: () -> Unit,
    setSearchUiState: () -> Unit,
) {
    var query by rememberSaveable { mutableStateOf(searchQuery) }
    val localFocusManager = LocalFocusManager.current

    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { upPress() },
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = Constant.EMPTY_STRING,
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
        TextField(
            value = query,
            onValueChange = { query = it },
            shape = RectangleShape,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_smartphone),
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                textColor = MaterialTheme.colorScheme.onSurface,
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    localFocusManager.clearFocus()
                    doSearchQuery.set(query)
                    setSearchUiState()
                },
            ),
        )
    }
}

@Composable
internal fun BrandItem(
    modifier: Modifier = Modifier,
    brand: Brand,
    onItemClick: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .width(220.dp)
            .padding(8.dp)
            .clickable { onItemClick(brand.brandSlug) },
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = brand.brandName.capitalizeWord(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }
}
