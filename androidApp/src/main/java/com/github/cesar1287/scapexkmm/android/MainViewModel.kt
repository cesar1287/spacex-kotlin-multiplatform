package com.github.cesar1287.scapexkmm.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.scapexkmm.domain.LaunchesUseCaseImpl
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val launchesUseCase by lazy {
        LaunchesUseCaseImpl()
    }

    private val _onLaunchesList: MutableLiveData<List<RocketLaunchVO>> = MutableLiveData()

    val onLaunchesList: LiveData<List<RocketLaunchVO>>
        get() = _onLaunchesList

    fun getAllLaunches() {
        viewModelScope.launch {
            kotlin.runCatching {
                launchesUseCase.getAllLaunches()
            }.onSuccess {
                _onLaunchesList.postValue(
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
                )
            }.onFailure {
                it
            }
        }
    }
}