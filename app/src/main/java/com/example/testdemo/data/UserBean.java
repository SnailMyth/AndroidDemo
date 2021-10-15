package com.example.testdemo.data;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {
  private String name;
  private String userId;
  private String sex;
  private String age;

  protected UserBean(Parcel in) {
    name = in.readString();
    userId = in.readString();
    sex = in.readString();
    age = in.readString();
  }

  public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
    @Override
    public UserBean createFromParcel(Parcel in) {
      return new UserBean(in);
    }

    @Override
    public UserBean[] newArray(int size) {
      return new UserBean[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(userId);
    dest.writeString(sex);
    dest.writeString(age);
  }
}
