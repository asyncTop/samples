package cn.sh.cjvision.material.fragment

import cn.sh.cjvision.material.R
import cn.sh.cjvision.material.databinding.FragmentOtherBinding
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils

class OtherFragment : BaseFragment<FragmentOtherBinding>() {
    private var open = false
    override fun initView() {
        binding.apply {
            chipGroupSingleLine.setOnCheckedStateChangeListener { group, checkedIds ->
                LogUtils.d("group:$group   ids:$checkedIds")
            }
            chip2.setOnCloseIconClickListener {
                ToastUtils.showShort("close")
            }
            button.setOnClickListener {
                button.isExpanded = !open
                    open = !open
            }
            tvFab.setOnClickListener {
                button.isExpanded = false
                open = false
            }
            tvFab1.setOnClickListener {

            }
        }
    }

    override fun layoutId() = R.layout.fragment_other
}