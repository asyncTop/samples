package cn.sh.cjvision.epoxy_sample.databinding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import cn.sh.cjvision.epoxy_sample.MainViewModel
import cn.sh.cjvision.epoxy_sample.R
import cn.sh.cjvision.lib_common.log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding: FragmentArticleBinding? = null
    val viewModel by viewModels<MainViewModel>()
    var tmpList: List<ArticleListEntity>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        binding = DataBindingUtil.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = ArticleController()

        binding?.apply {
            recycle.setController(controller)
        }

        controller.login { name, pwd ->
            log("name:$name   pwd:$pwd")
        }

        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.list.collect {
                    it?.data?.datas?.let { list ->
                        tmpList = list
                        Log.d("LIST", list.toString())
                        controller.setData(list)
                    }
                }
            }
        }
    }
}