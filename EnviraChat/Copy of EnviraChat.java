// EnviraChat.java
/*
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
*/
import java.io.*;
import java.net.*;
import chcs.envirachat.ui.*;
import chcs.envirachat.ui.character.objects.*;
import chcs.common.ui.*;
import netscape.application.*;
import netscape.util.*;


public class EnviraChat extends Application {

	//Frame f = new Frame("EnviraChat v1.0 alpha 1");
	private static boolean isApplication = false;
	static Map map;
	static String mapstr = "***  ^^^^^ ***\n"+
						   "***  ^   ^ ***\n"+
						   "***^ ^   ^ ***\n"+
						   "***  ^^^^^ ***\n";

	public void init() {
		InternalWindow mainWindow;
		InternalWindow mapWindow;
		InternalWindow chatWindow;
		InternalWindow direcWindow;
		InternalWindow btnWindow;

	    super.init();
	    
	    //mainRootView().setColor(Color.lightGray);
		if (!isApplication) {
			// Set up Main Window
			mainWindow = new InternalWindow(InternalWindow.PALETTE_LAYER,4,4,592,50);
			mainWindow.setTitle("EnviraChat v1.0 alpha 1");
			mainWindow.setResizable(true);
			mainWindow.setCloseable(true);
						
			// Set up Map Window
			mapWindow = new InternalWindow(4,54,403,203);
			mapWindow.setTitle("Map");
			mapWindow.setResizable(true);
			mapWindow.setCloseable(true);
			
			// Set up Button Config Window
			btnWindow = new InternalWindow(411,54,185,238);
			btnWindow.setTitle("Character");
			btnWindow.setResizable(true);
			btnWindow.setCloseable(true);
			
			// Set up Directional Pad Window
			direcWindow = new InternalWindow(InternalWindow.PALETTE_LAYER,411,296,185,100);
			direcWindow.setTitle("Directional Pad");
			direcWindow.setResizable(false);
			direcWindow.setCloseable(true);
			
			// Set up Chat Window
			chatWindow = new InternalWindow(4,261,403,135);
			chatWindow.setTitle("Chatbox");
			chatWindow.setResizable(true);
			chatWindow.setCloseable(true);
			
			// Show them all
			mainRootView().addSubview(mainWindow);
			mainRootView().addSubview(mapWindow);
			mainRootView().addSubview(btnWindow);
			mainRootView().addSubview(direcWindow);
			mainRootView().addSubview(chatWindow);
			mainWindow.show();
			mapWindow.show();
			btnWindow.show();
			direcWindow.show();
			chatWindow.show();

		//ContainerView containerView = new ContainerView(10,10,32,32);
		//TreeImage treeimage = new TreeImage();
		//containerView.setImage(treeimage);
		//mapWindow.addSubview(containerView);
		map = new Map(mapstr, 10, 10, 256, 128);
		mapWindow.addSubview(map);

		}
		
		// Resume normal configurations here
		//map = new Map("*******\n");
		//mapWindow.add(map);
		
	}

	public static void main(String args[]) {
		ExternalWindow mainWindow;
		ExternalWindow mapWindow;
		ExternalWindow chatWindow;
		ExternalWindow direcWindow;
		ExternalWindow btnWindow;
		EnviraChat app;
		
		app = new EnviraChat();
		
		mainWindow = new ExternalWindow();
		mainWindow.moveTo(4,4);
		mainWindow.sizeTo(592, 50);
		//mainWindow.setMainRootView(mainWindow.rootView());
		mainWindow.setTitle("EnviraChat v1.0 alpha 1");
		
		// Set up Map Window
		mapWindow = new ExternalWindow();
		mapWindow.moveTo(4, 54);
		mapWindow.sizeTo(403, 203);
		mapWindow.setTitle("Map");
		//mapWindow.setResizable(false);
		//mapWindow.setCloseable(true);
		
		// Set up Buttons and Config Window
		btnWindow = new ExternalWindow();
		btnWindow.moveTo(411, 54);
		btnWindow.sizeTo(185, 238);
		btnWindow.setTitle("Character");
		//btnWindow.setResizable(false);
		//btnWindow.setCloseable(false);

		
		// Set up Directional Pad Window
		direcWindow = new ExternalWindow();
		direcWindow.moveTo(411, 296);
		direcWindow.sizeTo(185, 100);
		direcWindow.setTitle("Directional Pad");
		direcWindow.setResizable(false);
		//direcWindow.setCloseable(false);
		
		// Set up Chat Window
		chatWindow = new ExternalWindow();
		chatWindow.moveTo(4, 261);
		chatWindow.sizeTo(403, 135);
		chatWindow.setTitle("Chatbox");
		chatWindow.setResizable(true);
		//chatWindow.setCloseable(false);
		
		// Show them all
		mainWindow.show();
		mapWindow.show();
		btnWindow.show();
		direcWindow.show();
		chatWindow.show();
		
		//ContainerView containerView = new ContainerView(10,10,32,32);
		//TreeImage treeimage = new TreeImage();
		//containerView.setImage(treeimage);
		//mapWindow.addSubview(containerView);
		map = new Map(mapstr, 10, 10, 256, 128);
		mapWindow.addSubview(map);


		isApplication = true;
		app.run();
		System.exit(0);
	}

}