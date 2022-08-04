package cn.sh.cjvision.lottie_sample;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.google.android.material.appbar.AppBarLayout;

public class CollapsibleToolbar extends MotionLayout implements AppBarLayout.OnOffsetChangedListener {

    public CollapsibleToolbar(@NonNull Context context) {
        this(context, null);
    }

    public CollapsibleToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapsibleToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        setProgress(-(verticalOffset / 1.0f) / appBarLayout.getTotalScrollRange());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof AppBarLayout)
            ((AppBarLayout) getParent()).addOnOffsetChangedListener(this);
    }
}
