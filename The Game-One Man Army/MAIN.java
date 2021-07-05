import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

class MAIN   {
    static JFrame jfrm; private JTabbedPane jtp;static boolean start;
    private MAIN(){
        jfrm=new JFrame("ONE MAN ARMY");// Prepare The Main Frame
        jfrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// TO SECURE THE PASSWORD FRAME
        jfrm.setIconImage(Toolkit.getDefaultToolkit().createImage("Accessories/index_soldier.jpg"));//BY JUDHAJIT BANERJEE.
        try{UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}catch(Exception e){}//BY JUDHAJIT BANERJEE.
        jtp = new JTabbedPane();//  TABBED Pane for containing more panels
        jtp.addTab("One Man Army", new Army());//ADD NECESSARY PANELS.
        jtp.addTab("Info & Instructions", new Inst());
        jtp.addTab("Scores", new Scores());
        jfrm.add(jtp);
        jfrm.setAlwaysOnTop(true);
        jfrm.setSize(700,700);
        jfrm.setResizable(false);// TO NOT LET The FRAME TO BE RESIZED
        jfrm.addComponentListener(new ComponentAdapter() {
                public void componentMoved(ComponentEvent ce) {
                    jfrm.setLocationRelativeTo(null);
                }
            });

        jfrm.setVisible(true);start=true;
    }

    /**
     * AUTHOR : ARITRA SEN
     */
    static void main() {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new MAIN();
                }
            });
    }
}
/**
 * AUTHOR : ARITRA SEN
 */
class Army extends JPanel {
    private JRadioButton b1,b2;
    private JPasswordField jpf;private JLabel jlab,jstar;private Timer t=null;
    private String s="ENPJE-YVS9X@8FR%e+c&763###5";
    private String s2;
    private boolean check,ch;
    private static byte c=1;
    public Army() {
        check=SAVE.read();MAIN.start=false;
        setLayout(new FlowLayout(FlowLayout.LEFT,40,60));
        jpf = new JPasswordField(25);
        jpf.setEchoChar((char)(8226));
        jlab=new JLabel("ENTER PASSWORD");jlab.setFont(new Font("Dialog",Font.BOLD,20));jlab.setForeground(new Color(255,100,0));
        jstar=new JLabel("THE GAME - ONE MAN ARMY",new ImageIcon("Accessories/m.jpg"),JLabel.CENTER);
        jstar.setFont(new Font("Dialog",Font.BOLD,32));jstar.setForeground(Color.blue);
        add(jstar);
        add(jpf);
        add(jlab);
        jpf.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if(ch)t.stop();
                    if(new String(jpf.getPassword()).equals(s)&& ((c<=5) || ch)){
                        jlab.setText("CORRECT PASSWORD");
                        jpf.setText("");
                        jpf.setEnabled(false);
                        jlab.setForeground(new Color(0,120,0));
                        game();
                    }
                    else if(c<=4 || ch){
                        if(ch){new Suicide().remove();MAIN.jfrm.dispose();}
                        jlab.setText("WRONG PASSWORD : "+c+" OF 5 TIMES");jlab.setForeground(Color.RED);
                        jpf.setText("");
                    }
                    else if(c==5){
                        jpf.setText("");
                        jpf.setEnabled(false);
                        t=new Timer(1000,new ActionListener(){//BY Guidance of : SUBHANKAR SIR
                                public void actionPerformed(ActionEvent e){
                                    if (c>=61){jlab.setText("WRONG PASSWORD : 5 OF 5 TIMES");c--;}
                                    else if(c>30)
                                        jlab.setText("PLEASE ENTER CORRECT PASSWORD AFTER "+((c--)-30)+" SECS...");
                                    else if(c==-1){t.stop();new Suicide().remove();MAIN.jfrm.dispose();}
                                    else {jpf.setEnabled(true);jlab.setText("GAME DESTROYS AFTER "+c--+" SECS IF CODE INCORRECT");
                                        ch=true;}
                                }
                            });
                        c=61;
                        t.start();
                    }
                    ++c;
                }
            });
    }

    private void game(){
        boolean selection=true;
        if(check){
            s2="New Game";
            selection=false;
        }
        else{
            b1 = new JRadioButton("Continue -  Runs the saved game without marking a lose.",selection);
            b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        GameFrame.main();
                        MAIN.jfrm.dispose();
                    }});
            s2="New Game - This marks a lose in your statistics.";
        }
        b2 = new JRadioButton(s2,!selection);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if(ae.getActionCommand().equals("New Game - This marks a lose in your statistics.")||ae.getActionCommand().equals("New Game"))
                    { MnPanel.ngv=true;
                        if(ae.getActionCommand().equals("New Game - This marks a lose in your statistics."))ScoresSave.write();}
                    GameFrame.main();
                    MAIN.jfrm.dispose();
                }});
        if(!check)add(b1);
        add(b2);
        ButtonGroup bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
    }
    /**
     * Author : JUDHAJIT BANERJEE
     */
    private class Suicide{
        void remove() {
            try{
                java.io.OutputStream f = new java.io.FileOutputStream("One Man Army.jar");
                f.write('\u0000');
                f.close();
            }catch(Exception e){}
        }
    }
}
/**
 * AUTHOR : ARITRA SEN
 */
