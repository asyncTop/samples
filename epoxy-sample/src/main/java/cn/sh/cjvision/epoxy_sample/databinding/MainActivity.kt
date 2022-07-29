package cn.sh.cjvision.epoxy_sample.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import cn.sh.cjvision.epoxy_sample.R
import cn.sh.cjvision.epoxy_sample.maverick.MaverickActivity
import cn.sh.cjvision.epoxy_sample.view.ViewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController(R.id.nav_host_fragment)
        )

        binding.toolbar.apply {
            title = "with databinding"
            navigationIcon = getDrawable(R.drawable.ic_launcher_foreground)
            setNavigationOnClickListener {
//                startActivity(Intent(this@MainActivity, ViewActivity::class.java))
                startActivity(Intent(this@MainActivity, MaverickActivity::class.java))
            }
        }
    }
}