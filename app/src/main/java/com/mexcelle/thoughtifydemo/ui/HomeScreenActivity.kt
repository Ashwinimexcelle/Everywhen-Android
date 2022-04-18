package com.mexcelle.thoughtifydemo.ui

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.util.Util
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.mexcelle.thoughtifydemo.R
import com.mexcelle.thoughtifydemo.model.*
import com.mexcelle.thoughtifydemo.repository.LoginActivityRepository
import com.mexcelle.thoughtifydemo.repository.NowAndThenRepository
import com.mexcelle.thoughtifydemo.repository.PredictThePictureRepository
import com.mexcelle.thoughtifydemo.retrofit.RetrofitClient
import com.mexcelle.thoughtifydemo.ui.ui.home.HomeFragmentDirections
import com.mexcelle.thoughtifydemo.util.Constants
import com.mexcelle.thoughtifydemo.util.Utility
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_home_screen.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    public lateinit var toolbar: Toolbar
    private lateinit var navImage: ImageView
    private lateinit var screenName: TextView


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var headeName: TextView
    private lateinit var headerEmailId: TextView
    private lateinit var headerImage: ImageView
    private lateinit var navigationView: NavigationView
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_screen)
        Firebase.messaging.isAutoInitEnabled = true

        toolbar = findViewById(R.id.toolbar)
        navImage = toolbar!!.findViewById(R.id.nav_btn)
        screenName = toolbar!!.findViewById(R.id.screen_name)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val header: View = navigationView.getHeaderView(0)
        headeName = header.findViewById(R.id.user_name)
        headerEmailId = header.findViewById(R.id.user_emailid)
        headerImage = header.findViewById(R.id.h_user_imageView)

        updateImage()
        getPredictThePictureTime()
        //getNowAndThenTime()

        Utility.setSemibold(this, headeName)
        Utility.setRegular(this, headerEmailId)
        Utility.setRegular(this, terms)
        Utility.setRegular(this, privacy)
        Utility.setRegular(this, logout)
        Utility.setExodar(this, screenName)
        headeName.text = Constants.USER_NAME
        headerEmailId.text = Constants.USER_EMAILID
        headerEmailId.text = Constants.USER_EMAILID


        getFCMToken()

        // Custom animation speed or duration.
        // Custom animation speed or duration.
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator
            .addUpdateListener { animation: ValueAnimator ->
                animation_view
                    .setProgress(
                        animation
                            .animatedValue as Float
                    )
            }
        animator.start()
        toolbar.setElevation(0F);


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_home_screen) as NavHostFragment
        navController = navHostFragment.navController
        navigationView.setupWithNavController(navController)
        navigationView.setNavigationItemSelectedListener(this);

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_now_and_then, R.id.nav_profile,
                R.id.nav_friends, R.id.nav_purchase_flairs,
                R.id.nav_settings
            ), drawerLayout
        )

        /*val res = resources.getDrawable(R.drawable.btn_sidemenu)
        navImage.setImageDrawable(res)*/
        navImage!!.setOnClickListener {

            // If the navigation drawer is not open then open it, if its already open then close it.

            updateImage()
            Log.e("Here In image change", "Here In image change");
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) drawerLayout.closeDrawer(Gravity.LEFT) else drawerLayout.openDrawer(
                Gravity.LEFT
            )
        }
        logout.setOnClickListener {

            // If the navigation drawer is not open then open it, if its already open then close it.
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) drawerLayout.closeDrawer(Gravity.LEFT) else drawerLayout.openDrawer(
                Gravity.LEFT
            )


            val preferences =
                getSharedPreferences("com.mexcelle.everywhendemo", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            finish()
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)


        }
        terms!!.setOnClickListener {

            // If the navigation drawer is not open then open it, if its already open then close it.
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) drawerLayout.closeDrawer(Gravity.LEFT) else drawerLayout.openDrawer(
                Gravity.LEFT
            )
            val intent = Intent(this@HomeScreenActivity, TermsAndConditionsActivity::class.java)
            startActivity(intent)


        }

        privacy!!.setOnClickListener {

            // If the navigation drawer is not open then open it, if its already open then close it.
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) drawerLayout.closeDrawer(Gravity.LEFT) else drawerLayout.openDrawer(
                Gravity.LEFT
            )

            val intent = Intent(this@HomeScreenActivity, PrivacyPolicyActivity::class.java)
            startActivity(intent)
        }


    }

    private fun getNowAndThenTime() {

        val call = RetrofitClient.apiInterface.getNowAndThenQuestion(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN
        )
        call.enqueue(object : Callback<NowAndThenResponseData> {
            override fun onFailure(call: Call<NowAndThenResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<NowAndThenResponseData>,
                response: Response<NowAndThenResponseData>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        val nowAndThenQuestionResponseData =
                            MutableLiveData<NowAndThenResponseData?>()
                        nowAndThenQuestionResponseData.value = data!!

                        if(nowAndThenQuestionResponseData.value!!.questions.size > 0){

                            Log.e(
                                "nowAndThenQuestionResponseData.value ",
                                "nowAndThenQuestionResponseData.value " + nowAndThenQuestionResponseData.value
                            );
                            Log.e(
                                "nowAndThenQuestionResponseData.value ",
                                "nowAndThenQuestionResponseData.value " + Utility.getTime(
                                    nowAndThenQuestionResponseData.value!!.questions[0].publishDate
                                )
                            )
                        }
                    } else {

                        Utility.hideProgressDialog(this@HomeScreenActivity)
                        Utility.showDialog(
                            this@HomeScreenActivity,
                            "Error !!",
                            "Server Error.",
                            "Ok",
                            "Cancel"
                        )
                    }
                } else {

                    Utility.hideProgressDialog(this@HomeScreenActivity)
                    Utility.showDialog(
                        this@HomeScreenActivity,
                        "Error !!",
                        "Server Error.",
                        "Ok",
                        "Cancel"
                    )

                }
            }
        })


    }

    /*TimeZone tz = TimeZone.getDefault();
    System.out.println("TimeZone   "+tz.getDisplayName(false, TimeZone.SHORT)+" Timezone id :: " +tz.getID());*/

    private fun getPredictThePictureTime() {

        val call = RetrofitClient.apiInterface.getPredictThePictureQuestionCalm(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN,
            "new"
        )


        call!!.enqueue(object : Callback<PredictThePictureResponseData> {
            override fun onFailure(call: Call<PredictThePictureResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<PredictThePictureResponseData>,
                response: Response<PredictThePictureResponseData>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        val predictThePictureResponseData =
                            MutableLiveData<PredictThePictureResponseData?>()
                        predictThePictureResponseData.value = data!!
                        if (predictThePictureResponseData.value!!.pictures.size > 0) {

                            Log.e(
                                "predictThePictureResponseData.value ",
                                "predictThePictureResponseData.value " + predictThePictureResponseData.value
                            );
                            Log.e(
                                "predictThePictureResponseData.value ",
                                "predictThePictureResponseData.value " + Utility.getTime(
                                    predictThePictureResponseData.value!!.pictures[0].publishDate
                                )
                            );

                            Constants.PREDICT_THE_PICTURE_CURRENT_QUESTION_TIME = Utility.getTime(predictThePictureResponseData.value!!.pictures[0].publishDate)
                            Log.e(
                                "PREDICT_THE_PICTURE_CURRENT_QUESTION_TIME",
                                "PREDICT_THE_PICTURE_CURRENT_QUESTION_TIME" + Constants.PREDICT_THE_PICTURE_CURRENT_QUESTION_TIME
                                )
                            if(predictThePictureResponseData.value!!.pictures.size > 1){

                                Constants.PREDICT_THE_PICTURE_NEXT_QUESTION_TIME = Utility.getTime(predictThePictureResponseData.value!!.pictures[1].publishDate)

                            }

                        }
                    } else {


                        Log.e("Error ","Error "+response.errorBody()?.string());
                        val jsonObject = JSONObject(response.errorBody()?.string())
                        var msg: String = ""

                        if (jsonObject.has("message")) {

                            msg = (jsonObject.getString("message"))
                            Utility.hideProgressDialog(this@HomeScreenActivity)
                            Utility.showDialog(this@HomeScreenActivity, "Error !!", "" + msg, "Ok", "Cancel")
                        } else {

                            Utility.hideProgressDialog(this@HomeScreenActivity)
                            Utility.showDialog(this@HomeScreenActivity, "Error !!", "Server Error.", "Ok", "Cancel")
                        }





                        /*

                            Utility.hideProgressDialog(this@HomeScreenActivity)
                            Utility.showDialog(
                                this@HomeScreenActivity,
                                "Error !!",
                                "Server Error.",
                                "Ok",
                                "Cancel"
                            )*/
                      /*  }*/
                    }
                } else {

                    Utility.hideProgressDialog(this@HomeScreenActivity)

                    Log.e("Error ","Error "+response.errorBody()?.string());
                    val jsonObject = JSONObject(response.errorBody()?.string())
                    var msg: String = ""

                    if (jsonObject.has("message")) {

                        msg = (jsonObject.getString("message"))
                        Utility.hideProgressDialog(this@HomeScreenActivity)
                        Utility.showDialog(this@HomeScreenActivity, "Error !!", "" + msg, "Ok", "Cancel")
                    } else {

                        Utility.hideProgressDialog(this@HomeScreenActivity)
                        Utility.showDialog(this@HomeScreenActivity, "Error !!", "Server Error.", "Ok", "Cancel")
                    }



                    /* Utility.showDialog(
                         this@HomeScreenActivity,
                         "Error !!",
                         "Server Error.",
                         "Ok",
                         "Cancel"
                     )*/

                }

            }
        })

    }

    public fun updateImage() {

        headerImage.setImageBitmap(null)
        val requestOptions =
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                .placeholder(R.drawable.logo1)
                .error(R.drawable.logo1)
                .dontAnimate()

        Glide.with(headerImage!!.getContext())
            .asBitmap()
            .load(Constants.BASE_URL + "/" + Constants.USER_IMAGE)
            .apply(requestOptions)
            .into(headerImage);

    }

    private fun getFCMToken() {

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("HomeScreen", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Constants.FCM_TOKEN = token
            var updateFCMTokenInputData = UpdateFCMTokenInputData(Constants.FCM_TOKEN)
            updateFCMTokenToServer(this@HomeScreenActivity, updateFCMTokenInputData)

            Log.e("HomeScreen", "FCM " + token)
            Toast.makeText(baseContext, "" + token, Toast.LENGTH_SHORT).show()
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_screen, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_screen)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        Log.e("item_id", item.itemId.toString())
        item.setChecked(true);

        drawerLayout.closeDrawers();
        when (item.itemId) {

            R.id.nav_home_icon -> navController.navigate(R.id.nav_now_and_then)


            R.id.nav_profile_icon -> navController.navigate(R.id.nav_profile)


            R.id.nav_friends_icon -> navController.navigate(R.id.nav_friends)


            R.id.nav_purchase_icon -> navController.navigate(R.id.nav_purchase_flairs)


            R.id.nav_settings_icon -> navController.navigate(R.id.nav_settings)


        }
        return true;

    }


    fun updateFCMTokenToServer(
        context: Context,
        updateFCMTokenInputData: UpdateFCMTokenInputData,
    ) {

        val call = RetrofitClient.apiInterface.updateFCMTokenToServer(
            Constants.AUTH_TOKEN,
            Constants.USER_AUTHTOKEN,
            updateFCMTokenInputData
        )
        call.enqueue(object : Callback<UpdateFCMTokenResponseData> {
            override fun onFailure(call: Call<UpdateFCMTokenResponseData>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<UpdateFCMTokenResponseData>,
                response: Response<UpdateFCMTokenResponseData>
            ) {

                if (response.body() != null) {

                    if (response.body().toString() != null) {

                        val data = response.body()
                        Log.e("data ", "data " + data);
                        //LoginActivityRepository.loginResponseData.value = data!!

                    } else {

                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")
                    }
                } else {
/*
                    val jsonObject = JSONObject(response.errorBody()?.string())
                    var msg: String = ""

                    if (jsonObject.has("message")) {

                        msg = (jsonObject.getString("message"))
                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "" + msg, "Ok", "Cancel")
                    } else {

                        Utility.hideProgressDialog(context)
                        Utility.showDialog(context, "Error !!", "Server Error.", "Ok", "Cancel")
                    }


                */
                }

            }
        })

    }
}