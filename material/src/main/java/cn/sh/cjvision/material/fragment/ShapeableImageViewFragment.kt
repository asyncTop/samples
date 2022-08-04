package cn.sh.cjvision.material.fragment

import android.graphics.Color
import android.graphics.Paint
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import cn.sh.cjvision.material.R
import cn.sh.cjvision.material.databinding.FragmentShapeableImageViewBinding
import com.google.android.material.shape.*

class ShapeableImageViewFragment : BaseFragment<FragmentShapeableImageViewBinding>() {
    override fun initView() {
        val shapeAppearanceModel3 = ShapeAppearanceModel.builder().apply {
            setAllCorners(RoundedCornerTreatment())
            setAllCornerSizes(20f)
            setBottomEdge(object : TriangleEdgeTreatment(20f, false) {
                // center 位置 ， interpolation 角的大小
                override fun getEdgePath(length: Float, center: Float, interpolation: Float, shapePath: ShapePath) {
                    super.getEdgePath(length, 35f, interpolation, shapePath)
                }
            })
        }.build()
        val drawable3 = MaterialShapeDrawable(shapeAppearanceModel3).apply {
            setTint(ContextCompat.getColor(requireContext(), R.color.teal_200))
            paintStyle = Paint.Style.FILL
        }
        (binding.code.parent as ViewGroup).clipChildren = false // 不限制子view在其范围内
        binding.code.setTextColor(Color.WHITE)
        binding.code.background = drawable3

        binding.roundedCornersCard.setOnClickListener {

        }
        binding.roundedCornersCard.progress = 0.5f
    }

    override fun layoutId(): Int = R.layout.fragment_shapeable_image_view
}