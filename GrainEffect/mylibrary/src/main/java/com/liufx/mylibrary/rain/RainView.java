package com.liufx.mylibrary.rain;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.liufx.mylibrary.R;


public class RainView extends BaseView {
	// 多个 “雨点” 对象。
	private ArrayList<RainItem> list = new ArrayList<RainItem>();
	// 以下4个值，均为默认值。
	private int rainNum = 80;  // “雨点”个数。
	private int size;
	private int rainColor;
	private boolean randColor;

	public RainView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// 加载，解析 样式属性。res/values/attrs.xml
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.RainView);

		rainNum = ta.getInteger(R.styleable.RainView_rainNum, 80);
		size = ta.getInteger(R.styleable.RainView_size, 20);
		rainColor = ta.getInteger(R.styleable.RainView_rainColor, 0xffffffff);
		randColor = ta.getBoolean(R.styleable.RainView_randColor, false);
		ta.recycle();
	}

	public RainView(Context context) {
		super(context);
	}

	@Override
	protected void drawSub(Canvas canvas) {
		for (RainItem item : list) {
			item.draw(canvas);
		}
	}

	@Override
	protected void logic() {
		for (RainItem item : list) {
			item.move();
		}
	}

	@Override
	protected void init() {
		for (int i = 0; i < rainNum; i++) {
			RainItem item = new RainItem(getWidth(), getHeight(), size,
					rainColor, randColor);
			list.add(item);
		}
	}

}