package com.nitishsharma.animateme.binding

import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.nitishsharma.animateme.R

@BindingAdapter("setTitleText")
fun setTitleText(view: AppCompatTextView, page: Int) {
    when (page) {
        0 -> view.text = "Step Up and Score"
        1 -> view.text = "Claim the Throne"
        2 -> view.text = "Score Big!!"
    }
}

@BindingAdapter("setSubtitleText")
fun setSubtitleText(view: AppCompatTextView, page: Int) {
    when (page) {
        0 -> view.text = "Join the Race, Lace Up, and Embrace Health"
        1 -> view.text = "Compare with your collegeagues for the Top Ranks"
        2 -> view.text = "Victory Unlocks Spectacular Vouchers, Cashbacks, and Beyond"
    }
}

@BindingAdapter("updateBackgroundWithAnimation")
fun updateBackgroundWithAnimation(view: ConstraintLayout, page: Int) {
    when (page) {
        0 -> view.setBackgroundResource(R.drawable.gradient_first)
        1 -> view.setBackgroundResource(R.drawable.gradient_second)
        2 -> view.setBackgroundResource(R.drawable.gradient_third)
    }
//    val fadeOutBackground = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
//    fadeOutBackground.duration = 50
//    fadeOutBackground.addUpdateListener { animation ->
//        if (animation.animatedFraction >= 0.5 && view.alpha != 0f) {
//            when (page) {
//                0 -> view.setBackgroundResource(R.drawable.gradient_first)
//                1 -> view.setBackgroundResource(R.drawable.gradient_second)
//                2 -> view.setBackgroundResource(R.drawable.gradient_third)
//            }
//        }
//    }
//
//    val fadeInBackground = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
//    fadeInBackground.duration = 50
//
//    val animatorSet = AnimatorSet()
//    animatorSet.playSequentially(fadeOutBackground, fadeInBackground)
//    animatorSet.start()
}

