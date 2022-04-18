package com.mexcelle.thoughtifydemo.ui.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.util.Utility
import kotlinx.android.synthetic.main.activity_splash.*

class OnBoardingAdapter(
    private val context: Context,
    private val IMAGES: ArrayList<Int>,
    private val TXT1: ArrayList<String>,
    private val TXT2: ArrayList<String>
) :
    PagerAdapter() {
    private val inflater: LayoutInflater
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return IMAGES.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout: View = inflater.inflate(R.layout.slidingimages_layout, view, false)!!
        val imageView = imageLayout.findViewById<View>(R.id.image) as ImageView
        val textView1 = imageLayout.findViewById<View>(R.id.title1) as TextView
        val textView2 = imageLayout.findViewById<View>(R.id.title2) as TextView

        Utility.setSemibold(context,textView1)
        Utility.setRegular(context,textView2)

        Glide.with(context).load(IMAGES[position]).into(imageView)
        textView1.text = TXT1[position]
        textView2.text = TXT2[position]


        textView1.alpha = 0.0F
        textView1.animate()
            .translationYBy(-40f)
            .alpha(1.0f)
            .setDuration(3000)

            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    textView1.setVisibility(View.VISIBLE)
                }
            })


        textView2.alpha = 0.0F
        textView2.animate()
            .translationYBy(-40f)
            .alpha(1.0f)
            .setDuration(3000)

            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    textView2.setVisibility(View.VISIBLE)
                }
            })



        imageView.alpha = 0.0F
        imageView.animate()
            .translationY(20f)
            .alpha(1.0f)
            .setDuration(5000)

            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    imageView.setVisibility(View.VISIBLE)
                }
            })


        val scaleDownX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.1f)
        val scaleDownY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.1f)
        scaleDownX.duration = 5000
        scaleDownY.duration = 5000

        val scaleDown = AnimatorSet()
        scaleDown.play(scaleDownX).with(scaleDownY)

        scaleDown.start()









        view.addView(imageLayout, 0)
        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
    }

    override fun saveState(): Parcelable? {
        return null
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}