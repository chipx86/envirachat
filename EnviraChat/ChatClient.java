//======================================================================//
//									//
//                 ChatClient.java - (C) 1996 Sam Taylor		//
//									//
//  DESCRIPTION								//
//	This file implements the client application for my Java chat	//
//	system.  ChatClient is a standalone client application for	//
//	my ChatServer system.  This client implements a neat GUI and	//
//	produces its own log of the chat session.  Messages are sent	//
//	from the client to the server over a socket connection, where	//
//	it is broadcast to all connected clients (including this one).	//
//	Messages are received from the server by a thread which reads	//
//	from the socket, and adds the contents to the log.  This	//
//	client may also be run as an applet with ChatClientApplet	//
//  COMMAND LINE OPTIONS						//
//	-name | -user <string> -- the name the user will chat under	//
//	{-server} <string> -- the name of the chat server machine	//
//	-port <integer> -- the port on the server to connect to		//
//  METHODS								//
//  1.	main(String []) -- parses the command line arguments, creates	//
//	a ChatClient object and then invokes connect()			//
//  2.	ChatClient(String, String, int) -- Creates the GUI and records	//
//	the default user name, host name, and port number		//
//  3.	handleEvent(Event) -- reacts to events created by the user	//
//  4.	run() -- to implement the runnable interface.  This procedure 	//
//	is run by the thisThread thread.  It sits on the socket waiting	//
//	for broadcasts from the server, and the calling log_line()	//
//  5.	connect(boolean) -- initiates a new connection.  If the prompt	//
//	flag is set, the connection is delayed and a ChatConnectDialog	//
//	is created to query details of the connection.			//
//  6.	disconnect() -- break the connection to server and end thread	//
//  7.	quit() -- disconnect the connection, and destroy the window	//
//  8.	send_line(String) -- sends a line to the server over the socket	//
//  9.	log_line(String, String) -- adds a line to the log from a	//
//	specific user.  The line is formatted before being added to the	//
//	log, with blank lines and a label added when a new user speaks	//
//  10.	log_save() -- This procedure creates a FileDialog and saves the	//
//	current contents of the log into the specified file.		//
//  HISTORY								//
//  15-12-95 (Sam Taylor)						//
//	Initial version implemented for the Java beta1 release.  There	//
//	are problems with stopping the thread when it blocks on a read,	//
//	and with displaying the dialog					//
//  20-12-95 (Sam Taylor)						//
//	Tweaked code for the beta2 release.  Thread problem solved,	//
//	but the dialogs still look messy when being created		//
//  28-02-96 (Sam Taylor)						//
//	Modified to be run as an applet by ChatClientApplet for entry 	//
//	in the Sun Java programming contest				//
//									//
//======================================================================//



import java.awt.*;
import java.io.*;
import java.lang.*;
import java.net.*;



public class ChatClient extends Frame implements Runnable
{
	Thread			thisThread;		// To implement run	//

    Font			text_font;		// GUI components set	//
    Font			button_font;	//   in the constructor //
    TextField		line;			//   and managed by 	//
    Button			conn;			//   handleEvent()	//
    Button			disc;
    Button			save;
    Button			quit;
    TextArea		log;

    String			log_user;		// Connection details	//
    String			user;
    String			host;
    int				port;
    volatile boolean		connected;
    boolean			applet;

    Socket			socket;				// Connected to the	//
    DataInputStream		in_stream;		//   server by streams	//
    DataOutputStream		out_stream;	//   over sockets	//



//-------------------------------- main --------------------------------//

    public static void main(String args[])
    {
    	String 	user = null;
		String	host = null;
		int		port = ChatServer.DEFAULT_PORT;
		String	arg;
		int 	next = 0;

		while (true)
		{
			try
	    	{
	    		arg = args[next++].toLowerCase();
				if (arg.startsWith("-n") || arg.startsWith("-u"))
		    		user = args[next++];
				else if (arg.startsWith("-s"))
		    		host = args[next++];
				else if (arg.startsWith("-p"))
		    		port = Integer.parseInt(args[next++]);
				else host = args[next - 1];
	    	}
	    	catch (NumberFormatException n)
	    	{
	    		port = ChatServer.DEFAULT_PORT;
				System.err.println("WARNING\tbad port number, using "+port);
	    	}
	    	catch (ArrayIndexOutOfBoundsException a) break;
		}
		if (user == null) user = System.getProperty("user.name");
		ChatClient c = new ChatClient(user, host, port, false);
		c.connect(host == null || user == null);
    }



//----------------------------- ChatClient -----------------------------//

