package cn.sh.cjvision.navigation_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import cn.sh.cjvision.lib_common.binding
import cn.sh.cjvision.navigation_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val controller: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavigationUI.setupWithNavController(binding.bottomNav, controller)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}