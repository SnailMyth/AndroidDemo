package com.example.testdemo.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.AbsSpinner;
import android.widget.ImageView;

public class GalleryFlow extends AbsSpinner {

  private Camera mCamera = new Camera();//相机类
  private int mMaxRotationAngle = 80;//最大转动角度
  private int mMaxZoom = -300;//最大缩放值
  private int mCoveflowCenter;//半径值
  public GalleryFlow(Context context) {
    super(context);
    Log.d("tag", "1");
    //支持转换 ,执行getChildStaticTransformation方法
    this.setStaticTransformationsEnabled(true);
  }
  public GalleryFlow(Context context, AttributeSet attrs) {
    super(context, attrs);
    Log.d("tag", "2");
    this.setStaticTransformationsEnabled(true);
  }
  public GalleryFlow(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    Log.d("tag", "3");
    this.setStaticTransformationsEnabled(true);
  }
  public int getMaxRotationAngle() {
    return mMaxRotationAngle;
  }
  public void setMaxRotationAngle(int maxRotationAngle) {
    mMaxRotationAngle = maxRotationAngle;
  }
  public int getMaxZoom() {
    return mMaxZoom;
  }
  public void setMaxZoom(int maxZoom) {
    mMaxZoom = maxZoom;
  }
  private int getCenterOfCoverflow() {
    return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
        + getPaddingLeft();
  }
  private static int getCenterOfView(View view) {
    Log.d("tag", "view left :"+view.getLeft());
    Log.d("tag", "view width :"+view.getWidth());
    return view.getLeft() + view.getWidth() / 2;
  }


  //控制gallery中每个图片的旋转(重写的gallery中方法)
  @Override
  protected boolean getChildStaticTransformation(View child, Transformation t) {
    //取得当前子view的半径值
    final int childCenter = getCenterOfView(child);
    Log.d("tag", "childCenter:"+childCenter);
    final int childWidth = child.getWidth();
    //旋转角度
    int rotationAngle = 0;
    //重置转换状态
    t.clear();
    //设置转换类型
    t.setTransformationType(Transformation.TYPE_MATRIX);
    //如果图片位于中心位置不需要进行旋转
    if (childCenter == mCoveflowCenter) {
      transformImageBitmap((ImageView) child, t, 0);
    } else {
      //根据图片在gallery中的位置来计算图片的旋转角度
      rotationAngle = (int) (((float) (mCoveflowCenter - childCenter) / childWidth) * mMaxRotationAngle);
      Log.d("tag", "rotationAngle:"+rotationAngle);
      //如果旋转角度绝对值大于最大旋转角度返回（-mMaxRotationAngle或mMaxRotationAngle;）
      if (Math.abs(rotationAngle) > mMaxRotationAngle) {
        rotationAngle = (rotationAngle < 0) ? -mMaxRotationAngle : mMaxRotationAngle;
      }
      transformImageBitmap((ImageView) child, t, rotationAngle);
    }
    return true;
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    mCoveflowCenter = getCenterOfCoverflow();
    super.onSizeChanged(w, h, oldw, oldh);
  }

  private void transformImageBitmap(ImageView child, Transformation t,
      int rotationAngle) {
    //对效果进行保存
    mCamera.save();
    final Matrix imageMatrix = t.getMatrix();
    //图片高度
    final int imageHeight = child.getLayoutParams().height;
    //图片宽度
    final int imageWidth = child.getLayoutParams().width;

    //返回旋转角度的绝对值
    final int rotation = Math.abs(rotationAngle);

    // 在Z轴上正向移动camera的视角，实际效果为放大图片。
    // 如果在Y轴上移动，则图片上下移动；X轴上对应图片左右移动。
    mCamera.translate(0.0f, 10.0f, 200.0f);// X值越大图片越靠右Y值越大是图片位置越高Z值越高图片越缩小
    // As the angle of the view gets less, zoom in
    if (rotation < mMaxRotationAngle) {
      float zoomAmount = (float) (mMaxZoom + (rotation * 1.5));
      mCamera.translate(0.0f, 0.0f, zoomAmount);
    }
    // 在Y轴上旋转，对应图片竖向向里翻转。
    // 如果在X轴上旋转，则对应图片横向向里翻转。
    mCamera.rotateY(rotationAngle);
    mCamera.getMatrix(imageMatrix);
    //以图片的中心点为旋转中心,如果不加这两句，就是以（0,0）点为旋转中心
    imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
    imageMatrix.postTranslate((imageWidth / 2), (imageHeight / 2));
    mCamera.restore();//恢复
  }

}