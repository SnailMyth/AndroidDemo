package com.example.testdemo.utils

import android.content.Context
import android.text.TextUtils
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine

class VoiceChatManager {

  private var mIsSpeaker //是否使用扬声器播放
      = false
  private val mIsSilence //是否静音
      = false
  private val mIsJoinChannel //是否已经加入了频道
      = false
  private var mRtcEngine: RtcEngine? = null
  private val mOnVoiceChatEvenListener: OnVoiceChatEvenListener? = null
  private val mRtcEventHandler = object : IRtcEngineEventHandler() {

    // 注册 onUserOffline 回调。远端用户离开频道后，会触发该回调。
    override fun onUserOffline(
      uid: Int,
      reason: Int
    ) {

    }

    // 注册 onUserMuteAudio 回调。远端用户静音后，会触发该回调。
    override fun onUserMuteAudio(
      uid: Int,
      muted: Boolean
    ) {

    }
  }

  private fun initializeAgoraEngine(context:Context) {
    try {
      log("init")
      mRtcEngine = RtcEngine.create(context, "bcd6dc9f9552413babd94a96d2c625dc", mRtcEventHandler)
      mRtcEngine?.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION)
      mRtcEngine?.setDefaultAudioRoutetoSpeakerphone(mIsSpeaker) //默认从听筒播放
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  //设置是否扬声器播放
  fun setSpeaker(speaker: Boolean) {
    mIsSpeaker = speaker

      if (mRtcEngine != null) {
        val code = mRtcEngine!!.setEnableSpeakerphone(mIsSpeaker)
        log("setSpeaker $mIsSpeaker code : $code")
      }

  }


  private fun log(msg: String) {
    if (TextUtils.isEmpty(msg)) return
    mOnVoiceChatEvenListener?.logMsg(msg)
  }

  interface OnVoiceChatEvenListener {
    fun logMsg(msg: String?)
    fun selfJoinRoomStatus(isSuccess: Boolean)
    fun oppositeJoinRoomStatus(isSuccess: Boolean)
  }
}