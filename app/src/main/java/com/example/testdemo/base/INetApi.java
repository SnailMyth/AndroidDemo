package com.example.testdemo.base;

import com.example.testdemo.data.ArticleBean;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INetApi {
  @FormUrlEncoded
  @POST("article/listproject/{page}/json")
  Call<Resource<ArticleBean>> getData(@Field("page") int page);
}
