package com.example.testdemo.base;

import com.example.testdemo.data.ArticleBean;

/**
 * @author : myth_hai
 * @date : 2021/3/24 18:27
 * @description : CommonNetWork
 */
public class CommonNetWork {

  private static CommonNetWork mInstance;
  private final  INetApi       mNetApi;

  @SuppressWarnings("unchecked")
  private CommonNetWork() {
    mNetApi = (INetApi) ServiceCreator.getInstance().create(INetApi.class);
  }

  public static CommonNetWork getInstance() {
    if (mInstance == null) {
      synchronized (CommonNetWork.class) {
        if (mInstance == null) {
          mInstance = new CommonNetWork();
        }
      }
    }
    return mInstance;
  }

  public Resource<ArticleBean> get(int page) {
    return null;
  }
}
