package cn.sh.cjvision.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import cn.sh.cjvision.material.databinding.ActivityMainBinding
import cn.sh.cjvision.material.databinding.ItemHomeBinding
import cn.sh.cjvision.material.fragment.OtherFragment
import cn.sh.cjvision.material.fragment.ShapeableImageViewFragment
import com.airbnb.epoxy.EpoxyDataBindingLayouts
import com.airbnb.epoxy.TypedEpoxyController
@EpoxyDataBindingLayouts(R.layout.item_home)
class MainActivity : AppCompatActivity() {

    private var showingFragment: Fragment? = null

    lateinit var binding: ActivityMainBinding

    val list = mutableListOf("ShapeableImageView","button","Other")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            recycler.withModels {
                list.forEach {
                    itemHome {
                        id(it)
                        title(it)
                        onClick {
                            v-> jump(it)
                        }
                    }
                }
            }
        }
    }

    private fun jump(it: String) {
        when(it){
            "ShapeableImageView"->changeFragment(ShapeableImageViewFragment())

            "Other"->changeFragment(OtherFragment())
        }
    }


    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .show(fragment)
            .commit()
        showingFragment = fragment
    }

    override fun onBackPressed() {
        if (showingFragment != null) {
            supportFragmentManager.beginTransaction()
                .remove(showingFragment!!)
                .commit()
            showingFragment = null
        } else
            super.onBackPressed()

    }
}