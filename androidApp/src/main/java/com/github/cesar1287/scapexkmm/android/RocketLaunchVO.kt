package com.github.cesar1287.scapexkmm.android

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketLaunchVO(
    val flightNumber: Int,
    val missionName: String,
    val launchYear: Int,
    val details: String?,
    val launchSuccess: Boolean?,
    val patch: String?,
    val article: String?
): Parcelable {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<RocketLaunchVO> =
            object : DiffUtil.ItemCallback<RocketLaunchVO>() {
                override fun areItemsTheSame(oldItem: RocketLaunchVO, newItem: RocketLaunchVO): Boolean {
                    return oldItem.flightNumber == newItem.flightNumber
                }

                override fun areContentsTheSame(oldItem: RocketLaunchVO, newItem: RocketLaunchVO): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
