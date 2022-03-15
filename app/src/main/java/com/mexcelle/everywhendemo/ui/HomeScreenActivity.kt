package com.mexcelle.everywhendemo.ui

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mexcelle.everywhendemo.R
import com.mexcelle.everywhendemo.util.Utility
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.nav_header_home_screen.*


class HomeScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    public lateinit var toolbar: Toolbar
    private lateinit var navImage: ImageView
    private lateinit var screenName: TextView


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var headeName: TextView
    private lateinit var headerEmailId: TextView
    private lateinit var navigationView: NavigationView
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_screen)


        toolbar = findViewById(R.id.toolbar)
        navImage = toolbar!!.findViewById(R.id.nav_btn)
        screenName = toolbar!!.findViewById(R.id.screen_name)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val header: View = navigationView.getHeaderView(0)
        headeName = header.findViewById(R.id.user_name)
        headerEmailId = header.findViewById(R.id.user_emailid)
        Utility.setSemibold(this, headeName)
        Utility.setRegular(this, headerEmailId)
        Utility.setRegular(this, terms)
        Utility.setRegular(this, privacy)
        Utility.setRegular(this, logout)
        Utility.setExodar(this, screenName)


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

        val res = resources.getDrawable(R.drawable.btn_sidemenu)
        navImage.setImageDrawable(res)
        navImage!!.setOnClickListener {

            // If the navigation drawer is not open then open it, if its already open then close it.
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) drawerLayout.closeDrawer(Gravity.LEFT) else drawerLayout.openDrawer(
                Gravity.LEFT
            )
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
}