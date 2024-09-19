//======================================================================//
//																		//
//                 ChatServer.java - (C) 1996 Sam Taylor 				//
//																		//
//  DESCRIPTION															//
//	This file defines the ChatServer and ChatConnection classes			//
//	which form the server end of a Java Chat session.  ChatServer		//
//	is a standalone application which provides a chat service to		//
//	any number of remote clients.  The actual ChatServer object			//
//	simply listens on a well-known server socket for requests for		//
//	connections from remote client.  Each request is handled by a		//
//	separate ChatConnection thread.  These threads in turn use the		//
//	ChatServer object to broadcast to all clients.  The list of			//
//	active client connections is maintained in a hash-table.  For		//
//	each connection, the name of the user is the hash-table key			//
//	to the associated ChatConnection object								//
//  COMMAND LINE OPTIONS												//
//	-log <string> -- The file name to log messages into					//
//	-user -- Switch to have user messages logged						//
//	-system	-- Switch to have system messages logged					//
//	-echo -- Switch to have system messages echoed to all users			//
//	-verbose -- Switch to turn on all logging							//
//	-quiet -- Switch to turn off all logging							//
//	-port <integer> -- Port number to establish server on				//
//  METHODS																//
//  1.	main(String[]) -- processes command line options, creates an	//
//	instance of the class, and then sits in an infinite loop 			//
//	accepting connections from socket, and spawning threads.			//
//  2.	ChatServer(int, DataOutputStream, int) -- allocates resources	//
//	which are centralised into the broadcast object : a server			//
//	socket, a hash-table of clients.  Requires log flags and stream		//
//  3.	broadcast(String, String, boolean) -- broadcasts a message to	//
//	all clients recorded in the hash-table and/or the log,				//
//	depending on logging options and the type of message				//
//  4.	add_client(String, ChatConnection, Image) -- adds a client connection	//
//  5.	list_clients() -- produces a list of clients in hash-table		//
//  6.	remove_client(String) -- removes a client connection			//
//  7.	remove_all_clients() -- removes all client connections			//
//  HISTORY																//
//  15-12-95 (Sam Taylor)												//
//	Initial version completed.  No serious protocol is used to			//
//	communicate between client and server.  Could make logging			//
//	more sophisticated.  Server does not terminate itself ever.			//
//  26-02-96 (Sam Taylor)												//
//	Tidied up the code a bit for the Sun competition					//
//																		//
//======================================================================//



import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.*;
import netscape.application.Image;


public final class ChatServer
{
    public static final int	DEFAULT_PORT	= 8126;

    public static int		LOG_DISABLED	= 0;
    public static int		LOG_USER		= 1;
    public static int		LOG_SYSTEM		= 2;
    public static int		LOG_ECHO		= 4;

    ServerSocket		server;
    Hashtable			clients;
    Hashtable			avatars;
    DataOutputStream	log;
    int					logging;



//-------------------------------- main --------------------------------//

    public static void main(String args[])
    {
    	ChatServer		session;
		ChatConnection	chat_connect;
		Socket			chat_socket;
		int	next 	=	0;
		String			arg;

		int	logging =	LOG_SYSTEM;
		DataOutputStream	log = null;
		int	port 	= 	DEFAULT_PORT;

    	//	Parse the command line options and create a ChatServer which
    	//	is based on those options.

		while (true)
		{
			try
	    	{
	    		arg = args[next++].toLowerCase();
				if (arg.startsWith("-v"))
		    		logging = LOG_USER | LOG_SYSTEM | LOG_ECHO;
				else if (arg.startsWith("-q"))
		    		logging = LOG_DISABLED;
				else if (arg.startsWith("-u"))
		    		logging |= LOG_USER;
				else if (arg.startsWith("-s"))
		    		logging |= LOG_SYSTEM;
				else if (arg.startsWith("-e"))
		    		logging |= LOG_ECHO;
				else if (arg.startsWith("-l"))
		    		log = new DataOutputStream(
					new FileOutputStream(args[next++]));
				else if (arg.startsWith("-p"))
		    		port = Integer.parseInt(args[next++]);
				else
		    		System.err.println("WARNING\tunknown option: "+arg);
	    	}
	    	catch (IOException i)
	    	{
	    		log = null;
				System.err.println("WARNING\tunable to create log file");
	    	}
	    	catch (NumberFormatException n)
	    	{
	    		port = DEFAULT_PORT;
				System.err.println("WARNING\tbad port number, using 8000");
	    	}
	    	catch (ArrayIndexOutOfBoundsException a) break;
		}
		session = new ChatServer(logging, log, port);

    	//	Sit in an infinite loop accepting new connections and forking
    	//	ChatConnection threads to handle each individual connection.

		while (true)
		{
			try
	    	{
	    		chat_socket = session.server.accept();
				chat_connect = new ChatConnection(session, chat_socket);
	    	}
	    	catch (IOException e) { ; }
   		}
   	}



//----------------------------- ChatServer -----------------------------//

    public ChatServer(int lg, DataOutputStream l, int p)
    {
    	try
		{
			clients	= new Hashtable();
	    	clients.clear();
	    	server	= new ServerSocket(p);
	    	logging	= lg;
	    	log		= l == null ? new DataOutputStream(System.out) : l;
		}
		catch (IOException i)
		{
			System.err.println("ERROR:\tunable to establish socket");
    	}
    }



//----------------------------- broadcast ------------------------------//

