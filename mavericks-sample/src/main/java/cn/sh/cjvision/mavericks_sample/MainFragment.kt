package cn.sh.cjvision.mavericks_sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cn.sh.cjvision.mavericks_sample.databinding.FragmentHotKeyBinding
import com.airbnb.mvrx.*
import com.drake.brv.utils.divider
import com.drake.brv.utils.linear
import com.drake.brv.utils.models
import com.drake.brv.utils.setup

class MainFragment : Fragment(), MavericksView {

    private val TAG = this.javaClass.simpleName

    private val viewModel: MainViewModel by fragmentViewModel()
    private var binding:FragmentHotKeyBinding?=null

    override fun invalidate() = withState(viewModel) { state ->
        when(state.request){
            is Loading->{
                Log.d(TAG,"请求中")
                binding?.refresh?.refreshing()
                return@withState
            }
            is Success->{
                binding?.refresh?.finishRefresh()
                binding?.recycle?.models = state.hotKeys
            }
            is Fail->{
                binding?.refresh?.finishRefresh()
            }
            else->{
                binding?.refresh?.finishRefresh()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.onAsync(MainState::request, deliveryMode = uniqueOnly(), onFail = {
//            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//                Snackbar.make(
//                    binding!!.root,
//                    "请求失败",
//                    Snackbar.LENGTH_INDEFINITE
//                )
//                    .apply {
//                        setAction("DISMISS") {
//                            this.dismiss()
//                        }
//                        show()
//                    }
//            }
//        }, onSuccess = {
//            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//                Snackbar.make(
//                    binding!!.root,
//                    "请求成功",
//                    Snackbar.LENGTH_INDEFINITE
//                )
//                    .apply {
//                        setAction("DISMISS") {
//                            this.dismiss()
//                        }
//                        show()
//                    }
//            }
//        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hot_key,container,false)
        binding = DataBindingUtil.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            recycle.linear().divider(drawable = R.drawable.devider)
                .setup {
                    addType<HotKeyEntity>(R.layout.item_hotkey)
                }

            refresh.apply {
                onRefresh {
                    Log.d(TAG,"onRefresh")
                    viewModel.getHotKeys()
                }
                setEnableAutoLoadMore(false)
                autoRefresh()
            }
        }


    }
}