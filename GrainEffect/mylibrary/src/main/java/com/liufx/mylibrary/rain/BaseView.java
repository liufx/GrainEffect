package com.liufx.mylibrary.rain;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
/**
 * 核心内容：
 1.绘制下雨场景的个体、雨点（直线）
 2.让直线动起来
 3.处理边界问题
 4.构造雨点对象
 5.雨点大小设置
 6.速度设置和角度设置等
 7.添加多个雨点
 8.抽离可以在 XML 中影响的属性
 9.在代码中解析样式属性并使用其控制雨点变化
 */

/**
 * 基类
 */
public abstract class BaseView extends View {

	private MyThread thread;
	private boolean running = true;

	public BaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BaseView(Context context) {
		super(context);
	}

	protected abstract void drawSub(Canvas canvas);

	protected abstract void logic();

	protected abstract void init();
	
	class MyThread extends Thread {
		@Override
		public void run() {
			init();
			while (running) {

				logic();
				postInvalidate();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected final void onDraw(Canvas canvas) {
		if (thread == null) {
			thread = new MyThread();
			thread.start();
		} else {
			drawSub(canvas);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		running = false;
		super.onDetachedFromWindow();
	}

}
