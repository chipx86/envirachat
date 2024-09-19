import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.*;
import chcs.common.net.*;

public final class EnvirachatServer extends Server {

	public static void main(String args[]) {
		EnvirachatServer	session;
		ChatConnection		chat_connect;
		Socket				chat_socket;
		int next		=	0;
		String				arg;
		
		int loggin		=	LOG_SYSTEM;
		DataOutputStream log = null;
		int port		=	DEFAULT_PORT;
		
		System.out.println("EnviraChat Server v1.0");
		System.out.println("Copyright (C) 1997, Christian Hammond Computer Services");
		
		while (true) {
			try {
				arg = args[next++].toLowerCase();
				if (arg.startsWith("-v")) {	// Verbose - Switch to turn on all logging.
					logging = LOG_USER | LOG_SYSTEM | LOG_ECHO;
					System.out.println("All logging enabled");
				}
				else if (arg.startsWith("-q")) {// Quiet - Switch to turn off all logging.
					logging = LOG_DISABLED;
					System.out.println("All logging disabled");
				}
				else if (arg.startsWith("-u")) {// User - Switch to have user messages logged.
					logging |= LOG_USER;
					System.out.println("User messages logged");
				}
				else if (arg.startsWith("-s")) {// System - Switch to have system messages logged.
					logging |= LOG_SYSTEM;
					System.out.println("System messages logged");
				}
				else if (arg.startsWith("-e")) {// Echo - Switch to have system messages echoed to all users.
					logging |= LOG_ECHO;
					System.out.println("System messages echoed to all users");
				}
				else if (arg.startsWith("-l")) {// Log <string> - The file name to log messages into.
					String fname = args[next++];
					log = new DataOutputStream(new FileOutputStream(fname));
					System.out.println("Messages logged into "+fname);
				}
				else if (arg.startsWith("-p")) {// Port <integer> - Port number to establish server on.
					String curport = args[next++];
					port = Integer.parseInt(curport);
					System.out.println("Server running on port "+curport);
				}
				else
					System.err.println("WARNING\tunknown option: "+arg);
			}
			catch (IOException i) {
				log = null;
				System.err.println("WARNING\tunable to create log file.");
			}
			catch (NumberFormatException n) {
				port = DEFAULT_PORT;
				System.err.println("WARNING\tbad port number. Using "+DEFAULT_PORT);
			}
			catch (ArrayIndexOutOfBoundsException a) {
				break;
			}
		}
		session = new EnvirachatServer(logging, log, port);
		
		// Start an infinite loop accepting new connections and forking
		// ChatConnection threads to handle each individual connection.
		
		while (true) {
			try {
				chat_socket = session.server.accept();
				chat_connect = new ChatConnection((Server)session, chat_socket);
			}
			catch (IOException e) {}
		}
	}
	
	public EnvirachatServer(int lg, DataOutputStream l, int p) {
		super(lg, l, p);
	}
}