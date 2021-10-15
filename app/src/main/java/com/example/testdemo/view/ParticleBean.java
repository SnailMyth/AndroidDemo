package com.example.testdemo.view;

/**
 * @author : myth_hai
 * @date : 2021/4/26 15:38
 * @description : ParticleBean
 */
public class ParticleBean {
  private int   x;
  private int   y;
  private int   nextX;
  private int   nextY;
  private int   color;
  private int   xSpeed;
  private int   ySpeed;
  private int   shape     = 1;//1矩形,2原型
  private int   width;
  private int   height;
  private float radius;
  private float angle     = 0;
  private int   showDelay = 0;
  private int   yOffSet;

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getShape() {
    return shape;
  }

  public void setShape(int shape) {
    this.shape = shape;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public float getRadius() {
    return radius;
  }

  public int getyOffSet() {
    return yOffSet;
  }

  public void setyOffSet(int yOffSet) {
    this.yOffSet = yOffSet;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public int getNextX() {
    return nextX;
  }

  public void setNextX(int nextX) {
    this.nextX = nextX;
  }

  public int getShowDelay() {
    return showDelay;
  }

  public void setShowDelay(int showDelay) {
    this.showDelay = showDelay;
  }

  public int getNextY() {
    return nextY;
  }

  public void setNextY(int nextY) {
    this.nextY = nextY;
  }

  public int getXSpeed() {
    return xSpeed;
  }

  public int getYSpeed() {
    return ySpeed;
  }

  public void setAngle(float angle) {
    this.angle = angle;
  }

  public float getAngle() {
    return angle;
  }

  public void setYSpeed(int ySpeed) {
    this.ySpeed = ySpeed;
  }

  public void setXSpeed(int xSpeed) {
    this.xSpeed = xSpeed;
  }

  public void setDelayValue(int maxNum, int index, int delayFactor) {
    showDelay = delayFactor * index / maxNum;
  }

  public int getColor() {
    return color;
  }

  public ParticleBean(int x, int y, int color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }

  public void setRec(int w, int h) {
    this.shape = 1;
    width = w;
    height = h;
  }

  public void setOval(int radius) {
    this.shape = 2;
    this.radius = radius;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public ParticleBean(int x, int y) {
    this.x = x;
    this.y = y;
    this.nextX = x;
    this.nextY = y;
  }

  public ParticleBean(int x, int y, int nextX, int nextY) {
    this.x = x;
    this.y = y;
    this.nextX = nextX;
    this.nextY = nextY;
  }

  @Override
  public String toString() {
    return "ParticleBean{" +
        "x=" + x +
        ", y=" + y +
        ", nextX=" + nextX +
        ", nextY=" + nextY +
        ", color=" + color +
        ", xSpeed=" + xSpeed +
        ", ySpeed=" + ySpeed +
        ", shape=" + shape +
        ", width=" + width +
        ", height=" + height +
        ", radius=" + radius +
        ", angle=" + angle +
        ", showDelay=" + showDelay +
        '}';
  }

  public ParticleBean() {
  }
}
