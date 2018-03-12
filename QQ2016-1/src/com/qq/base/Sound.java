package com.qq.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

public class Sound {
	@SuppressWarnings("resource")
	public Sound(int cMd){
		String filename = "sound/Audio.wav";
		try{
		switch(cMd){
		   case cmd.CMD_ONLINE :
			   filename = "sound/system.wav";
			   break;
		   case cmd.CMD_OFFLINE :
			   filename = "sound/Global.wav";
			   break;
		   case cmd.CMD_SEND   :
			   filename = "sound/msg.wav";
			   break;
		   case cmd.CMD_SHAKE :
			   filename = "sound/shake.wav";
			   break;
			   }
		File file = new File(filename);
		InputStream is = new FileInputStream(file);
		int size = is.available();
		byte[] b = new byte[size];
		is.read(b,0,size);
		
		//准备播放的资源
		AudioData ad = new AudioData(b);
		
		//准备播放的设备
		AudioDataStream ads = new AudioDataStream(ad);
		
		//用设备播放准备好的播放资源
		AudioPlayer.player.start(ads);
		
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Sound(cmd.CMD_ARGEE);
	}
}
