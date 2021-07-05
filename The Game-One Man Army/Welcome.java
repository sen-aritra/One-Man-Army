import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Ellipse2D;

import javax.swing.*;

/**
 * AUTHOR : Aritra Sen
 */
public class Welcome   {
    private Welcome() {
        JFrame jf=new JFrame("WELCOME");//The Welcome Screen
        jf.setUndecorated(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setIconImage(Toolkit.getDefaultToolkit().createImage("Accessories/index_soldier.jpg"));//BY JUDHAJIT BANERJEE.
        jf.setShape(new Ellipse2D.Double(0,0,1000,700));//Set Oval Shape of FRAME
        JPanel panel = new JPanel()  {
                protected void paintComponent(Graphics g) {
                    Paint p =new GradientPaint(0.0f, 0.0f, new Color(220,100,220,0),0.0f, 700, new Color(220,100,220,100), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);//Set Gradient Paint For Better Look and Fill it
                    g2d.fillOval(0, 0, 1000, 700);
                    g2d.setStroke(new BasicStroke(5));//For RED Boundary of panel
                    g2d.setColor(Color.RED);
                    g2d.drawOval(0,0,1000,700);
                    g2d.drawString("WELCOME ! ! !",350,70);//Information of AUTHORS.
                    g2d.setColor(new Color(150,0,175));g2d.drawString("TO    THE    GAME  - ",300,150);
                    g2d.setColor(new Color(255,100,0));g2d.drawString("ONE      MAN      ARMY  ...",240,240);
                    g2d.setColor(Color.green);g2d.drawString("BY",450,320);
                    g2d.setColor(Color.blue);g2d.drawString("ARITRA   SEN   &   JUDHAJIT   BANERJEE",25,390);
                    g2d.drawImage( Toolkit.getDefaultToolkit().getImage("Accessories/creator1.jpg"),150,425,this);
                    g2d.drawImage( Toolkit.getDefaultToolkit().getImage("Accessories/sign1.jpg"),310,460,this);
                    g2d.drawImage( Toolkit.getDefaultToolkit().getImage("Accessories/creator2.jpg"),450,425,this);
                    g2d.drawImage( Toolkit.getDefaultToolkit().getImage("Accessories/sign2.jpg"),610,470,this);
                    g2d.setColor(new Color(150,25,0));g2d.drawString("CLICK TO PLAY THE GAME .",180,600);
                }};
        panel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 40));//Set FONT Of panel
        jf.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent me) {
                    //To dispose
                    MAIN.main();jf.dispose();
                }
            });
        jf.setSize(1000,700);//Prepare The Frame
        jf.setLocationRelativeTo(null);
        jf.setBackground(new Color(0,0,0,0));
        jf.setContentPane(panel);//add Panel
        jf.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jf.setAlwaysOnTop(true);
        jf.setVisible(true);//Make FRAME VISIBLE.
    }

    /**
     * Author : ARITRA SEN AND JUDHAJIT BANERJEE.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Welcome();//Call for Welcome Screen
                }
            });
    }
}