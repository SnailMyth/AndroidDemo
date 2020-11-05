package com.example.testdemo.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.customview.widget.ViewDragHelper;

public class DragConstraintLayout extends ConstraintLayout {

  private ViewDragHelper mDragHelper;

  private View mDragView;
  private int  mLeft;
  private int  mTop;

  public DragConstraintLayout(@NonNull Context context) {
    super(context);
    init(context, null, 0, 0);
  }

  public DragConstraintLayout(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs, 0, 0);
  }

  public DragConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr, 0);
  }

  public DragConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs, defStyleAttr, defStyleRes);
  }

  private void init(@NonNull Context context, @Nullable AttributeSet attrs,
      int defStyleAttr,
      int defStyleRes) {
    mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
      @Override public boolean tryCaptureView(@NonNull View child, int pointerId) {
        return mDragView != null && mDragView == child;
      }

      @Override public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
        return left;
      }

      @Override public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
        return top;
      }

      @Override
      public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx,
          int dy) {
        super.onViewPositionChanged(changedView, left, top, dx, dy);
      }

      @Override public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
        super.onViewReleased(releasedChild, xvel, yvel);
        //松手返回原位置
        if (releasedChild == mDragView) {
          mDragHelper.settleCapturedViewAt(mLeft, mTop);
          invalidate();
        }
      }

      //解决点击事件冲突，返回不能为0
      @Override public int getViewHorizontalDragRange(@NonNull View child) {
        return mDragHelper.getTouchSlop();
      }

      @Override public int getViewVerticalDragRange(@NonNull View child) {
        return mDragHelper.getTouchSlop();
      }
    });
  }

  public void setDragView(@NonNull View dragView) {
    mDragView = dragView;
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    return mDragHelper.shouldInterceptTouchEvent(ev);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    mDragHelper.processTouchEvent(event);
    return true;
  }

  @Override public void computeScroll() {
    super.computeScroll();
    //使用continueSettling(true)判断拖拽是否完成
    if (mDragHelper.continueSettling(true)) {
      invalidate();
    }
  }

  //布局资源加载完毕
  @Override protected void onFinishInflate() {
    super.onFinishInflate();
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    if (mDragView != null) {
      mLeft = mDragView.getLeft();
      mTop = mDragView.getTop();
    }
  }
}
