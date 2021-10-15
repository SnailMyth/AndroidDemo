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
      @Override
      public boolean tryCaptureView(@NonNull View child, int pointerId) {
        if (mDragView != null) {
          return mDragView == child;
        } else {
          mDragView = child;
        }
        return true;
      }

      @Override
      public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
        //控制左边的拖曳距离，不能越界。
        //当拖曳的距离超过左边的padding值，也意味着child view越界，复位
        //默认的padding值=0
        int paddingLeft = getPaddingLeft();
        if (left < paddingLeft) {
          return paddingLeft;
        }

        //这里是控制右边的拖曳边缘极限位置。
        //假设pos的值刚好是子view child右边边缘与父view的右边重合的情况
        //pos值即为一个极限的最右边位置，超过也即意味着拖曳越界：越出右边的界限，复位。
        //可以再加一个paddingRight值，缺省的paddingRight=0，所以即便不加也在多数情况正常可以工作
        int pos = getWidth() - child.getWidth() - getPaddingRight();
        return Math.min(left, pos);
      }

      @Override
      public int clampViewPositionVertical(@NonNull View child, int top, int dy) {

        int paddingTop = getPaddingTop();
        if (top < paddingTop) {
          return paddingTop;
        }

        int bottom = getHeight() - child.getHeight() - getPaddingBottom();

        return Math.min(top, bottom);
      }

      @Override
      public boolean onEdgeLock(int edgeFlags) {
        return super.onEdgeLock(edgeFlags);
      }

      @Override
      public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx,
          int dy) {
        super.onViewPositionChanged(changedView, left, top, dx, dy);
      }

      @Override
      public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
        super.onViewReleased(releasedChild, xvel, yvel);
        //松手返回原位置
        mDragView = null;
        //if (releasedChild == mDragView) {
        //  mDragHelper.settleCapturedViewAt(mLeft, mTop);
        //  invalidate();
        //}
      }

      //解决点击事件冲突，返回不能为0
      @Override
      public int getViewHorizontalDragRange(@NonNull View child) {
        return mDragHelper.getTouchSlop();
      }

      @Override
      public int getViewVerticalDragRange(@NonNull View child) {
        return mDragHelper.getTouchSlop();
      }
    });
  }

  public void setDragView(@NonNull View dragView) {
    mDragView = dragView;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return mDragHelper.shouldInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    mDragHelper.processTouchEvent(event);
    return true;
  }

  @Override
  public void computeScroll() {
    super.computeScroll();
    //使用continueSettling(true)判断拖拽是否完成
    if (mDragHelper.continueSettling(true)) {
      invalidate();
    }
  }

  //布局资源加载完毕
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
  }

}
