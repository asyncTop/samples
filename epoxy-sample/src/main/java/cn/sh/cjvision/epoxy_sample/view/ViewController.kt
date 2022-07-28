package cn.sh.cjvision.epoxy_sample.view

import cn.sh.cjvision.epoxy_sample.databinding.ArticleListEntity
import com.airbnb.epoxy.TypedEpoxyController

class ViewController : TypedEpoxyController<List<ArticleListEntity>>() {
    override fun buildModels(data: List<ArticleListEntity>?) {
        data?.forEach {
            ItemViewModel_()
                .id(modelCountBuiltSoFar)
                .name(it.shareUser)
                .content(it.title)
                .date(it.niceDate)
                .addTo(this)
        }
    }
}