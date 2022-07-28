package cn.sh.cjvision.epoxy_sample.databinding

data class ArticleEntity(
    var curPage: Int, var datas: List<ArticleListEntity>,
    var offset: Int, var over: Boolean, var pageCount: Int, var size: Int, var total: Int
)