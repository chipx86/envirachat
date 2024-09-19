package chcs.common.net;

import java.lang.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Server extends Object {
	public static final int	DEFAULT_PORT	= 8126;
	
	public static int		LOG_DISABLED	= 0;
	public static int		LOG_USER		= 1;
	public static int		LOG_SYSTEM		= 2;
	public static int		LOG_ECHO		= 4;
	
	public static int		CHAT_TALK		= 0;
	public static int		CHAT_WHISPER	= 1;
	public static int		CHAT_SHOUT		= 2;
	public static int		CHAT_TELEPATHY	= 3;
	
	public ServerSocket		server;
	Hashtable				clients;
	DataOutputStream		log;
	public static int		logging;
	Client					systemClient = new Client("SYSTEM");
	
	public Server(int lg, DataOutputStream l, int p) {
		try {
			clients	= new Hashtable();
			clients.clear();
			server	= new ServerSocket(p);
			logging	= lg;
			log		= l == null ? new DataOutputStream(System.out) : l;
		}
		catch (IOException i) {
			System.err.println("ERROR:\tunable to establish socket.");
		}
	}
	
	synchronized boolean broadcast(Client user, String line, int type, boolean user_message) {
		Enumeration enum = clients.elements();
		
		if ((user.getUser() == null) || (line == null))
			return false;
		if (user_message ? (logging & LOG_USER) != 0 : (logging & LOG_SYSTEM) != 0) {
			try {
				log.writeBytes(user.getUser() + ":\t" + line + "\n");
				log.flush();
			}
			catch (IOException i) {}
		}
		if ((!user_message) && ((logging & LOG_ECHO) == 0))
			return true;
		
		while (enum.hasMoreElements()) {
			try {
				((ChatConnection)enum.nextElement()).send(user.getUser(), line);
			}
			catch (NoSuchElementException n) {
				return false;
			}
		}
		return true;
	}
	
	synchronized boolean broadcast(Client user, String line, boolean user_message) {
		return(broadcast(user, line, CHAT_TALK, user_message));
	}
	
	synchronized boolean addClient(Client user, ChatConnection connect) {
		if (clients.containsKey(user))
			return false;
		//Calendar cal = new Calendar();//new TimeZone().getTimeZone("PST"), new Local(Local.ENGLISH, Local.US))
		broadcast(systemClient, user.getUser() + " has joined.", false); // at "+(String)date.getTime()+" on "+(String)date.getDate()+'.', false)
		
		clients.put(user, connect);
		return(true);
	}
	
	synchronized String listClients() {
		StringBuffer buffer = new StringBuffer();
		Enumeration enum = clients.keys();
		try {
			while (enum.hasMoreElements()) {
				if (buffer.length() > 0)
					buffer.append(", ");
				buffer.append((String)enum.nextElement());
			}
		}
		catch (NoSuchElementException n) {
			return null;
		}
		return(buffer.length() == 0 ? null : buffer.toString());
	}
	
	synchronized void removeClient(Client user) {
		if (user != null)
			clients.remove(user);
		
		//Calendar cal = new Calendar();//new TimeZone().getTimeZone("PST"), new Local(Local.ENGLISH, Local.US))
		broadcast(systemClient, user.getUser() + " has disconnected.", false);// at "+(String)date.getTime()+" on "+(String)date.getDate()+'.', false);
	}
	
	
	synchronized void removeAllClients() {
		//Calendar cal = new Calendar();//new TimeZone().getTimeZone("PST"), new Local(Local.ENGLISH, Local.US))
		broadcast(systemClient, "shutting down...",false); // at "+cal.get(Calendar.DATE   (String)date.getTime()+" on "+(String)date.getDate()+'.', false);
		
		Enumeration enum = clients.elements();
		while (enum.hasMoreElements()) {
			try {
				((ChatConnection)enum.nextElement()).close();
			}
			catch(NoSuchElementException n) {
				break;
			}
		}
	}
}