package com.rabbit.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class SlideMenu extends ViewGroup {

	private static final String TAG = "SlideMenu";
	private int mMostRecentX; // x轴最后一次的偏移量

	private final int MENU_SCREEN = 1;// 菜单界面

	private final int MAIN_SCREEN = 2;// 主界面

	private int currentScreen = MAIN_SCREEN; // 当前的屏幕的显示界面 默认为主界面
	private Scroller scroller;// 用于模拟数据
	private int mTouchSlop;

	public SlideMenu(Context context, AttributeSet attrs) {
		super(context, attrs);

		scroller = new Scroller(context);
		// scroller.startScroll(startX, 0, dx, 0, duration);

		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		// 初始化菜单和主界面的宽和高
		initView(widthMeasureSpec, heightMeasureSpec);

	}

	/**
	 * 初始化菜单和主界面的宽和高
	 * 
	 * @param widthMeasureSpec
	 *            slidemenu控件的宽度测量标准
	 * @param heightMeasureSpec
	 *            slidemenu控件的高度测量标准
	 */

	private void initView(int widthMeasureSpec, int heightMeasureSpec) {
		// 菜单对象
		View menuView = this.getChildAt(0);

		menuView.measure(menuView.getLayoutParams().width, heightMeasureSpec);

		// 主界面对象
		View mainView = this.getChildAt(1);
		mainView.measure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		// 菜单对象
		View menuView = this.getChildAt(0);

		menuView.layout(-menuView.getMeasuredWidth(), 0, 0, b);

		// 主界面对象
		View mainView = this.getChildAt(1);

		mainView.layout(l, t, r, b);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

/*		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			mMostRecentX = (int) event.getX();

			break;
		case MotionEvent.ACTION_MOVE:

			// 获取最新的x轴偏移量
			int currentX = (int) event.getX();
			// 1.计算增量值 增量值 = x轴最后一次偏移量 - 当前最新的x轴偏移量；
			int delta = mMostRecentX - currentX;
			// 2.根据增量值 更新屏幕显示的位置

			// 判断移动后是否超过了两个边界

			int scrollX = getScrollX() + delta;

			if (scrollX < -getChildAt(0).getWidth()) {// 当前超出了左边界
				scrollTo(-getChildAt(0).getWidth(), 0);
			} else if (scrollX > 0) {// 超出了右边界
				scrollTo(0, 0);
			} else {
				// 正常移动
				scrollBy(delta, 0);
			}

			Log.i(TAG, "delta" + delta);

			// 3.x轴的最后一次偏移量=当前最新的x轴偏移量
			mMostRecentX = currentX;

			break;

		case MotionEvent.ACTION_UP:

			// 菜单的中心点

			int menuCenter = -getChildAt(0).getWidth() / 2;
			// 获得当前屏幕X轴的偏移量
			int _x = getScrollX();

			if (_x > menuCenter) {// 切换到主界面
				// scrollTo(0, 0);

				currentScreen = MAIN_SCREEN;

			} else {// 切换到菜单界面

				// scrollTo(-getChildAt(0).getWidth(), 0);

				currentScreen = MENU_SCREEN;

			}
			switchScreen();

			break;

		default:
			break;
		}
*/
		return true;
	}

	/**
	 * 用于拦截事件
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

	/*	switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			mMostRecentX = (int) ev.getX();
			
			break;
		case MotionEvent.ACTION_MOVE:
			
			int moveX = (int) ev.getX();
			
			int diff = moveX - mMostRecentX;
			if(Math.abs(diff) > mTouchSlop){
				return true;  //认为是横向移动的消耗掉此事件
			}

			break;
		case MotionEvent.ACTION_UP:

			break;

		default:
			break;
		}*/

		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public void computeScroll() {
		// 更新当前屏幕的的x轴的偏移量

		if (scroller.computeScrollOffset()) {// 返回TRUE代表正在模拟数据中 FALSE已经停止模拟数据

			// 更新了x轴的偏移量
			scrollTo(scroller.getCurrX(), 0);

			invalidate();

		}

	}

	// @Override
	// protected boolean drawChild(Canvas canvas, View child, long drawingTime)
	// {
	// return super.drawChild(canvas, child, drawingTime);
	// }

	/**
	 * 切换屏幕
	 * 
	 * 根据currentScreen变量
	 * 
	 */
	private void switchScreen() {

		int startX = getScrollX();

		int dx = 0;

		if (currentScreen == MAIN_SCREEN) {

			dx = 0 - startX;

		} else if (currentScreen == MENU_SCREEN) {

			dx = -getChildAt(0).getWidth() - startX;
		}

		// 开始模拟数据
		scroller.startScroll(startX, 0, dx, 0, Math.abs(dx) * 5);

		invalidate(); // invalidate--->drawChild--->child.drawChild--->computeScroll
	}

	/**
	 * 判断是否正在显示菜单
	 * 
	 * @return
	 */

	public boolean isShowMenu() {

		return currentScreen == MENU_SCREEN;
	}

	/**
	 * 隐藏菜单
	 */

	public void hideMenu() {
		currentScreen = MAIN_SCREEN;
		switchScreen();
	}

	/**
	 * 显示菜单
	 */
	public void showMenu() {
		currentScreen = MENU_SCREEN;
		switchScreen();
	}

}
