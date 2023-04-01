package com.dirzaaulia.smartphonespec.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dirzaaulia.smartphonespec.domain.data.model.PhoneDetail
import com.dirzaaulia.smartphonespec.domain.data.model.PhoneDetail.Companion.toMapDetail
import com.dirzaaulia.smartphonespec.ui.common.CommonLoading
import com.dirzaaulia.smartphonespec.ui.common.NetworkImage
import com.dirzaaulia.smartphonespec.ui.common.StaggeredVerticalGrid
import com.dirzaaulia.smartphonespec.utils.ResponseResult
import com.dirzaaulia.smartphonespec.utils.isLoading
import com.dirzaaulia.smartphonespec.utils.replaceIfNull
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun Detail(
    modifier: Modifier = Modifier,
    slug: String,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val phoneDetailState by viewModel.phoneDetailState.collectAsState()
    val phoneDetail by viewModel.phoneDetail.collectAsState()


    LaunchedEffect(viewModel) {
        viewModel.getPhoneDetail(slug)
    }

    when (phoneDetailState) {
        ResponseResult.Loading -> CommonLoading(visibility = phoneDetailState.isLoading)
        is ResponseResult.Success -> DetailSuccess(
            modifier = modifier,
            phoneDetail = phoneDetail
        )
        is ResponseResult.Error -> TODO()
    }
}

@Composable
fun DetailSuccess(
    modifier: Modifier = Modifier,
    phoneDetail: PhoneDetail?
) {
    val phoneImages = phoneDetail?.phoneImages.replaceIfNull()

    val pagerState = rememberPagerState()

    LazyColumn(modifier = modifier) {
        item {
            HorizontalPager(
                count = phoneImages.size,
                state = pagerState
            ) { index ->
                NetworkImage(
                    modifier = Modifier.height(320.dp),
                    url = phoneImages[index]
                )
            }
        }
        item {
            PagerIndicator(
                pageCount = phoneImages.size,
                pagerState = pagerState
            )
        }
        item {
            Text(
                text = phoneDetail?.phoneName.replaceIfNull(),
                style = MaterialTheme.typography.displayLarge
            )
        }
        item {
            phoneDetail?.toMapDetail()?.let {
                SpecificationDetail(
                    title = "Information",
                    map = it
                )
            }
        }
    }
}

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pageCount: Int,
    pagerState: PagerState
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                MaterialTheme.colorScheme.onSurface
            else MaterialTheme.colorScheme.inversePrimary
            Box(modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .background(color)
                .size(12.dp)
            )
        }
    }
}

@Composable
fun SpecificationDetail(
    modifier: Modifier = Modifier,
    title: String,
    map: Map<String, String>
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge
        )
        StaggeredVerticalGrid(maxColumnWidth = 220.dp) {
            map.forEach { data ->
                Card(
                    modifier = modifier
                        .padding(8.dp)
                        .width(220.dp)
                ) {
                    Text(
                        text = data.key,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = data.value,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    }
}