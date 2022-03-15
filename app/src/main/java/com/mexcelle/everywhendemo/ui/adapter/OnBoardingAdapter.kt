package com.mexcelle.everywhendemo.ui.adapter

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.ui.OnBoardingFragment

class OnBoardingAdapter(
    private val context: Context,
    private val IMAGES: ArrayList<Int>
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
        Glide.with(context).load(IMAGES[position]).into(imageView)
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