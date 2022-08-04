package cn.sh.cjvision.epoxy_sample.databinding

import cn.sh.cjvision.epoxy_sample.LayoutCustomViewBindingModel_
import cn.sh.cjvision.epoxy_sample.R
import cn.sh.cjvision.epoxy_sample.entity.ArticleTop
import com.airbnb.epoxy.EpoxyDataBindingLayouts
import com.airbnb.epoxy.TypedEpoxyController
import com.blankj.utilcode.util.ToastUtils

@EpoxyDataBindingLayouts(R.layout.layout_custom_view)
class DataBindingController : TypedEpoxyController<List<ArticleTop>>() {
    override fun buildModels(data: List<ArticleTop>) {
        data.forEach {
            LayoutCustomViewBindingModel_()
                .id(modelCountBuiltSoFar)
                .title(it.title)
                .author(it.author)
                .time(it.niceDate)
                .onClick { v->
                    ToastUtils.showShort(it.author)
                }
                .addTo(this)
        }
    }
}