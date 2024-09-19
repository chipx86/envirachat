package chcs.common.net;

import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public final class ChatConnection extends Thread {
	Server				session;
	Client				user = new Client();
	volatile boolean			connected;
	Socket				socket;
	volatile BufferedReader		in_stream;
	volatile BufferedWriter		out_stream;
	
	public ChatConnection(Server c, Socket s) {
		super();
		session = c;
		socket = s;
		String others;
		try {
			in_stream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out_stream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			user.setUser(in_stream.readLine());
			others = session.listClients();
			if (session.addClient(user, this)) {
				connected = true;
				start();
				if ((session.logging & Server.LOG_ECHO) != 0) {
					out_stream.write("SYSTEM\n",0,"SYSTEM\n".length());
					if (others != null) {
						String curmessage = "other connected users are: "+others+".\n";
						out_stream.write(curmessage,0,curmessage.length());
					}
					else {
						String curmessage = "no other users are currently connected.\n";
						out_stream.write(curmessage,0,curmessage.length());
					}
					out_stream.flush();
				}
			}
			else
				socket.close();
		}
		catch (IOException i) {
			System.out.println("ChatConnection() - error opening connection to client");
		}
	}
	
	public void run() {
		String line;
		try {
			while(connected) {
				line = in_stream.readLine();
				if (line == null)
					break;
				session.broadcast(user, line, true);
			}
		}
		catch (IOException e) {}
		close();
	}
	
	synchronized void send(String u, String t) {
		try {
			out_stream.write(u,0,t.length());
			out_stream.write('\n');//,0,1);
			out_stream.write(t,0,t.length());
			out_stream.write('\n');//,0,1);
			out_stream.flush();
		}
		catch (IOException i) {
			close();
		}
	}
	
	synchronized void close() {
		connected = false;
		try {
			session.removeClient(user);
			stop();
			in_stream.close();
			in_stream = null;
			out_stream.close();
			out_stream = null;
			socket.close();
		}
		catch (IOException i) {}
	}
}