package com.github.cesar1287.scapexkmm.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.scapexkmm.domain.LaunchesUseCaseImpl
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val launchesUseCase by lazy {
        LaunchesUseCaseImpl()
    }

    fun getAllLaunches() {
        viewModelScope.launch {
            kotlin.runCatching {
                launchesUseCase.getAllLaunches()
            }.onSuccess {
                it.map {rocketLauncher ->
                    RocketLaunchVO(
                        flightNumber = rocketLauncher.flightNumber,
                        missionName = rocketLauncher.missionName,
                        launchYear = rocketLauncher.launchYear,
                        details = rocketLauncher.details,
                        launchSuccess = rocketLauncher.launchSuccess,
                        patch = rocketLauncher.links.patch,
                        article = rocketLauncher.links.article
                    )
                }
            }.onFailure {
                it
            }
        }
    }
}