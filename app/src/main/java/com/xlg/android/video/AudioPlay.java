package com.xlg.android.video;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import java.util.concurrent.ConcurrentLinkedQueue;

public class AudioPlay {
	private boolean isPlaying = false;

	private int audioTime = -1;// 时间戳
	private int audioType = -1; // 语音类型， 0表示adpcm
	private int audioCycle = -1; // 赫兹
	private int audioBit = -1; // 采样位数
	private int audioChannel = -1; // 声道
	private int audioBlock = -1; // 原始块大小
	private ConcurrentLinkedQueue<byte[]> audioData = new ConcurrentLinkedQueue<byte[]>();
	private AudioTrack audioTrack; // 音频播放类
	private byte blockBuff[];
	private byte adpcmBuff[];

	public AudioPlay() {

	}

	/**
	 * 开始播放
	 */
	public void play(byte[] head) {
		audioTrack.write(head, 0, head.length);
		audioTrack.flush();
	}



	/**
	 * 停止播放
	 */
	public void stop() {
		if (null != audioTrack) {
			audioTrack.stop();
			audioTrack.release();
			audioTrack = null;
		}
	}
	
	/**
	 * 设置采样率
	 */
	public void setConfig(int sample, int channel) {
		if(audioCycle == sample && audioChannel == channel) {
			return;
		}
		
		audioCycle = sample;
		audioChannel = channel;
		
		if (null != audioTrack) {
			audioTrack.stop();
			audioTrack.release();
			audioTrack = null;
		}
		
		int bufsize = audioCycle * 20;
		audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
				audioCycle,
				(2==audioChannel)?AudioFormat.CHANNEL_CONFIGURATION_STEREO:AudioFormat.CHANNEL_CONFIGURATION_DEFAULT,
				AudioFormat.ENCODING_PCM_16BIT, bufsize,
				AudioTrack.MODE_STREAM);
		audioTrack.play();
	}
}
