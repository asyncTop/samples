package cn.sh.cjvision.epoxy_sample

import androidx.fragment.app.Fragment
import com.airbnb.epoxy.EpoxyController

abstract class BaseFragment : Fragment() {
    abstract val controller: EpoxyController
}