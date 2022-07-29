package cn.sh.cjvision.epoxy_sample.databinding

import cn.sh.cjvision.lib_common.log
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.TypedEpoxyController
import cn.sh.cjvision.epoxy_sample.ItemMainHeaderBindingModel_

class ArticleController : TypedEpoxyController<List<ArticleListEntity>>() {
    private var loadingMore = true

    private var login: ((name:String,pwd:String) -> Unit)? = null

    @AutoModel
    lateinit var loginModel_:ItemMainHeaderBindingModel_

    fun login(login: (name:String,pwd:String) -> Unit) {
        this.login = login
    }

    fun setLoadingMore() {
        this.loadingMore = !loadingMore
    }

    override fun buildModels(data: List<ArticleListEntity>) {
        loginModel_.id(modelCountBuiltSoFar)
            .onBind { _, view, _ ->
                val binding = view.dataBinding
                if(binding is ItemMainHeaderBinding){
                    binding.setOnClick {
                        log(binding.inputName.text.toString()+"---"+binding.inputPassword.text.toString())
                        login?.invoke(binding.inputName.text.toString(),binding.inputPassword.text.toString())
                    }
                }
            }
            .addTo(this)

//        ItemMainHeaderBindingModel_()
//            .id(modelCountBuiltSoFar)
//            .onClick { _ ->
//                log(username)
//            }
//            .addTo(this)
//        data.forEach {
//            val a = Random.nextInt(4)
//            if(a <= 1){
//                itemArticle {
//                    id(this@ArticleController.modelCountBuiltSoFar)
//                    data(it)
//                }
//            }else if(a <=2){
//                itemArticle2 {
//                    id(this@ArticleController.modelCountBuiltSoFar)
//                    data(it)
//                }
//            }else{
//                ItemArticle3BindingModel_()
//                    .id(modelCountBuiltSoFar)
//                    .data(it)
//                    .addTo(this)
//            }
//        }
//        LayoutLoadmoreBindingModel_()
//            .id(modelCountBuiltSoFar)
//            .addIf(loadingMore,this)
    }
}