package com.example.testdemo.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.blankj.utilcode.util.LogUtils;

/**
 * Created by chengjuechao on 2017/5/17.
 * 为了适配8.0  所有权限使用权限组的方式申请
 * 8.0定位需要检测是否开启了定位服务
 */

public class PermissionManger {
  /**
   * 相机
   * Manifest.permission.CAMERA
   */
  public static final int CAMERA_CODE = 1;

  /**
   * 访问您设备上的照片、媒体内容和文件
   * Manifest.permission.READ_EXTERNAL_STORAGE
   */
  public static final int STORAGE_CODE = 2;

  /**
   * 录音  Manifest.permission.RECORD_AUDIO
   */
  public static final int AUDIO_CODE = 3;

  /**
   * 定位  Manifest.permission.ACCESS_FINE_LOCATION
   */
  public static final int LOCATION_CODE = 4;

  /**
   * 粗略定位，不用GPS  Manifest.permission.ACCESS_COARSE_LOCATION
   */
  public static final int ACCESS_COARSE_LOCATION = 7;

  /**
   * 获取手机状态  Manifest.permission.READ_PHONE_STATE
   */
  public static final int READ_PHONE_STATE_CODE = 5;

  /**
   * 相机和麦克风Manifest.permission.READ_PHONE_STATE
   */
  public static final int CAMERA_AND_AUDIE_CODE = 6;

  /**
   * 推送通知
   */
  public static final int PUSH_NOTIFICATION = 8;


  public static boolean hasPermission(Context context, int which) {
    if (which == PUSH_NOTIFICATION) {
      NotificationManagerCompat manager = NotificationManagerCompat.from(context);
      // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
      return manager.areNotificationsEnabled();
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      String[] permissions = getPermissionString(context, which);
      if (permissions != null && permissions.length > 0) {
        for (String permission : permissions) {
          if (ContextCompat.checkSelfPermission(context, permission)
              != PackageManager.PERMISSION_GRANTED) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static void getPermission(Fragment fragment, int which) {
    if (which == PUSH_NOTIFICATION) {
      Intent intent = new Intent();
      try {
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", fragment.getActivity().getPackageName(), null);
        intent.setData(uri);
        fragment.startActivityForResult(intent, which);
      } catch (Exception e) {
        LogUtils.d("myth", "设置失败，请在手机设置中设置");
        e.printStackTrace();
      }
      return;
    }
    fragment.requestPermissions(getPermissionString(fragment, which), which);
  }

  @TargetApi(Build.VERSION_CODES.M)
  public static void getPermission(Activity activity, int which) {
    if (which == PUSH_NOTIFICATION) {
      try {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, which);
      } catch (Exception e) {
        LogUtils.d("myth", "设置失败，请在手机设置中设置");
        e.printStackTrace();
      }
      return;
    }
    activity.requestPermissions(getPermissionString(activity, which), which);
  }

  @TargetApi(Build.VERSION_CODES.M)
  private static String[] getPermissionString(Object o, int which) {
    String[] permissions = null;
    switch (which) {
      case CAMERA_CODE:
        permissions = new String[] { Manifest.permission.CAMERA };
        break;
      case STORAGE_CODE:
        permissions = new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        break;
      case AUDIO_CODE:
        permissions = new String[] { Manifest.permission.RECORD_AUDIO };
        break;
      case LOCATION_CODE:
        permissions = new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        };
        break;
      case ACCESS_COARSE_LOCATION:
        permissions = new String[] { Manifest.permission.ACCESS_COARSE_LOCATION };
        break;
      case READ_PHONE_STATE_CODE:
        permissions = new String[] {
            Manifest.permission.READ_PHONE_STATE
        };
        //Manifest.permission.CALL_PHONE,
        //Manifest.permission.WRITE_CALL_LOG,
        //Manifest.permission.ADD_VOICEMAIL,
        //Manifest.permission.USE_SIP,
        //Manifest.permission.PROCESS_OUTGOING_CALLS};
        break;
      case CAMERA_AND_AUDIE_CODE:
        permissions = new String[] { Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO };
        break;
      default:
        break;
    }
    return permissions;
  }
}
