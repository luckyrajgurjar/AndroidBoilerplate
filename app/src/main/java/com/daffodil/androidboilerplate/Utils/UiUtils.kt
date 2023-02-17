package com.daffodil.androidboilerplate.Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.daffodil.androidboilerplate.R

object UiUtils {

    /** show desired loader in any fragment
    @param rootView  , loader will go in the midpoint of root view
     **/
    @SuppressLint("ResourceType")
    fun showLoader(rootView : ViewGroup,context: Activity) {
        val loaderAnimation = LottieAnimationView(context).apply {
            id = 12345
            setAnimation(R.raw.dots_loading)
            repeatMode = LottieDrawable.INFINITE
            loop(true)
            layoutParams = ViewGroup.LayoutParams(50, 50)
            playAnimation()
        }
        loaderAnimation.doOnLayout {
            it.x = rootView.width/2f - it.width/2
            it.y = rootView.height/2f - it.height/2
        }
        context.window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        rootView.addView(loaderAnimation)
    }

    // pass same viewgroup that was paased in showLoader(ViewGroup)
    @SuppressLint("ResourceType")
    fun removeLoader(rootView: ViewGroup,context: Activity){
        context.window.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        val animationView = rootView.findViewById<LottieAnimationView>(12345)
        rootView.removeView(animationView)
    }
}