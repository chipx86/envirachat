import java.awt.*;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.util.Vector;




/**** massage box Class **************************************/
class MBox extends Dialog {
    Button button1;
    Label label1;
    Panel p;

    public MBox(String title,String msg){
        super(null,title,true);
        setResizable(true);
        Dimension d , box;
        d = getToolkit().getScreenSize();
        this.setLayout(new BorderLayout());
        TextArea ta = new TextArea(ReformatMessage(msg),5,45);
        ta.setEditable(false);
        ta.setFont(new Font("Dialog",Font.PLAIN,12));
        add("Center",ta);
        button1=new Button(" OK ");
        Panel p = new Panel();
        p.setLayout(new FlowLayout());
        p.add(button1);
        add("South",p);
        pack();
        box = size();
        move((d.width - box.width)/2 , (d.height-box.height)/2);
        button1.requestFocus();
    }

    public boolean handleEvent(Event evt) {
        if( (evt.id == Event.ACTION_EVENT && evt.target == button1) || (evt.id == Event.WINDOW_DESTROY) ){
            hide();
            dispose();
            pj.MBOXgeladen = false;
            return true;
        }
        return super.handleEvent(evt);
    }

    String ReformatMessage(String message) {
        int iIndex = 0;
        int iOldIndex = 0;
        int iLength = message.length();
        int iLineLen = 40;
        String sub;
        Vector v = new Vector();

        while(iIndex < iLength && iIndex != -1) {
            iIndex += iLineLen;
            iIndex = message.indexOf(' ', iIndex);
            if (iIndex >= iLength)
            break;
            if (iIndex == -1)
                sub = message.substring(iOldIndex);
            else
                sub = message.substring(iOldIndex, iIndex);
            sub.trim();
            sub += "\r\n";
            v.addElement(sub);
            iOldIndex = iIndex;
        }

        if (iIndex >= iLength){
            sub = message.substring(iOldIndex);
            sub.trim();
            sub += "\r\n";
            v.addElement(sub);
        }

        String linedString = new String();
        for (int i = 0; i < v.size(); i++)
            linedString += v.elementAt(i);
        return linedString;
    }


    /****show a message box  with a Windows Title********************/
    public static void msgBox(String title,String text) {
        MBox m = new MBox(title,text);
        m.show();
    }

    /****show a massage box****************************************/
    public static void msgBox(String text){
        MBox m = new MBox("",text);
        m.show();
    }


}

