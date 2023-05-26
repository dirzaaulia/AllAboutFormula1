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

package com.dirzaaulia.smartphonespec.core.network.source

import com.dirzaaulia.smartphonespec.core.common.network.Dispatcher
import com.dirzaaulia.smartphonespec.core.common.network.SmartphoneSpecDispatchers.IO
import com.dirzaaulia.smartphonespec.core.network.Brands
import com.dirzaaulia.smartphonespec.core.network.KtorClient
import com.dirzaaulia.smartphonespec.core.network.Latest
import com.dirzaaulia.smartphonespec.core.network.Search
import com.dirzaaulia.smartphonespec.core.network.TopByInterest
import com.dirzaaulia.smartphonespec.core.network.model.NetworkBrand
import com.dirzaaulia.smartphonespec.core.network.model.NetworkData
import com.dirzaaulia.smartphonespec.core.network.model.NetworkPhone
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val ktorClient: KtorClient,
) : NetworkDataSource {
    override suspend fun getLatestSmartphone(): List<NetworkPhone> {
        return withContext(ioDispatcher) {
            ktorClient
                .callGetApi<Latest, NetworkData>(Latest())
                .phones
        }
    }

    override suspend fun getTopByInterestSmartphone(): List<NetworkPhone> {
        return withContext(ioDispatcher) {
            ktorClient
                .callGetApi<TopByInterest, NetworkData>(TopByInterest())
                .phones
        }
    }

    override suspend fun getSmartphoneBrands(): List<NetworkBrand> {
        return withContext(ioDispatcher) {
            ktorClient.callGetApi(Brands())
        }
    }

    override suspend fun searchSmartphone(query: String): List<NetworkPhone> {
        return withContext(ioDispatcher) {
            ktorClient
                .callGetApi<Search, NetworkData>(Search(query = query))
                .phones
        }
    }
}
