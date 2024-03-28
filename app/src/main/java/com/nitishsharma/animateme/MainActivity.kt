package com.nitishsharma.animateme

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.nitishsharma.animateme.databinding.ActivityMainBinding
import com.nitishsharma.animateme.utils.EventObserver
import com.nitishsharma.animateme.viewpagermock.MockViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var viewPager: ViewPager2
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gestureDetector = GestureDetectorCompat(this, MyGestureListener())
        onViewCreated()
    }

    private fun onViewCreated() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        updateImageViewForRightSwipe(currentIndex)
        initClickListeners()
        initObservers()
        initDots()
    }

    private fun initDots() {
        viewPager = binding.viewPageMock
        val adapter = MockViewPagerAdapter()
        viewPager.adapter = adapter
        binding.wormDot.attachTo(viewPager)
    }

    private fun initObservers() {
        viewModel.pageNumber.observe(this, EventObserver {
            setStatusBarColor(it)
            viewPager.currentItem = it
        })
    }

    private fun setStatusBarColor(page: Int) {
        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        when (page) {
            0 -> window.statusBarColor = ContextCompat.getColor(this, R.color.fade_purple_dark)
            1 -> window.statusBarColor = ContextCompat.getColor(this, R.color.fade_blue_dark)
            2 -> window.statusBarColor = ContextCompat.getColor(this, R.color.fade_red_dark)
        }
    }

    private fun initClickListeners() {
        binding.buttonNxt.setOnClickListener {
            checkForRightSwipe()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event)
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        @Suppress("nothing_to_override", "accidental_override")
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffX = e2?.x?.minus(e1!!.x) ?: 0f
            val diffY = e2?.y?.minus(e1!!.y) ?: 0f

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        checkForLeftSwipe()
                    } else if (diffX < 0) {
                        checkForRightSwipe()
                    }
                    return true
                }
            }
            return false
        }
    }

    private fun checkForLeftSwipe() {
        if (currentIndex > 0) {
            currentIndex--
            updateImageViewForLeftSwipe(currentIndex)
            viewModel.updatePageNumber(currentIndex)
        }
    }

    private fun checkForRightSwipe() {
        if (currentIndex < viewModel.topImages.size - 1) {
            currentIndex++
            updateImageViewForRightSwipe(currentIndex)
            viewModel.updatePageNumber(currentIndex)
        }
    }

    private fun updateImageViewForRightSwipe(index: Int) {
        val fadeOut = ObjectAnimator.ofFloat(binding.thonImg, View.ALPHA, 1f, 0f)
        val slideOut = ObjectAnimator.ofFloat(
            binding.thonImg,
            View.TRANSLATION_X,
            0f,
            -binding.thonImg.width.toFloat()
        )
        val slideIn = ObjectAnimator.ofFloat(
            binding.thonImg,
            View.TRANSLATION_X,
            binding.thonImg.width.toFloat(),
            0f
        )
        val fadeIn = ObjectAnimator.ofFloat(binding.thonImg, View.ALPHA, 0f, 1f)
        val scaleInX = ObjectAnimator.ofFloat(binding.thonImg, View.SCALE_X, 0.5f, 1f)
        val scaleInY = ObjectAnimator.ofFloat(binding.thonImg, View.SCALE_Y, 0.5f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeOut, slideOut, slideIn, fadeIn, scaleInX, scaleInY)
        animatorSet.duration = 300
        animatorSet.start()

        binding.thonImg.setImageDrawable(
            ContextCompat.getDrawable(
                this@MainActivity,
                viewModel.topImages[index]
            )
        )
    }

    private fun updateImageViewForLeftSwipe(index: Int) {
        val fadeOut = ObjectAnimator.ofFloat(binding.thonImg, View.ALPHA, 1f, 0f)
        val slideOut = ObjectAnimator.ofFloat(
            binding.thonImg,
            View.TRANSLATION_X,
            0f,
            binding.thonImg.width.toFloat()
        )
        val slideIn = ObjectAnimator.ofFloat(
            binding.thonImg,
            View.TRANSLATION_X,
            -binding.thonImg.width.toFloat(),
            0f
        )
        val fadeIn = ObjectAnimator.ofFloat(binding.thonImg, View.ALPHA, 0f, 1f)
        val scaleInX = ObjectAnimator.ofFloat(binding.thonImg, View.SCALE_X, 0.5f, 1f)
        val scaleInY = ObjectAnimator.ofFloat(binding.thonImg, View.SCALE_Y, 0.5f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeOut, slideOut, slideIn, fadeIn, scaleInX, scaleInY)
        animatorSet.duration = 300
        animatorSet.start()

        binding.thonImg.setImageDrawable(
            ContextCompat.getDrawable(
                this@MainActivity,
                viewModel.topImages[index]
            )
        )
    }
}
