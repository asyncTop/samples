package cn.sh.cjvision.epoxy_sample

import cn.sh.cjvision.epoxy_sample.databinding.ItemHomeBinding
import com.airbnb.epoxy.EpoxyDataBindingLayouts
import com.airbnb.epoxy.Typed2EpoxyController
@EpoxyDataBindingLayouts(R.layout.item_home)
class EpoxyController :
    Typed2EpoxyController<MutableList<String>, (int: Int, model: String) -> Unit>() {
    override fun buildModels(
        data: MutableList<String>,
        clickCallback: (int: Int, model: String) -> Unit
    ) {
        data.forEach {
            ItemHomeBindingModel_()
                .id(modelCountBuiltSoFar)
                .title(it)
                .onBind { model, view, position ->
                    (view.dataBinding as ItemHomeBinding).setOnClick { _ ->
                        clickCallback.invoke(position, it)
                    }
                }
                .addTo(this)
        }
    }
}