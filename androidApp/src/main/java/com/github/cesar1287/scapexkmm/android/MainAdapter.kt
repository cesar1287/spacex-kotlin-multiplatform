package com.github.cesar1287.scapexkmm.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.cesar1287.scapexkmm.android.databinding.ItemLaunchBinding

class MainAdapter:
    ListAdapter<RocketLaunchVO, MainAdapter.MainViewHolder>(RocketLaunchVO.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemLaunchBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainViewHolder(
        private val binding: ItemLaunchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            rocketLaunch: RocketLaunchVO?
        ) = with(binding) {

            missionName.text = itemView.context.getString(R.string.mission_name_field, rocketLaunch?.missionName)
            launchYear.text = itemView.context.getString(R.string.launch_year_field, rocketLaunch?.launchYear.toString())
            details.text = itemView.context.getString(R.string.details_field, rocketLaunch?.details ?: "")

            rocketLaunch?.launchSuccess?.let {
                if (it) {
                    launchSuccess.text = itemView.context.getString(R.string.successful)
                    launchSuccess.setTextColor((ContextCompat.getColor(itemView.context, R.color.colorSuccessful)))
                } else {
                    launchSuccess.text = itemView.context.getString(R.string.unsuccessful)
                    launchSuccess.setTextColor((ContextCompat.getColor(itemView.context, R.color.colorUnsuccessful)))
                }
            } ?: run {
                launchSuccess.text = itemView.context.getString(R.string.no_data)
                launchSuccess.setTextColor((ContextCompat.getColor(itemView.context, R.color.colorNoData)))
            }
        }
    }
}