package com.gusi.neontv;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Date: 2017-11-07 13:48
 * 霓虹灯TextView
 *
 * @author ylw
 */
public class NeonTextView extends TextView {

  private LinearGradient mGradient;
  /**
   * colors
   */
  private int[] mGradientColors = {
      Color.rgb(255, 227, 132), Color.rgb(64, 224, 205), Color.rgb(0, 255, 255),
      Color.rgb(153, 51, 250), Color.rgb(218, 112, 214)
  };
  /**
   * 霓虹灯效果下，变化时间
   */
  private int mExchangeTime = 1000;
  /**
   * 是否是霓虹灯效果  false 类似于彩虹效果
   */
  private boolean mOpenNeon;
  /**
   * 变色方向
   * <enum name="horizontal" value="0" />
   * <enum name="vertical" value="1" />
   */
  private int mOrientation = 0;

  public NeonTextView(Context context) {
    this(context, null);
  }

  public NeonTextView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public NeonTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NeonTextView);
    try {
      CharSequence[] colorArray = array.getTextArray(R.styleable.NeonTextView_colors);
      array.getTextArray(R.styleable.NeonTextView_colors);
      if (colorArray != null && colorArray.length > 0) {
        for (int len = colorArray.length, i = 0; i < len; i++) {
          mGradientColors[i] = Color.parseColor(colorArray[i].toString());
        }
      }

      mExchangeTime = array.getInt(R.styleable.NeonTextView_exchange_time, 1000);

      mOpenNeon = array.getBoolean(R.styleable.NeonTextView_open_neon, true);
      mOrientation = array.getInt(R.styleable.NeonTextView_neon_orientation, 0);
    } finally {
      array.recycle();
    }
  }

  @Override protected void onDraw(Canvas canvas) {
    Layout layout = getLayout();
    int height = layout.getHeight();
    //Shader配置   http://hencoder.com/ui-1-2/
    mGradient = new LinearGradient(0, 0, (mOrientation == 0) ? layout.getWidth() : 0, height,
        mGradientColors, null, Shader.TileMode.MIRROR);

    TextPaint paint = getPaint();
    paint.setShader(mGradient);
    super.onDraw(canvas);
    if (mOpenNeon) {
      exchangeOrder();
    }
  }

  private void exchangeOrder() {
    if (isAttachedToWindow()) {
      postDelayed(new Runnable() {
        @Override public void run() {
          invalidate();
        }
      }, mExchangeTime);

      int[] exchangeColors = new int[mGradientColors.length];
      for (int len = mGradientColors.length, i = 0; i < len; i++) {
        exchangeColors[i] = mGradientColors[(i + 1) == len ? 0 : (i + 1)];
      }
      mGradientColors = exchangeColors;
    }
  }
}
