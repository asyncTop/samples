package cn.sh.cjvision.lottie_sample

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import cn.sh.cjvision.lib_common.binding
import cn.sh.cjvision.lib_common.log
import cn.sh.cjvision.lottie_sample.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by binding(R.layout.activity_main)

    private val titles = arrayOf("你是", "他的", "恧嗨","测试","发财")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val adapter = PageAdapter(listOf("1", "2", "3","4","5") as MutableList<String>, this)

        binding.pager.adapter = adapter

        val mediator =
            TabLayoutMediator(binding.tablayout, binding.pager, false, true) { tab, index ->
                tab.text = titles[index]
            }
        mediator.attach()

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                val cur = 1.0f / (adapter.itemCount-1)
                log("progress:${(positionOffset + position)*cur}   cur:$cur   position:$position offset:$positionOffset")
                binding.lottie.progress = (positionOffset + position) * cur
            }

        })

    }


    class PageAdapter(var list: MutableList<String>, var context: Context) :
        RecyclerView.Adapter<PagerHolder>() {

        override fun getItemViewType(position: Int): Int {
            return position
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerHolder {
            val view = TextView(context)
            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            view.layoutParams = params

            view.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    if (viewType == 0) R.color.teal_200 else if (viewType == 1) R.color.teal_700 else R.color.purple_200
                )
            )
            return PagerHolder(view)
        }

        override fun onBindViewHolder(holder: PagerHolder, position: Int) {
//            (holder.itemView as TextView).text = "text:$position"
        }

        override fun getItemCount(): Int {
            return list.size
        }

    }

    class PagerHolder(var itemView: View) : RecyclerView.ViewHolder(itemView)
}