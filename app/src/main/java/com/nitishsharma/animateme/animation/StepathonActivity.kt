package com.nitishsharma.animateme.animation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.nitishsharma.animateme.MainActivityViewModel
import com.nitishsharma.animateme.R
import com.nitishsharma.animateme.binding.animateImage
import com.nitishsharma.animateme.databinding.ActivityStepathonBinding
import com.nitishsharma.animateme.utils.EventObserver
import com.nitishsharma.animateme.viewpagermock.MockViewPagerAdapter

class StepathonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStepathonBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepathonBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        onViewCreated()
    }

    private fun onViewCreated() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        initClickListeners()
        initObservers()
        initDots()
    }

    private fun initDots() {
        viewPager = binding.viewPageMock
        viewPager.adapter = MockViewPagerAdapter()
        binding.wormDot.attachTo(viewPager)
    }

    private fun initObservers() {
        viewModel.pageNumber.observe(this, EventObserver {
            viewPager.currentItem = it
            binding.buttonNxt.text = when (it) {
                2 -> "Let's Go!"
                else -> "Next"
            }
        })
    }

    private fun initClickListeners() {
        binding.buttonNxt.setOnClickListener {
            when (viewModel.pageNumber.value?.peekContent()) {
                0 -> {
                    binding.main.transitionToState(R.id.second)
                    viewModel.updatePageNumber(1)
                }
                1 -> {
                    binding.main.transitionToState(R.id.third)
                    viewModel.updatePageNumber(2)
                }
            }
        }

        binding.main.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
                updatePageNumber(motionLayout?.currentState)
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                updatePageNumber(motionLayout?.currentState)
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                updatePageNumber(motionLayout?.currentState)
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
                // No implementation needed
            }

            private fun updatePageNumber(state: Int?) {
                state?.let {
                    val pageNumber = when (it) {
                        R.id.first -> 0
                        R.id.second -> 1
                        R.id.third -> 2
                        else -> return
                    }
                    viewModel.updatePageNumber(pageNumber)
                }
            }
        })
    }
}
