package cn.sh.cjvision.epoxy_sample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cn.sh.cjvision.epoxy_sample.MainViewModel
import cn.sh.cjvision.epoxy_sample.R
import cn.sh.cjvision.epoxy_sample.databinding.ActivityViewBinding
import cn.sh.cjvision.lib_common.binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewActivity : AppCompatActivity() {
    private val binding by binding<ActivityViewBinding>(R.layout.activity_view)
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val controller = ViewController()

        binding.apply {
            recycle.apply {
                layoutManager = LinearLayoutManager(this@ViewActivity)
                addItemDecoration(DividerItemDecoration(this@ViewActivity,LinearLayoutManager.VERTICAL))
            }
        }
        binding.recycle.adapter = controller.adapter


        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.list.collect {
                    it?.data?.datas?.let { list ->
                        controller.setData(list)
                    }
                }
            }
        }
    }
}