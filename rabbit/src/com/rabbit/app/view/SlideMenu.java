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
	private int mMostRecentX; // x�����һ�ε�ƫ����

	private final int MENU_SCREEN = 1;// �˵�����

	private final int MAIN_SCREEN = 2;// ������

	private int currentScreen = MAIN_SCREEN; // ��ǰ����Ļ����ʾ���� Ĭ��Ϊ������
	private Scroller scroller;// ����ģ������
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

		// ��ʼ���˵���������Ŀ�͸�
		initView(widthMeasureSpec, heightMeasureSpec);

	}

	/**
	 * ��ʼ���˵���������Ŀ�͸�
	 * 
	 * @param widthMeasureSpec
	 *            slidemenu�ؼ��Ŀ�Ȳ�����׼
	 * @param heightMeasureSpec
	 *            slidemenu�ؼ��ĸ߶Ȳ�����׼
	 */

	private void initView(int widthMeasureSpec, int heightMeasureSpec) {
		// �˵�����
		View menuView = this.getChildAt(0);

		menuView.measure(menuView.getLayoutParams().width, heightMeasureSpec);

		// ���������
		View mainView = this.getChildAt(1);
		mainView.measure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		// �˵�����
		View menuView = this.getChildAt(0);

		menuView.layout(-menuView.getMeasuredWidth(), 0, 0, b);

		// ���������
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

			// ��ȡ���µ�x��ƫ����
			int currentX = (int) event.getX();
			// 1.��������ֵ ����ֵ = x�����һ��ƫ���� - ��ǰ���µ�x��ƫ������
			int delta = mMostRecentX - currentX;
			// 2.��������ֵ ������Ļ��ʾ��λ��

			// �ж��ƶ����Ƿ񳬹��������߽�

			int scrollX = getScrollX() + delta;

			if (scrollX < -getChildAt(0).getWidth()) {// ��ǰ��������߽�
				scrollTo(-getChildAt(0).getWidth(), 0);
			} else if (scrollX > 0) {// �������ұ߽�
				scrollTo(0, 0);
			} else {
				// �����ƶ�
				scrollBy(delta, 0);
			}

			Log.i(TAG, "delta" + delta);

			// 3.x������һ��ƫ����=��ǰ���µ�x��ƫ����
			mMostRecentX = currentX;

			break;

		case MotionEvent.ACTION_UP:

			// �˵������ĵ�

			int menuCenter = -getChildAt(0).getWidth() / 2;
			// ��õ�ǰ��ĻX���ƫ����
			int _x = getScrollX();

			if (_x > menuCenter) {// �л���������
				// scrollTo(0, 0);

				currentScreen = MAIN_SCREEN;

			} else {// �л����˵�����

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
	 * ���������¼�
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
				return true;  //��Ϊ�Ǻ����ƶ������ĵ����¼�
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
		// ���µ�ǰ��Ļ�ĵ�x���ƫ����

		if (scroller.computeScrollOffset()) {// ����TRUE��������ģ�������� FALSE�Ѿ�ֹͣģ������

			// ������x���ƫ����
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
	 * �л���Ļ
	 * 
	 * ����currentScreen����
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

		// ��ʼģ������
		scroller.startScroll(startX, 0, dx, 0, Math.abs(dx) * 5);

		invalidate(); // invalidate--->drawChild--->child.drawChild--->computeScroll
	}

	/**
	 * �ж��Ƿ�������ʾ�˵�
	 * 
	 * @return
	 */

	public boolean isShowMenu() {

		return currentScreen == MENU_SCREEN;
	}

	/**
	 * ���ز˵�
	 */

	public void hideMenu() {
		currentScreen = MAIN_SCREEN;
		switchScreen();
	}

	/**
	 * ��ʾ�˵�
	 */
	public void showMenu() {
		currentScreen = MENU_SCREEN;
		switchScreen();
	}

}
