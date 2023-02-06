package com.github.cesar1287.scapexkmm.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cesar1287.scapexkmm.android.viewObject.RocketLaunchVO
import com.github.cesar1287.scapexkmm.domain.LaunchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val launchesUseCase: LaunchesUseCase
) : ViewModel() {

    private val _onLaunchesList: MutableLiveData<List<RocketLaunchVO>> = MutableLiveData()

    val onLaunchesList: LiveData<List<RocketLaunchVO>>
        get() = _onLaunchesList

    val command: MutableLiveData<Command> = MutableLiveData()

    fun getAllLaunches() {
        viewModelScope.launch {
            kotlin.runCatching {
                command.value = Command.Loading(true)
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
                command.value = Command.Error()
            }.also {
                command.value = Command.Loading(false)
            }
        }
    }
}

sealed class Command {
    class Loading(val value: Boolean): Command()
    class Error(val error: Int? = null): Command()
}