package cn.sh.cjvision.epoxy_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cn.sh.cjvision.epoxy_sample.custom.CustomViewFragment
import cn.sh.cjvision.epoxy_sample.databinding.ActivityEpoxyBinding
import cn.sh.cjvision.epoxy_sample.databinding.DataBindingFragment
import cn.sh.cjvision.epoxy_sample.epoxyrecyclerview.EpoxyRecyclerViewFragment
import cn.sh.cjvision.lib_common.binding
import cn.sh.cjvision.privacy_policy.PrivacyPolicyActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpoxyActivity : AppCompatActivity() {

    val binding: ActivityEpoxyBinding by binding(R.layout.activity_epoxy)
    private var showingFragment: Fragment? = null

    private val items =
        mutableListOf("with custom view", "with databinding", "with epoxy_recyclerview")

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = EpoxyController()

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = controller.adapter

        controller.setData(items) { position, model ->
            when (position) {
                0 -> changeFragment(CustomViewFragment())
                1 -> changeFragment(DataBindingFragment())
                2 -> launcher.launch(PrivacyPolicyActivity.newInstance(this@EpoxyActivity))
//                2->changeFragment(EpoxyRecyclerViewFragment())
            }
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