package com.nitishsharma.animateme.viewpagermock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nitishsharma.animateme.databinding.ActivityMainBinding

class MockViewPagerAdapter() : RecyclerView.Adapter<MockViewPagerAdapter.ViewHolder>() {
    class ViewHolder(val binding: ActivityMainBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
            }
        }
    }

    override fun getItemCount() = 3
}