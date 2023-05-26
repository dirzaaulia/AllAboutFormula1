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

package com.dirzaaulia.smartphonespec.core.data.di

import com.dirzaaulia.smartphonespec.core.data.repository.brands.SmartphoneBrandsRepository
import com.dirzaaulia.smartphonespec.core.data.repository.brands.SmartphoneBrandsRepositoryImpl
import com.dirzaaulia.smartphonespec.core.data.repository.interest.TopByInterestSmartphoneRepository
import com.dirzaaulia.smartphonespec.core.data.repository.interest.TopByInterestSmartphoneRepositoryImpl
import com.dirzaaulia.smartphonespec.core.data.repository.latest.LatestSmartphoneRepository
import com.dirzaaulia.smartphonespec.core.data.repository.latest.LatestSmartphoneRepositoryImpl
import com.dirzaaulia.smartphonespec.core.data.repository.search.SearchSmartphoneRepository
import com.dirzaaulia.smartphonespec.core.data.repository.search.SearchSmartphoneRepositoryImpl
import com.dirzaaulia.smartphonespec.core.data.util.ConnectivityManagerNetworkMonitor
import com.dirzaaulia.smartphonespec.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    fun bindsLatestSmartphoneRepository(
        latestSmartphoneRepositoryImpl: LatestSmartphoneRepositoryImpl,
    ): LatestSmartphoneRepository

    @Binds
    fun bindsTopByInterestSmartphoneRepository(
        topByInterestSmartphoneRepositoryImpl: TopByInterestSmartphoneRepositoryImpl
    ): TopByInterestSmartphoneRepository

    @Binds
    fun bindsSmartphoneBrandsRepository(
        smartphoneBrandsRepositoryImpl: SmartphoneBrandsRepositoryImpl
    ): SmartphoneBrandsRepository

    @Binds
    fun bindsSearchSmartphoneRepository(
        searchSmartphoneRepositoryImpl: SearchSmartphoneRepositoryImpl
    ): SearchSmartphoneRepository
}