    public ChatClient(String u, String h, int p, boolean app)
    {
    	super("Java Chat");
		applet = app;
		user = u;			host = h;
		port = p;			connected = false;
		Panel controls = new Panel();
		text_font = new Font("Courier", Font.PLAIN, 12);
		button_font = new Font("Helvetica", Font.PLAIN, 14);

		if (!applet)
		{
			controls.setLayout(new GridLayout(1, (applet?2:4), 0, 0));
	    	conn = new Button("Connect");
	    	conn.setFont(button_font);
	    	conn.enable();
	    	controls.add(conn);
	    	disc = new Button("Disconnect");
	    	disc.setFont(button_font);
	    	disc.disable();
	    	controls.add(disc);
	    	save = new Button("Save");
	    	save.setFont(button_font);
	    	save.enable();
	    	controls.add(save);
	    	quit = new Button("Quit");
	    	quit.setFont(button_font);
	    	quit.enable();
	    	controls.add(quit);
	    	add("North", controls);
		}
		line = new TextField(80);
		line.setFont(text_font);
		line.disable();
		add("South", line);
		log = new TextArea(25,80);
		log.setFont(text_font);
		log.setEditable(false);
		add("Center", log);
		resize(preferredSize());
		pack();
		if (!applet) show();
    }



//---------------------------- handleEvent -----------------------------//

    public boolean handleEvent(Event e)
    {
    	Button b;
		if (e.id == Event.WINDOW_DESTROY) quit();
		else if (e.id == Event.ACTION_EVENT)
		{
			if (e.target instanceof TextField) return send_line();
	    	else if (e.target instanceof Button)
	    	{
	    		b = (Button) e.target;
				if (b == save) return log_save();
				else if (b == conn) return connect(true);
				else if (b == quit) quit();
				else if (b == disc)
				{
					disconnect();
		    		return true;
				}
			}
		}
		return false;
    }



//-------------------------------- run ---------------------------------//

    public void run()
    {
    	String next_user;
		String next_line;
		try
		{
			while (connected)
	    	{
	    		next_user = in_stream.readLine();
				next_line = in_stream.readLine();
				log_line(next_user, next_line);
			}
		}
		catch (IOException i)
		{
			System.err.println("run() - error reading from server");
	    	System.exit(0);
    	}
    }



//------------------------------ connect -------------------------------//

    synchronized boolean connect(boolean prompt)
    {
    	if ((prompt) && (!connected) && (!applet))
		{
			new ChatConnectDialog(this);
	    	return true;
		}
		else if ((connected) || (host == null) || (port == 0))
	    	return false;
		try
		{
			socket = new Socket(host, port);
	    	in_stream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	    	out_stream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	    	out_stream.writeBytes(user==null ? "anonymous\n" : user+'\n');
	    	out_stream.flush();
	    	connected = true;
	    	thisThread = new Thread(this);
	    	thisThread.start();
		}
		catch (IOException i) return false;
		catch (IllegalThreadStateException t)
		{
			try socket.close(); catch (IOException i) ;
	    	if (applet) quit();
	    	return false;
		}
		setTitle("Java Chat - session run from " + host);
		log.setText("");		log_user = "";
		line.enable();			line.requestFocus();
		if (applet) show();
		else
		{
			conn.disable();		disc.enable();
		}
		return true;
    }



//----------------------------- disconnect -----------------------------//

    synchronized void disconnect()
    {
    	if (!connected) return;
		connected = false;
		thisThread.stop();		// Stop the listening thread
		thisThread = null;
		try
		{
			in_stream.close();		// Close the streams and socket
	    	out_stream.close();
	    	socket.close(); 
		}
		catch (IOException i);
		setTitle("Java Chat");
		line.disable();
		if (applet)			hide();
		else
		{
			conn.enable();		disc.disable();
    	}
    }



//-------------------------------- quit --------------------------------//

    synchronized void quit ()
    {
    	disconnect();			// Kill the connection and thread
		dispose();			// Throw away the window
		if (!applet) System.exit(0);	// Throw away the system!
    }



//----------------------------- send_line ------------------------------//

    synchronized boolean send_line()
    {
    	String t = line.getText();
		if ((t.length() <= 0) || (!connected)) return false;
		try
		{
			out_stream.writeBytes(t + '\n');
	    	out_stream.flush();
		}
		catch (IOException i)
		{
			System.err.println("send_line() - unable to write to server");
	    	disconnect();
		}
		line.setText("");
		return true;
    }



//------------------------------ log_line ------------------------------//

    synchronized void log_line(String usr, String line)
    {
    	StringBuffer buffer;

		if ((usr == null) || (line == null)) return;
		if (!usr.equals(log_user))
		{
			log_user = usr;
	    	buffer = new StringBuffer(usr);
	    	buffer.append(":           ");
	    	buffer.setLength(11);
	    	buffer.append(' ');
	    	buffer.insert(0, '\n');
		}
		else 
		    buffer = new StringBuffer("            ");
		buffer.append(line);
		buffer.append('\n');
		log.appendText(buffer.toString());
    }



//------------------------------ log_save ------------------------------//

