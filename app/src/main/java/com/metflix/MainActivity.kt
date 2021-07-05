package com.metflix

import android.os.Bundle
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.metflix.common.BindingActivity
import com.metflix.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>() {
    override fun getLayoutResId() = R.layout.activity_main
}