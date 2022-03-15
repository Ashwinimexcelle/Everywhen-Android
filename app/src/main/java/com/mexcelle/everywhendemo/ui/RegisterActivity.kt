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
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.databinding.RegisterActivityBinding
import com.mexcelle.everywhendemo.util.Constants
import com.mexcelle.everywhendemo.util.Utility
import com.mexcelle.everywhendemo.viewModel.RegisterScreenViewModel
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    lateinit var binding: RegisterActivityBinding
    lateinit var registerScreenViewModel: RegisterScreenViewModel
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private var RC_SIGN_IN: Int = 1001


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerScreenViewModel = ViewModelProvider(this).get(RegisterScreenViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.setLifecycleOwner(this)
        binding.registerScreenViewModel = registerScreenViewModel

        Init()

        register_login_btn.setOnClickListener {

            if (Utility.isOnline(this@RegisterActivity)) {

                Utility.showProgressDialog(this@RegisterActivity)
                registerScreenViewModel.signup(this@RegisterActivity)!!
                    .observe(this, Observer { signupResponseData ->

                        Utility.showDialog(
                            this,
                            "Register !!",
                            "Success" /*+ signupResponseData?.message*/,
                            "Ok",
                            "Cancel"
                        )
                        Utility.hideProgressDialog(this@RegisterActivity)
                        val intent = Intent(this@RegisterActivity, OTPActivity::class.java)
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




        google_rl.setOnClickListener {

           /* val intent = Intent(this@RegisterActivity, HomeScreenActivity::class.java)
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
            val intent = Intent(this@RegisterActivity, IntoductionActivity::class.java)
            startActivity(intent)
            Constants.USER_NAME = account.displayName.toString()
            Log.e("LoginActicity", "Success")


        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("LoginActicity", "signInResult:failed code=" + e.statusCode)
            //updateUI(null)
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
        }
    }



    private fun Init() {

        Utility.setRegular(this@RegisterActivity,register_txt1)
        Utility.setExodar(this@RegisterActivity,register_txt2)
        Utility.setRegular(this@RegisterActivity,register_txt3)
        Utility.setRegular(this@RegisterActivity,register_txt4)
        Utility.setRegular(this@RegisterActivity,register_email_txt4)
        Utility.setRegular(this@RegisterActivity,register_password_txt4)
        Utility.setSemibold(this@RegisterActivity,register_login_btn)
        Utility.setSemibold(this@RegisterActivity,google_btn)

        Utility.setRegular(this@RegisterActivity,register_username_et)
        Utility.setRegular(this@RegisterActivity,register_email_id_et)
        Utility.setRegular(this@RegisterActivity,register_password_et)

        Utility.setSolidFontAwesome(this@RegisterActivity,register_username_tv)
        Utility.setSolidFontAwesome(this@RegisterActivity,register_email_id_tv)
        Utility.setSolidFontAwesome(this@RegisterActivity,register_password_tv)

    }
}