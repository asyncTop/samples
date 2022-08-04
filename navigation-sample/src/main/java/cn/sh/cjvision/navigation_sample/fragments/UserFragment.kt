package cn.sh.cjvision.navigation_sample.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import cn.sh.cjvision.navigation_sample.BR
import cn.sh.cjvision.navigation_sample.R
import cn.sh.cjvision.navigation_sample.databinding.FragmentUserBinding
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState


class UserFragment : Fragment() {

//    val viewModel :UserViewModel by fragmentViewModel()
    val viewModel2 by viewModels<UserViewModel2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("UserFragment",viewModel2.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        val binding = DataBindingUtil.bind<FragmentUserBinding>(view)
        binding?.setVariable(BR.viewModel,viewModel2)
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {

            }
    }

//    override fun invalidate() = withState(viewModel2){
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("UserFragment","onDestroy")
    }
}