package cn.sh.cjvision.epoxy_sample.custom

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.sh.cjvision.epoxy_sample.EpoxyViewModel
import cn.sh.cjvision.epoxy_sample.databinding.FragmentCustomViewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class CustomViewFragment : Fragment() {

    private var _binding: FragmentCustomViewBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<EpoxyViewModel>()

    lateinit var controller: CustomController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = CustomController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomViewBinding.inflate(inflater, container, false)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}