package com.nitishsharma.animateme.viewpagermock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nitishsharma.animateme.databinding.ActivityMainBinding
import com.nitishsharma.animateme.databinding.ActivityStepathonBinding

class MockViewPagerAdapter() : RecyclerView.Adapter<MockViewPagerAdapter.ViewHolder>() {
    class ViewHolder(val binding: ActivityStepathonBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ActivityStepathonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
            }
        }
    }

    override fun getItemCount() = 3
}