package chcs.common.ui;

import java.awt.*;


public class Panel3D extends Panel {

  int outerThick = 1;
  int innerThick = 1;
  int flatThick = 1;
  boolean outerRaised = true;
  boolean innerRaised = false;

  public final static int MODE_RAISED = 1;
  public final static int MODE_LOWERED = 2;
  
  
  public Panel3D() {
  }

  public Panel3D(int outer, int othick, int inner, int inThick, int flat) {
    if ( outer == MODE_RAISED ) {
      outerRaised = true;
    } else {
      outerRaised = false;
    }
    outerThick = othick;

    if ( inner == MODE_RAISED ) {
      innerRaised = true;
    } else {
      innerRaised = false;
    }
    innerThick = inThick;
    flatThick = flat;

    

  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    
    if ( outerRaised ) {
      g.setColor( Color.white );
    }
    else {
      g.setColor( Color.gray );
    }
    for (int i = 0; i < outerThick; i++ ) {
      g.fillRect( 0, i, d.width - i  , 1 ); // outer
      g.fillRect( i, 0, 1, d.height - i  ); // outer
    }
      

    if ( innerRaised ) {
      g.setColor( Color.white );
    }
    else {
      g.setColor( Color.gray );
    }
    for (int i = 0; i < innerThick; i++ ) { 
      g.fillRect( outerThick + flatThick, outerThick + flatThick + i, d.width - (2 * (outerThick + flatThick)) - i , 1); // inner
      g.fillRect( outerThick + flatThick + i, outerThick + flatThick, 1,  d.height - (2 * ( outerThick + flatThick)) - i); //inner
    }



    if ( !innerRaised ) {
      g.setColor( Color.white );
    }
    else {
      g.setColor( Color.gray );
    }
    for (int i = 0; i < innerThick; i++ ) { 
      g.fillRect( d.width - outerThick - flatThick - innerThick + i, outerThick + flatThick + innerThick - 1 - i, 1, d.height - innerThick - 1 + i - (2 * (outerThick + flatThick))); // inner
      g.fillRect( outerThick + flatThick + innerThick - 1 - i, d.height - outerThick - flatThick - innerThick - 1 + i, d.width - (2 * (flatThick + outerThick) ) - innerThick + 1 + i, 1); // inner
    }

    if ( outerRaised ) {
      g.setColor( Color.gray );
    }
    else {
      g.setColor( Color.white );
    }
    for (int i = 0; i < outerThick; i++ ) {
      g.fillRect( d.width - outerThick + i, outerThick - 1 - i, 1, d.height - outerThick  - 1 + i ); //outer
      g.fillRect( outerThick - 1 - i, d.height - outerThick - 1 + i, d.width - (outerThick - 1 - i), 1); // outer
    }

  }

}