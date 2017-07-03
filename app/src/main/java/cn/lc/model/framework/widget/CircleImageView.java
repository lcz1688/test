package cn.lc.model.framework.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends AppCompatImageView {
	private ShapeDrawable drawable;
	private int radias;
	private BitmapShader shader;
	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CircleImageView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas cns) {
		if (radias == 0)
			radias = Math.min(getWidth(), getHeight());
		initDrawable();
		if (drawable != null) {
			drawable.setBounds((getWidth() >> 1) - (radias >> 1), (getHeight() >> 1) - (radias >> 1), (getWidth() >> 1) + (radias >> 1), (getHeight() >> 1) + (radias >> 1));
			drawable.getPaint().setShader(shader);
			drawable.draw(cns);
		} else
			super.onDraw(cns);
	}

	private void initDrawable() {
		if (getDrawable() != null && getDrawable() instanceof BitmapDrawable) {
			Bitmap bmp = Bitmap.createScaledBitmap(((BitmapDrawable) getDrawable()).getBitmap(), radias, radias, true);
			if (bmp != null) {
				shader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
				drawable = new ShapeDrawable(new OvalShape());
			}
		}

	}

}