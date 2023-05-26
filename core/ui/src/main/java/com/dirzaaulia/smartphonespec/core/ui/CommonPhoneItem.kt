package com.dirzaaulia.smartphonespec.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dirzaaulia.smartphonespec.core.designsystem.component.DynamicAsyncImage
import com.dirzaaulia.smartphonespec.core.model.Phone

@Composable
fun CommonPhoneItem(
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
