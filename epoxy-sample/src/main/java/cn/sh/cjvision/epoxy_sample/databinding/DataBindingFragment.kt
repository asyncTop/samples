package cn.sh.cjvision.epoxy_sample.databinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.sh.cjvision.epoxy_sample.EpoxyViewModel
import cn.sh.cjvision.epoxy_sample.R
import cn.sh.cjvision.epoxy_sample.custom.CustomController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DataBindingFragment : Fragment() {

    private var _binding: FragmentDataBindingBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<EpoxyViewModel>()

    lateinit var controller: DataBindingController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = DataBindingController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBindingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recycler.layoutManager = LinearLayoutManager(context)
            recycler.adapter = controller.adapter
        }
        lifecycleScope.launchWhenCreated {
            viewModel.articleTop.collect {
                it?.let { entity->
                    controller.setData(entity.data)
                }
            }
        }

        viewModel.articleTop()
    }
}