    synchronized boolean log_save()
    {
    	FileDialog		dialog;
		FileOutputStream	file;
		String			filename, text;
		byte			buffer[];

		dialog = new FileDialog(this, "Save the Log", FileDialog.SAVE);
		dialog.show();
		filename = dialog.getFile();
		if (filename == null) return false;
		try
		{
			file = new FileOutputStream(filename);
	    	text = log.getText();
	    	buffer = new byte[text.length()];
	    	text.getBytes(0, buffer.length, buffer, 0);
	    	file.write(buffer);
	    	file.close();
		}
		catch (IOException i)
	    	return false;
		return true;
    }

}   // class ChatClient



//======================================================================//
//									//
//                            ChatConnection				//
//									//
//  DESCRIPTION								//
//	This private class is really just an extension of the main	//
//	ChatClient class, designed to handle the connection dialog	//
//	window.  This provides user name, host name, and host port	//
//	numbers to a parent ChatClient object.				//
//  METHODS								//
//  1.	ChatConnectDialog(ChatClient) -- The constructor requires a 	//
//	parent chat client.  Creates the dialog window and returns	//
//  2.	handleEvent(Event) -- processes an event to handle the buttons	//
//	the text fields and the close widget				//
//  3.	close() -- closes the window, updates the user, host and port	//
//	fields of the parent ChatClient, and requests that a connection	//
//	be established							//
//									//
//======================================================================//



final class ChatConnectDialog extends Dialog
{
    ChatClient	parent;
    TextField	user_field;
    TextField	host_field;
    TextField	port_field;    
    Button		connect;
    Button		cancel;



//------------------------- ChatConnectDialog --------------------------//

    ChatConnectDialog(ChatClient par)
    {
    	super(par, "Establish Connection", true);
		parent = par;

		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(3, 1, 4, 4));
		Label l1 = new Label("user name", Label.RIGHT);
		l1.setFont(parent.button_font);
		p1.add(l1);
		Label l2 = new Label("server machine", Label.RIGHT);
		l2.setFont(parent.button_font);
		p1.add(l2);
		Label l3 = new Label("port number", Label.RIGHT);
		l3.setFont(parent.button_font);
		p1.add(l3);

		Panel p2 = new Panel();	
		p2.setLayout(new GridLayout(3, 1, 4, 4));
		user_field = new TextField(parent.user==null?"":parent.user, 30);
		user_field.setFont(parent.text_font);
		p2.add(user_field);
		host_field = new TextField(parent.host==null?"":parent.host,30);
		host_field.setFont(parent.text_font);
		p2.add(host_field);
		port_field = new TextField(String.valueOf(parent.port), 6);
		port_field.setFont(parent.text_font);
		p2.add(port_field);

		Panel p3 = new Panel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
		connect = new Button("Connect");
		connect.setFont(parent.button_font);	p3.add(connect);	
		cancel = new Button("Cancel");	
		cancel.setFont(parent.button_font);	p3.add(cancel);

		add("West", p1);
		add("Center", p2);
		add("South", p3);
		add("North", new Panel());
		add("East", new Panel());

		resize(preferredSize());
		pack();
		setResizable(false);
		show();
		if (parent.host == null && parent.user != null)
	    	host_field.requestFocus();
		else user_field.requestFocus();
    }



//---------------------------- handleEvent -----------------------------//

    public boolean handleEvent(Event e)
    {
    	if (e.id == Event.WINDOW_DESTROY)
		{
			dispose();
	    	return true;
		}
		else if (e.id == Event.ACTION_EVENT)
		{
			if (e.target instanceof TextField)
	    	{
	    		TextField f = (TextField) e.target;
				if (f == user_field) host_field.requestFocus();
				else if (f == host_field) port_field.requestFocus();
				else if (f == port_field) close();
	    	}
	    	else if (e.target instanceof Button)
	    	{
	    		Button b = (Button) e.target;
				if (b == connect) close();
				else if (b == cancel) dispose();
	    	}
	    	return true;
		}
		return false;
    }



//------------------------------- close --------------------------------//

    void close()
    {
    	String u = user_field.getText();
		String h = host_field.getText();
		int p = 0;
		try p = Integer.parseInt(port_field.getText());
		catch (NumberFormatException n) p = 0;
		if (u != null) parent.user = u;
		if (h != null) parent.host = h;
		if (p != 0) parent.port = p;
		dispose();
		parent.connect(false);
    }

}   // class ChatConnectDialog



//======================================================================//

