package cn.sh.cjvision.epoxy_sample.custom

import cn.sh.cjvision.epoxy_sample.entity.ArticleTop
import com.airbnb.epoxy.TypedEpoxyController
import com.blankj.utilcode.util.ToastUtils

class CustomController : TypedEpoxyController<List<ArticleTop>>() {
    override fun buildModels(data: List<ArticleTop>) {
        data.forEach {
            CustomViewModel_()
                .id(modelCountBuiltSoFar)
                .title(it.title)
                .author(it.author)
                .onClick {
                    v->
                    ToastUtils.showShort(it.title)
                }
                .time(it.niceDate)
                .addTo(this)
        }
    }
}