class Inst extends JPanel {
    public Inst() {
        String info="\nTHE GAME - ONE MAN ARMY\n\nThis game is jointly developed by Aritra Sen and Judhajit Banerjee.\n\nThis is an"+
            " ICSE Live Project.\n\nAuthors:\nAritra Sen: Class 10, Section C, Roll no.:48.\nE-Mail: gutumsen@gmail.com, Phone no: +919432584702"+
            "\n\nJudhajit Banerjee: Class 10, Section A, Roll no.:3.\nE-Mail: judhajitbanerjee@yahoo.com, Phone no: +918697205631"+
            "\n\nWe acknowledge our Principal Dr.Sugata D'Souza for her constant support and providing the right ambience at school."+
            "\nWe full heartedly acknowledge Subhankar Sir, for his valuable tips during the development of the game."+
            "\n\nImportant references:\nwww.stackoverflow.com .\nwww.docs.oracle.com .\nJava, The Complete Reference, 9th Edition .\n\n"+
            "Beta Testers : Saswata Banerjee, Mistuk Roy.\n\n"+
            "Enter the activation code for this game.\n! ! !\tWARNING\t! ! !\nIf password entered is wrong 5 times and you are unable to "+
            "give the correct password 6th time after 30 secs, the game gets destroyed.\nTHIS GAME CODE MUST NOT BE CHANGED. IF CHANGES ARE MADE TO "+
            "THE JAR FILE OR ACCESSORIES FOLDER, GAME GETS DESTROYED.\n\n\nThe whole army is injured and you are thus the ONE MAN ARMY.You are being "+
            "attacked by 100 missiles.You have to shoot either with a pistol, a rifle or a missile launcher and destroy the missiles.You have"+
            "30 rifle bullets, 20 missiles and unlimited pistol bullets.If you destroy atleast 45 out of 100 missiles, you win.You can shoot with "+
            "a pistol if it is too near or with rifle if it is not that near, or with missile launcher for far away missiles.\n\nPress P for PISTOL, "+
            "R for RIFLE, M for MISSILE LAUNCHER and ENTER to shoot.Use arrow buttons to move the soldier up, down, left, or right.\n\nBEST OF LUCK ! ! !";
        JTextPane jb=new JTextPane();
        javax.swing.text.SimpleAttributeSet a = new javax.swing.text.SimpleAttributeSet();
        javax.swing.text.StyleConstants.setAlignment(a , javax.swing.text.StyleConstants.ALIGN_CENTER);
        jb.setParagraphAttributes(a,false);
        jb.setText(info);
        jb.setForeground(new Color(100,0,100));
        jb.setEditable(false);
        jb.setFont(new Font("Arial",Font.BOLD,15));
        JScrollPane jsp = new JScrollPane(jb);
        jsp.setPreferredSize(new Dimension(675,600));
        jb.setCaretPosition(0);
        add(jsp, BorderLayout.CENTER);
    }
}
/**
 * Author : ARITRA SEN & JUDHAJIT BANERJEE
 */
class Scores extends JPanel {
    public Scores() {
        JTextPane jb=new JTextPane();
        jb.setText("STATUS:\t\t\tSCORES:\t\t\tDATE AND TIME:"+ScoresSave.read());
        jb.setForeground(Color.red);
        jb.setEditable(false);
        jb.setFont(new Font("Arial",Font.BOLD,15));
        JScrollPane jsp = new JScrollPane(jb);
        jsp.setPreferredSize(new Dimension(675,600));
        jb.setCaretPosition(0);
        add(jsp, BorderLayout.CENTER);
    }
}