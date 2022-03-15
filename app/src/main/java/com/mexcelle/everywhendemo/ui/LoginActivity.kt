package com.mexcelle.everywhendemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.databinding.LoginActivityBinding
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.util.prefs
import com.mexcelle.everywhendemo.viewModel.LoginScreenViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var binding: LoginActivityBinding
    lateinit var loginScreenViewModel: LoginScreenViewModel
    private var RC_SIGN_IN: Int = 1001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginScreenViewModel = ViewModelProvider(this).get(LoginScreenViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.setLifecycleOwner(this)
        binding.loginScreenViewModel = loginScreenViewModel
        Init()

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        val account = GoogleSignIn.getLastSignedInAccount(this)

        if(account != null){

            val intent = Intent(this@LoginActivity, HomeScreenActivity::class.java)
            startActivity(intent)

        }else{


        }
        //updateUI(account)

        register_btn.setOnClickListener {

            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)

        }

        login_btn.setOnClickListener {

           /* val intent = Intent(this@LoginActivity, IntoductionActivity::class.java)
            startActivity(intent)*/


            if (Utility.isOnline(this@LoginActivity)) {

                Utility.showProgressDialog(this@LoginActivity)
                loginScreenViewModel.login(this@LoginActivity)!!
                    .observe(this, Observer { loginResponseData ->

                       /* Utility.showDialog(
                            this,
                            "Login !!",
                            "Success" *//*+ signupResponseData?.message*//*,
                            "Ok",
                            "Cancel"
                        )*/
                        Utility.hideProgressDialog(this@LoginActivity)
                        Constants.USER_AUTHTOKEN = loginResponseData!!.token
                        Constants.USER_ID = loginResponseData!!.id
                        Constants.USER_NAME = loginResponseData!!.username
                        Constants.USER_EMAILID = loginResponseData!!.email
                        Constants.USER_ROLE = loginResponseData!!.role
                        Constants.USER_SURVEY_COMPLETED = loginResponseData!!.surveyCompleted
                        Constants.USER_SURVEY_SKIP = loginResponseData!!.skipSurveyCount
                        Constants.USER_QUIZ_COMPLETED = loginResponseData!!.quizCompleted
                        Constants.USER_QUIZ_SKIP = loginResponseData!!.skipQuizCount
                        Constants.USER_TODAYS_TRAIL = loginResponseData!!.stats.todaysTrials
                        Constants.USER_TOTAL_PREDICT_PICTURE = loginResponseData!!.stats.totalPredictPictures
                        Constants.USER_CORRECT_PREDICT_PICTURE = loginResponseData!!.stats.correctPredictPictures
                        Constants.USER_TOTAL_STREAKS = loginResponseData!!.stats.totalStreaks
                        Constants.USER_STREAK_PROGRESS = loginResponseData!!.stats.streakProgress
                        Constants.USER_POINTS = loginResponseData!!.stats.points
                        Constants.USER_IS_NOW_AND_THEN_UNLOKED = loginResponseData!!.stats.unlockNowAndThen

                        prefs.initUsername = Constants.USER_NAME
                        prefs.initAuthToken = Constants.USER_AUTHTOKEN
                        prefs.initEmailid = Constants.USER_EMAILID

                        val intent = Intent(this@LoginActivity, IntoductionActivity::class.java)
                        startActivity(intent)
                    })

            } else {

                Utility.showDialog(
                    this,
                    "Network Error !!",
                    "Please check your network connection.",
                    "Ok",
                    "Cancel"
                )

            }


        }

        forgot_password_btn.setOnClickListener {

            val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        google_rl.setOnClickListener {

            /*val intent = Intent(this@LoginActivity, HomeScreenActivity::class.java)
            startActivity(intent)*/

            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
           // updateUI(account)
            val intent = Intent(this@LoginActivity, IntoductionActivity::class.java)
            startActivity(intent)
            Constants.USER_NAME = account.displayName.toString()
            Log.e("LoginActicity", "Success")


        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("LoginActicity", "signInResult:failed code=" + e.statusCode)
            //updateUI(null)
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
        }
    }
    private fun Init() {


        Utility.setRegular(this@LoginActivity, login_txt1)
        Utility.setExodar(this@LoginActivity, login_txt2)
        Utility.setRegular(this@LoginActivity, login_txt3)
        Utility.setRegular(this@LoginActivity, login_txt4)
        Utility.setRegular(this@LoginActivity, password_txt4)
        Utility.setSemibold(this@LoginActivity, login_btn)
        Utility.setRegular(this@LoginActivity, register_btn)
        Utility.setRegular(this@LoginActivity, forgot_password_btn)
        Utility.setRegular(this@LoginActivity, login_username_et)
        Utility.setRegular(this@LoginActivity, password_et)
        Utility.setSolidFontAwesome(this@LoginActivity, login_username_tv)
        Utility.setSolidFontAwesome(this@LoginActivity, password_tv)
        Utility.setSemibold(this@LoginActivity, google_btn)


    }
}