package cn.sh.cjvision.epoxy_sample.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import cn.sh.cjvision.epoxy_sample.R
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ItemView : FrameLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
       LayoutInflater.from(context).inflate(R.layout.item_view, this, true)

        tvContent = findViewById(R.id.tvContent)
        tvName = findViewById(R.id.tvName)
        tvDate = findViewById(R.id.tvDate)
    }

     var tvContent :TextView
     var tvName :TextView
     var tvDate :TextView

    @TextProp
    fun setContent(content:CharSequence) {
        tvContent.text = content
    }
    @TextProp
    fun setName(name:CharSequence) {
        tvName.text = name
    }
    @TextProp
    fun setDate(date:CharSequence) {
        tvDate.text = date
    }
}