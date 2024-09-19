import java.awt.*;
import java.applet.Applet;
import chcs.envirachat.ui.*;
import chcs.common.ui.*;

public class GOD extends Applet
{
	
	public static void main(String argv[])
	{
		GOD god = new GOD();
		god.init();
		god.start();
		
	}
	
	public void init()
	{
		Frame f = new Frame("Welcome to the Graphic Object Designer!");
		Panel txtPanel = new Panel();
		Panel btnPanel = new Panel();
		Dialog dl = new Dialog(f, "Graphic Object Designer",true);
		
		dl.setResizable(false);
		dl.setLayout(new GridLayout(2,1));
	
		dl.add(txtPanel);
		dl.add(btnPanel);
		
		txtPanel.add(new Label("What would you like to use?"));
		//btnPanel.add(new Button3D("Avatar Designer"));
		//btnPanel.add(new Button3D("Map Designer"));
		btnPanel.add(new Button("Quit"));
		
		Button3D b3d = new Button3D("Quit");
		b3d.setSize(40,40);
		
		btnPanel.add(b3d);
		btnPanel.add(new Button("Quit"));
		
		dl.setSize(320,100);
		dl.show();

	}
	
	public void start()
	{
	
	
	}

}


class avatarFrame extends Frame
{
	avatarFrame(String title)
	{
		super(title);
		
	}
}

class mapFrame extends Frame
{
	mapFrame(String title)
	{
		super(title);
	}
}