    synchronized boolean broadcast(String user, String line, int curLocation, int type, boolean user_message)
    {
    	Enumeration	enum = clients.elements();

		if ((user == null) || (line == null)) return false;
		if (user_message ? (logging & LOG_USER) != 0 : (logging & LOG_SYSTEM) != 0)
		{
			try
			{
				log.writeBytes(user + ":\t" + line + "\n");
				log.flush();
	    	}
	    	catch (IOException i);
		}
		if ((!user_message) && ((logging & LOG_ECHO) == 0))
	    	return true;
		
		while (enum.hasMoreElements())
		{
			try ((ChatConnection) enum.nextElement()).send(user, line, curLocation, type);
	    	catch (NoSuchElementException n) return false;
		}
		return true;
    }

//------------------------- another broadcast --------------------------//
	synchronized boolean broadcast(String user, String line, boolean user_message) {
	{
		return(this.broadcast(user, line, 0, 0, user_message));
	}

//----------------------------- add_client -----------------------------//

    synchronized boolean add_client(String user, ChatConnection connect, Image img)
    {
    	if (clients.containsKey(user)) return false;
		//broadcast("SYSTEM", user + " has joined", false);
		
		// Do stuff for adding character to the map
		avatars.put(user, img);
		clients.put(user, connect);
		return true;
    }



//---------------------------- list_clients ----------------------------//

    synchronized String list_clients()
    {
    	StringBuffer buffer = new StringBuffer();
		Enumeration enum = clients.keys();
		try 
		{
			while (enum.hasMoreElements())
	    	{
	    		if (buffer.length() > 0) buffer.append(", ");
				buffer.append((String) enum.nextElement());
			}
		}
		catch (NoSuchElementException n) return null;
		return buffer.length() == 0 ? null : buffer.toString();
    }



//--------------------------- remove_client ----------------------------//

    synchronized void remove_client(String user)
    {
    	if (user != null) clients.remove(user);
		//broadcast("SYSTEM", user + " has disconnected", false);
		
		// Remove character from map
    }



//------------------------- remove_all_clients -------------------------//

    synchronized void remove_all_clients()
    {
    	// Do something with this...
    	broadcast("SYSTEM", "shutting down...", false);
    	
    	// Remove all characters from map
		
		Enumeration enum = clients.elements();
		while (enum.hasMoreElements())
		{
			try ((ChatConnection) enum.nextElement()).close();
	    	catch (NoSuchElementException n) break;
    	}
    }

}   // class ChatServer



//======================================================================//
//																		//
//                            ChatConnection							//
//																		//
//  DESCRIPTION															//
//	This class maintains the details of a single connection.  It		//
//	has a background thread process sits and listens for messages		//
//	from the client application.  It is also used by the server			//
//	to send to the client as part of the broadcast operation.			//
//  METHODS																//
//  1.	ChatConnection(ChatServer, Socket) -- creates a connection over	//
//	the socket, receives user name, and optionally broadcasts the		//
//	details of the other users currently connected						//
//  2.	run() -- background thread which listens for messages from the	//
//	client and then uses the parent ChatServer to broadcast them		//
//  3.	send(String, String) -- send a message to the client			//
//  4.	close() -- terminate connection to the client machine			//
//																		//
//======================================================================//

final class ChatConnection extends Thread
{
    ChatServer			session;
    String				user;
    volatile boolean	connected;
    Socket				socket;
    volatile DataInputStream	in_stream;
    volatile DataOutputStream	out_stream;



//--------------------------- ChatConnection ---------------------------//

    ChatConnection(ChatServer c, Socket s, Image img)
    {
    	super();
		session = c;
		socket = s;
		String others;
		try
		{
			in_stream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	    	out_stream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	    	user = in_stream.readLine();
	    	others = session.list_clients();
	    	if (session.add_client(user, this, session.avatars))
	    	{
	    		connected = true;
				start();
				if ((session.logging & ChatServer.LOG_ECHO) != 0) 
				{
					out_stream.writeBytes("SYSTEM\n");
		    		if (others != null)
						out_stream.writeBytes("other users are "+others+'\n');
		    		else
						out_stream.writeBytes("no other users connected\n");
		    		out_stream.flush();
	    		}
	    	}
	    	else socket.close();
		}
		catch (IOException i)
		{
			System.err.println("ChatConnection() - error opening client");
    	}
    }



//-------------------------------- run ---------------------------------//

    public void run()
    {
    	String line;
		try
		{
			while(connected) 
	    	{
	    		line = in_stream.readLine();
				if (line == null) break;
				session.broadcast(user, line, true);
			}
		}
		catch (Exception e) ;
		close();
    }



//-------------------------------- send --------------------------------//

    synchronized void send(String u, String t)
    {
    	try
		{
			out_stream.writeBytes(u);
	    	out_stream.writeByte('\n');
	    	out_stream.writeBytes(t);
	    	out_stream.writeByte('\n');
	    	out_stream.flush();
		}
		catch (IOException i)
		{
			close();
    	}
    }



//------------------------------- close --------------------------------//

    synchronized void close()
    {
    	connected = false;
		try
		{
			session.remove_client(user);
	    	stop();
	    	in_stream.close();		in_stream = null;
	    	out_stream.close();		out_stream = null;
	    	socket.close();
		}
		catch (IOException i);
    }

}   // class ChatConnection



//======================================================================//






