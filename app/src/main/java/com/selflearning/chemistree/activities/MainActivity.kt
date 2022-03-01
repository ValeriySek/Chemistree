package com.selflearning.chemistree.activities

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.selflearning.chemistree.R
import com.selflearning.chemistree.views.data.SystemBarsSize
import com.selflearning.chemistree.views.fullscreen.FragmentFullScreen

class MainActivity : AppCompatActivity(), FragmentFullScreen {

    private lateinit var bottomMarginView: View
    private lateinit var topMarginView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomMarginView = findViewById<View>(R.id.main_bottom_margin)
        topMarginView = findViewById<View>(R.id.main_top_margin)

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets ->
            val inset = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val bottomInset = inset.bottom
            val topInset = inset.top

            bottomMarginView.layoutParams.height = bottomInset
            topMarginView.layoutParams.height = topInset
//            bottomMarginView.layoutParams = LinearLayout.LayoutParams(0, bottomInset)
//            topMarginView.layoutParams = LinearLayout.LayoutParams(0, topInset)


            ViewCompat.onApplyWindowInsets(
                window.decorView,
                insets.replaceSystemWindowInsets(0, 0, 0, 0)
            )
        }
        ViewCompat.requestApplyInsets(window.decorView)

        window.navigationBarColor = Color.WHITE
        window.statusBarColor = Color.TRANSPARENT

        val navView = findViewById<BottomNavigationView>(R.id.main_bottom_nav_bar)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            hideBottomAppBar(destination, navView)
        }
    }

    private fun View.setStatusBarTopPadding(bars: SystemBarsSize) {
        val statusBarSize = bars.statusBarSize
        val navigationBarSize = bars.navigationBarSize
        updatePadding(
            top = paddingTop + statusBarSize,
            bottom = paddingBottom + navigationBarSize
        )
    }

    private fun Activity.isKeyboardAppeared(bottomInset: Int): Boolean {
        return bottomInset / resources.displayMetrics.heightPixels.toDouble() > .25
    }

    private fun hideBottomAppBar(destination: NavDestination, navView: BottomNavigationView) {
        navView.isVisible = when (destination.id) {
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_games_pack,
            R.id.navigation_notifications,
            R.id.navigation_statistic -> true
            else -> false
        }
    }

    override fun fullScreen(isFullScreen: Boolean) {
            bottomMarginView.isVisible = !isFullScreen
            topMarginView.isVisible = !isFullScreen
    }
}