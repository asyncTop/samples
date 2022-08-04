package cn.sh.cjvision.privacy_policy

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.sh.cjvision.privacy_policy.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicyActivity : AppCompatActivity() {


    companion object {
        const val defaultUrl = "http://www.cjvision.sh.cn/privacy.html"
        fun newInstance(context: Context, url: String = defaultUrl): Intent {
            val intent = Intent(context, PrivacyPolicyActivity::class.java)
            intent.putExtra("url", url)
            return intent
        }
    }

   private val binding by lazy {
        ActivityPrivacyPolicyBinding.inflate(layoutInflater)
    }

    private val screenSize by lazy{
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.defaultDisplay.getRealSize(point)
        } else {
            wm.defaultDisplay.getSize(point)
        }
        return@lazy point
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val params = window.attributes
        params.height =(screenSize.y * 0.65f).toInt()
        params.width = (screenSize.x * 0.8f).toInt()
        window.attributes = params
        window.setBackgroundDrawableResource(android.R.color.transparent)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            setFinishOnTouchOutside(false)
        }
        initView()
        initData()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(binding.webView.canGoBack())
            binding.webView.goBack()
        return true
    }

    private fun initView() {
        binding.apply {
            btCancel.setOnClickListener {
                Toast.makeText(this@PrivacyPolicyActivity,"点击'同意'后方可使用本软件",Toast.LENGTH_LONG).show()
            }
            btAgree.setOnClickListener {
                setResult(RESULT_OK)
                finish()
            }
            progress.max = 100
            webView.apply {
                settings.apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
                        loadWithOverviewMode = true
                    }
                    useWideViewPort = true
                    setSupportZoom(true)
                    builtInZoomControls = false
                    loadsImagesAutomatically = true
                    textZoom = 100
                    setInitialScale(100)
                }
                requestFocus()
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        binding.progress.progress = newProgress
                    }
                }
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        binding.progress.visibility = View.GONE
                    }

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        binding.progress.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun initData() {
        val url = intent.getStringExtra("url") ?: defaultUrl
        binding.webView.loadUrl(url)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webView.apply {
            stopLoading()
            removeAllViews()
            destroy()
        }
    }
}