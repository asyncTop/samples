package cn.sh.cjvision.epoxy_sample.databinding

import cn.sh.cjvision.epoxy_sample.ItemArticleBindingModel_
import com.airbnb.epoxy.TypedEpoxyController

class ArticleController : TypedEpoxyController<List<ArticleListEntity>>() {
    override fun buildModels(data: List<ArticleListEntity>) {
        data.forEach {
            ItemArticleBindingModel_()
                .id(modelCountBuiltSoFar)
                .data(it)
                .addTo(this)
        }

    }
}