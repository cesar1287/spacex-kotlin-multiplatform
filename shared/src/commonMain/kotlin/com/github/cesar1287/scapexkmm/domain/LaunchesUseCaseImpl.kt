package com.github.cesar1287.scapexkmm.domain

import com.github.cesar1287.scapexkmm.data.RocketLaunch
import com.github.cesar1287.scapexkmm.data.SpaceXApi

interface LaunchesUseCase {

    suspend fun getAllLaunches(): List<RocketLaunch>
}

class LaunchesUseCaseImpl: LaunchesUseCase {

    private val spaceXApi by lazy {
        SpaceXApi()
    }

    override suspend fun getAllLaunches() = spaceXApi.getAllLaunches()
}