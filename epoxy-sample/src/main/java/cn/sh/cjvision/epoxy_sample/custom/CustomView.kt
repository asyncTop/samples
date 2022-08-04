package cn.sh.cjvision.epoxy_sample.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.Nullable
import cn.sh.cjvision.epoxy_sample.R
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CustomView : FrameLayout {
    val title: TextView
    val author: TextView
    val time: TextView
    val view: View

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_custom_view, this, false)
        title = view.findViewById(R.id.tvTitle)
        author = view.findViewById(R.id.tvAuthor)
        time = view.findViewById(R.id.tvTime)
        addView(view)
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        this.title.text = title
    }

    @TextProp
    fun setAuthor(author: CharSequence) {
        this.author.text = author
    }

    @TextProp
    fun setTime(time: CharSequence) {
        this.time.text = time
    }

    @CallbackProp
    fun setOnClick(clickListener: OnClickListener?) {
        view.setOnClickListener(clickListener)
    }
}