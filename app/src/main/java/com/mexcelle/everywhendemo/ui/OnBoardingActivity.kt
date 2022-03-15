package com.mexcelle.everywhendemo.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.ui.adapter.OnBoardingAdapter
import com.mexcelle.everywhendemo.util.Utility
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import java.util.*

class OnBoardingActivity : AppCompatActivity() {

    private val mPager: ViewPager? = null
    private var currentPage = 0
    private var NUM_PAGES = 0
    private val IMAGES = arrayOf(R.drawable.onboarding1, R.drawable.onboarding2, R.drawable.onboarding3)
    private val ImagesArray = ArrayList<Int>()
    private var mPager11: ViewPager? = null
    lateinit var titleTv: TextView
    lateinit var  subtitleTv: TextView
    var nextImage: ImageView? = null
    lateinit var skip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        init()

    }

    fun init() {
        for (i in IMAGES.indices) ImagesArray.add(
            IMAGES.get(i)
        )

        mPager11 = findViewById(R.id.pager11)
        skip = findViewById(R.id.skip_btn)
        titleTv = findViewById(R.id.title1)
        subtitleTv = findViewById(R.id.title2)
        Utility.setSemibold(this@OnBoardingActivity,titleTv)
        Utility.setRegular(this@OnBoardingActivity,subtitleTv)
        Utility.setbold(this@OnBoardingActivity,skip)




        skip!!.setOnClickListener {
            gotoTermsAndConditionsPage()
        }



        mPager11!!.setAdapter(OnBoardingAdapter(this@OnBoardingActivity, ImagesArray))
        val indicator = findViewById<View>(R.id.indicator) as DotsIndicator
        indicator.setViewPager(mPager11!!)
        val density = resources.displayMetrics.density

        //Set circle indicator radius
        // indicator.radius = 5 * density
        NUM_PAGES = IMAGES.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager11!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 2000, 2000)

        // Pager listener over indicator

        titleTv!!.setText("Volunteer for different \ncause")

        Utility.setSemibold(this@OnBoardingActivity,titleTv)
        Utility.setRegular(this@OnBoardingActivity,subtitleTv)
        Utility.setbold(this@OnBoardingActivity,skip)


        subtitleTv!!.setText("Lorem ipsum dolor sit amet, consectetur\n" +
                "adipiscing elit. Nil vitae consectetur nisi id\n" +
                "mauris donec. Ac adipiscing magna tristique\n" +
                "lacus aliquam elementum. In orci, lobortis\n" +
                "libero pellentesque nunc. Nec aliquet lectus\n" +
                "vitae tellus.")
        mPager11!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                currentPage = position
                if (position == 0) {
                    titleTv!!.setText("TITLE BOARDING 1")
                    subtitleTv!!.setText("Lorem ipsum dolor sit amet, consectetur\n" +
                            "adipiscing elit. Nil vitae consectetur nisi id\n" +
                            "mauris donec. Ac adipiscing magna tristique\n" +
                            "lacus aliquam elementum. In orci, lobortis\n" +
                            "libero pellentesque nunc. Nec aliquet lectus\n" +
                            "vitae tellus.")
                }
                if (position == 1) {
                    titleTv!!.setText("TITLE BOARDING 2")
                    subtitleTv!!.setText("Lorem ipsum dolor sit amet, consectetur\n" +
                            "adipiscing elit. Nil vitae consectetur nisi id\n" +
                            "mauris donec. Ac adipiscing magna tristique\n" +
                            "lacus aliquam elementum. In orci, lobortis\n" +
                            "libero pellentesque nunc. Nec aliquet lectus\n" +
                            "vitae tellus.")
                }
                if (position == 2) {
                    titleTv!!.setText("TITLE BOARDING 3")
                    subtitleTv!!.setText("Lorem ipsum dolor sit amet, consectetur\n" +
                            "adipiscing elit. Nil vitae consectetur nisi id\n" +
                            "mauris donec. Ac adipiscing magna tristique\n" +
                            "lacus aliquam elementum. In orci, lobortis\n" +
                            "libero pellentesque nunc. Nec aliquet lectus\n" +
                            "vitae tellus.")
                }
            }
            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun gotoTermsAndConditionsPage() {

        val intent = Intent(this@OnBoardingActivity, TermsAndConditionsActivity::class.java)
        startActivity(intent)
    }

}