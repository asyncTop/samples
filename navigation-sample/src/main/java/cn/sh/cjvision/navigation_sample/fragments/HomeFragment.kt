package cn.sh.cjvision.navigation_sample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cn.sh.cjvision.lib_webview.CJWebChromeClient
import cn.sh.cjvision.lib_webview.CJWebViewClient
import cn.sh.cjvision.lib_webview.WebViewCacheHolder
import cn.sh.cjvision.navigation_sample.R
import cn.sh.cjvision.navigation_sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = DataBindingUtil.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val webview = WebViewCacheHolder.acquireWebViewInternal(requireContext())
            webview.loadUrl("http://192.168.0.173:8088/dzbp4/web/#/page/2596621194331283462?SCHOOLID=2367679302641680457&XQID=2368012454664904704&JXLID=2368012454664904723&JSID=2605231091932423559&TIME=0&ISWEB=1")
            webview.show {
                webViewClient = object : CJWebViewClient() {

                }
                webChromeClient = object : CJWebChromeClient() {

                }
            }
            container.addView(webview)
        }
    }
}