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

package com.dirzaaulia.smartphonespec.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dirzaaulia.smartphonespec.core.designsystem.icon.Icon
import com.dirzaaulia.smartphonespec.utilities.Constant

@Composable
fun ErrorScreen() {

}
@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String = Constant.EMPTY_STRING,
    icon: Icon,
    showIcon: Boolean = false,
    retry: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
        ) {
            if (showIcon) Icon(icon = icon)
            Text(
                modifier = Modifier.padding(30.dp),
                text = errorMessage,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
            )
            Button(
                onClick = retry,
            ) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}
