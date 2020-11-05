package com.example.testdemo.anim;

import android.view.View;

public class ScaleAnimator extends BaseAnimator {

  private float start;
  private float end;
  private long  duration = 1000;

  public ScaleAnimator(float end, View... view) {
    super(view);
    this.start = 1;
    this.end = end;
  }

  public ScaleAnimator(float start, float end, View... view) {
    super(view);
    this.start = start;
    this.end = end;
  }

  public ScaleAnimator(float start, float end, long duration, View... view) {
    super(view);
    this.start = start;
    this.end = end;
    this.duration = duration;
  }

  @Override public AnimatorBuilder prepare(View... targets) {
    //AnimatorBuilder 提供了很多实用的方法
    //    .animate(target[0]) //动画1
    //    .translationX（100) //单位 dp
    //    .parentTop(10) //距离顶部10dp
    //    .duration(1000)
    //    .with(target[1]) //动画2，动画2与动画1同步执行
    //    .leftof(target[3],10); //target[1]移动到 target[3] 的右边,距离为 10dp,仅支持对静止的 view 设置相对位置
    //    .alpha(0,1)
    //    .after(target[2]) //动画3，动画1，2执行完了，再执行动画3
    //    .translationX（100);
    return AnimatorBuilder
        .animate(targets[0])
        .scaleX(start, end)
        .scaleY(start, end)
        .duration(duration);
  }
}
