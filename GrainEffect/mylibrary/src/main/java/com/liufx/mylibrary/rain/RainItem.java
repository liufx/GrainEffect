package com.liufx.mylibrary.rain;

import java.util.Random;
import android.graphics.Canvas;
import android.graphics.Paint;
/**
 * 抽象出一个 雨点 的类
 */
public class RainItem {

	private int width;
	private int height;

	private float startX;
	private float startY;
	private float stopX;
	private float stopY;
	private float sizeX;
	private float sizeY;
	private float opt;  // 速率
	private Paint paint;
	private Random random;  // 随机数

	private int size = 20;
	private int color;
	// 随机 雨点颜色
	private boolean randColor = false;;

	// 自定义“雨点”的 宽 和 高
	public RainItem(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}

	public RainItem(int width, int height, int size) {
		this.size = size;
		this.width = width;
		this.height = height;
		init();
	}

	public RainItem(int width, int height, int size, int color) {
		this.color = color;
		this.size = size;
		this.width = width;
		this.height = height;
		init();
	}

	public RainItem(int width, int height, int size, int color,
					boolean randColor) {
		this.randColor = randColor;
		this.color = color;
		this.size = size;
		this.width = width;
		this.height = height;
		init();
	}

	private void init() {
		random = new Random();
		// 角度 "X和Y随机。
		sizeX = 1 + random.nextInt(size / 2);
		sizeY = 10 + random.nextInt(size);
		// "雨点"X和Y随机位置。
		startX = random.nextInt(width);
		startY = random.nextInt(height);
		stopX = startX + sizeX;
		stopY = startY + sizeY;
		// 速率随机。
		opt = 0.2f + random.nextFloat();
		paint = new Paint();
		if (randColor) {
			// 颜色随机值。
			int r = random.nextInt(256);
			int g = random.nextInt(256);
			int b = random.nextInt(256);

			paint.setARGB(255, r, g, b);
		} else {
			paint.setColor(color);
		}
	}

	public void draw(Canvas canvas) {
		canvas.drawLine(startX, startY, stopX, stopY, paint);
	}

	public void move() {
		startX += sizeX * opt;
		stopX += sizeX * opt;

		startY += sizeY * opt;
		stopY += sizeY * opt;

		if (startY > height) {
			init();
		}
	}

}