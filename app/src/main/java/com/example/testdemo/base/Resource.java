package com.example.testdemo.base;

import com.google.gson.annotations.SerializedName;

public class Resource<T> {
  @SerializedName("errorCode")
  private int    code;
  @SerializedName("errorMsg")
  private String errorMsg;
  private T      data;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "Resource{" +
        "code=" + code +
        ", errorMsg='" + errorMsg + '\'' +
        ", data=" + data +
        '}';
  }
}
