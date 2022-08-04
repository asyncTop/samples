package cn.sh.cjvision.epoxy_sample.epoxyrecyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import cn.sh.cjvision.epoxy_sample.*
import cn.sh.cjvision.epoxy_sample.databinding.FragmentEpoxyRecyclerViewBinding
import com.airbnb.epoxy.EpoxyDataBindingLayouts
import com.blankj.utilcode.util.ToastUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
@EpoxyDataBindingLayouts(R.layout.item_epoxy_recycler)
@AndroidEntryPoint
class EpoxyRecyclerViewFragment : Fragment() {


    private var _binding: FragmentEpoxyRecyclerViewBinding? = null

    private val binding get() = _binding!!
    private val viewModel by viewModels<EpoxyViewModel>()
//    private val controller = DataBindingController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpoxyRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.recycler.setController(controller)
        lifecycleScope.launchWhenCreated {
            viewModel.articleTop.collect {
                it?.let { entity ->
//                    controller.setData(entity.data)
//                    val models = ArrayList<ItemEpoxyRecyclerBindingModel_>()
//                    binding.recycler.setModels(models)

                    binding.recycler.withModels {
                        entity.data?.forEach { top ->
                            itemEpoxyRecycler {
                                id("epoxy")
                                title(top.title)
                                    .onClick { _ -> ToastUtils.showShort(top.title) }
                            }
                            layoutCustomView {
                                id("epoxy")
                                title(top.title)
                                author(top.author)
                                    .time(top.niceDate)
                                    .onClick { _ -> ToastUtils.showShort("${top.title}  ${top.niceDate}") }
                            }
                        }
                    }
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