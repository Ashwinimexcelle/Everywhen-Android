package com.mexcelle.thoughtifydemo.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.ui.adapter.OnBoardingAdapter
import com.mexcelle.thoughtifydemo.util.Utility
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import java.util.*

class OnBoardingActivity : AppCompatActivity() {

    private val mPager: ViewPager? = null
    private var currentPage = 0
    private var NUM_PAGES = 0
    private val IMAGES = arrayOf(R.drawable.onboarding1, R.drawable.onboarding2, R.drawable.onboarding3)
    private val TXT1 = arrayOf("Lorem ipsum dolor sit amet, consectetur\\n\" +\n" +
            "                \"adipiscing elit. Nil vitae consectetur nisi id\\n\" +\n" +
            "                \"mauris donec. Ac adipiscing magna tristique\\n\" +\n" +
            "                \"lacus aliquam elementum. In orci, lobortis\\n\" +\n" +
            "                \"libero pellentesque nunc. Nec aliquet lectus\\n\" +\n" +
            "                \"vitae tellus.",
        "Lorem ipsum dolor sit amet, consectetur\\n\" +\n" +
                "                \"adipiscing elit. Nil vitae consectetur nisi id\\n\" +\n" +
                "                \"mauris donec. Ac adipiscing magna tristique\\n\" +\n" +
                "                \"lacus aliquam elementum. In orci, lobortis\\n\" +\n" +
                "                \"libero pellentesque nunc. Nec aliquet lectus\\n\" +\n" +
                "                \"vitae tellus.",
        "Lorem ipsum dolor sit amet, consectetur\\n\" +\n" +
                "                \"adipiscing elit. Nil vitae consectetur nisi id\\n\" +\n" +
                "                \"mauris donec. Ac adipiscing magna tristique\\n\" +\n" +
                "                \"lacus aliquam elementum. In orci, lobortis\\n\" +\n" +
                "                \"libero pellentesque nunc. Nec aliquet lectus\\n\" +\n" +
                "                \"vitae tellus.")


    private val TXT2 = arrayOf("TITLE BOARDING 1"
        , "TITLE BOARDING 2" ,
        "TITLE BOARDING 3")


    private val ImagesArray = ArrayList<Int>()
    private val txt1Array = ArrayList<String>()
    private val txt2Array = ArrayList<String>()

    private var mPager11: ViewPager? = null

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


        for (i in TXT1.indices) txt1Array.add(
            TXT1.get(i)
        )

        for (i in TXT2.indices) txt2Array.add(
            TXT2.get(i)
        )


        mPager11 = findViewById(R.id.pager11)
        skip = findViewById(R.id.skip_btn)
        /*titleTv = findViewById(R.id.title1)
        subtitleTv = findViewById(R.id.title2)*/
       /* Utility.setSemibold(this@OnBoardingActivity,titleTv)
        Utility.setRegular(this@OnBoardingActivity,subtitleTv)*/
        Utility.setbold(this@OnBoardingActivity,skip)




        skip!!.setOnClickListener {
            gotoTermsAndConditionsPage()
        }



        mPager11!!.setAdapter(OnBoardingAdapter(this@OnBoardingActivity, ImagesArray,txt1Array,txt2Array))
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
        }, 5000, 5000)

        // Pager listener over indicator

       /* titleTv!!.setText("Volunteer for different \ncause")

        Utility.setSemibold(this@OnBoardingActivity,titleTv)
        Utility.setRegular(this@OnBoardingActivity,subtitleTv)*/
        Utility.setbold(this@OnBoardingActivity,skip)


       /* subtitleTv!!.setText("Lorem ipsum dolor sit amet, consectetur\n" +
                "adipiscing elit. Nil vitae consectetur nisi id\n" +
                "mauris donec. Ac adipiscing magna tristique\n" +
                "lacus aliquam elementum. In orci, lobortis\n" +
                "libero pellentesque nunc. Nec aliquet lectus\n" +
                "vitae tellus.")*/
        mPager11!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                currentPage = position
                if (position == 0) {
                    /*titleTv!!.setText("TITLE BOARDING 1")*/
                    /*subtitleTv!!.setText("Lorem ipsum dolor sit amet, consectetur\n" +
                            "adipiscing elit. Nil vitae consectetur nisi id\n" +
                            "mauris donec. Ac adipiscing magna tristique\n" +
                            "lacus aliquam elementum. In orci, lobortis\n" +
                            "libero pellentesque nunc. Nec aliquet lectus\n" +
                            "vitae tellus.")*/
                }
                if (position == 1) {
                    /*titleTv!!.setText("TITLE BOARDING 2")
                    subtitleTv!!.setText("Lorem ipsum dolor sit amet, consectetur\n" +
                            "adipiscing elit. Nil vitae consectetur nisi id\n" +
                            "mauris donec. Ac adipiscing magna tristique\n" +
                            "lacus aliquam elementum. In orci, lobortis\n" +
                            "libero pellentesque nunc. Nec aliquet lectus\n" +
                            "vitae tellus.")*/
                }
                if (position == 2) {
                    /*titleTv!!.setText("TITLE BOARDING 3")
                    subtitleTv!!.setText("Lorem ipsum dolor sit amet, consectetur\n" +
                            "adipiscing elit. Nil vitae consectetur nisi id\n" +
                            "mauris donec. Ac adipiscing magna tristique\n" +
                            "lacus aliquam elementum. In orci, lobortis\n" +
                            "libero pellentesque nunc. Nec aliquet lectus\n" +
                            "vitae tellus.")*/
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