import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

class GameFrame   {
    static byte c=0,shot=0;
    MnPanel p;JFrame jfrm;
    private GameFrame() {
        if(MAIN.start){
            jfrm = new JFrame("ONE MAN ARMY");
            jfrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            jfrm.setContentPane(new JLabel(new ImageIcon("Accessories/sand.jpg")));
            jfrm.setIconImage(Toolkit.getDefaultToolkit().createImage("Accessories/index_soldier.jpg"));
            jfrm.addComponentListener(new ComponentAdapter() {
                    public void componentMoved(ComponentEvent ce) {
                        jfrm.setLocation(10,10);
                    }});
            jfrm.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
            p=new MnPanel();
            jfrm.addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent we){
                        String s=" "+p.x[0]+" "+p.x[1]+" "+p.x[2]+" "+p.x[3]+" "+p.y[0]+" "+p.y[1]+" "+p.y[2]+" "+p.y[3]+" "+
                            p.count[0]+" "+p.count[1]+" "+p.count[2]+" "+p.count[3]+" "+GameFrame.shot+" "+MnPanel.cm+" "+MnPanel.cr+" ";;
                        if(c==4){
                            ScoresSave.write();
                            SAVE.write("true"+s);}
                        else 
                            SAVE.write("false"+s);
                        Runtime.getRuntime().gc();
                        jfrm.dispose();
                    }

                    public void windowIconified(WindowEvent we){jfrm.setState(Frame.NORMAL);}
                });
            jfrm.add(p);
            jfrm.setFocusable(false);
            p.setFocusable(true);
            jfrm.setSize(1350, 720);
            jfrm.setResizable(false);
            jfrm.setAlwaysOnTop(true);
            jfrm.setVisible(true);
        }
    }

    static void main() {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GameFrame();
                }
            });
    }
}