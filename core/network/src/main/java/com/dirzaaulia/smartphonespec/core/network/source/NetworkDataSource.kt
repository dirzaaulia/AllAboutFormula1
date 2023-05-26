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

import com.dirzaaulia.smartphonespec.core.network.model.NetworkBrand
import com.dirzaaulia.smartphonespec.core.network.model.NetworkPhone

interface NetworkDataSource {
    suspend fun getLatestSmartphone(): List<NetworkPhone>

    suspend fun getTopByInterestSmartphone(): List<NetworkPhone>

    suspend fun getSmartphoneBrands(): List<NetworkBrand>

    suspend fun searchSmartphone(query: String): List<NetworkPhone>
}
