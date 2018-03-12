package com.qq.base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;

import com.qq.beens.Account;

public class SendCmd {
	public static void send(SendMsg msg){
		try {
			DatagramSocket scoket = new DatagramSocket();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(msg);
			oos.flush();
			byte[] b = bos.toByteArray();
			InetAddress idr = InetAddress.getByName(msg.frendIfo.getIpadress());
			int port = msg.frendIfo.getPort();
			DatagramPacket packet = new DatagramPacket(b,0, b.length,idr,port);
			scoket.send(packet);
			scoket.close();
			oos.close();
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static void sendAll(Vector<Account> allifo,Account myifo,int cMd){
		for(Account a : allifo){
			if(!a.getStement().equals(cmd.STATUS[1]) && a.getQQid()!=myifo.getQQid()){
				SendMsg msg = new SendMsg();
				msg.Cmd=cMd;
				msg.myIfo=myifo;
				msg.frendIfo=a;
				send(msg);
				
			}
		}
	}
}
