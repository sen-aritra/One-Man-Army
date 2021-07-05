import java.awt.*;

import java.awt.event.*;

import javax.sound.sampled.*;

import javax.swing.*;

class MnPanel extends JPanel    {
    private Image ip,ir,im,ie,img;
    private volatile boolean am=false,ar=false,shoot=false;
    BombPanel pp,pp1,pp2,pp3;
    static JLabel jl;private Timer t;
    static volatile boolean target,ngv;
    static byte count[]={1,1,1,1},cm=0,cr=0;
    static int x[]=new int[4],y[]=new int [4],pos;
    private int a=0,b=0,c=175,r=800,rad=5,posx,posy;
    private Color clr=Color.black;
    MnPanel(){
        //Prepare the Panel
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        setPreferredSize(new Dimension(1350,720));
        //Initialize values for new game
        if(ngv){for(int i=0;i<=3;i++){count[i]=1; x[i]=(int)(Math.random()*1000);}GameFrame.shot=0;cr=0;cm=0;}
        //create the JLabel jl to show current condition
        jl=new JLabel("MISSILES SHOT : 0 OUT OF 0");
        jl.setFont(new Font("Dialog",Font.BOLD,24));jl.setForeground(Color.blue);
        //load Images
        im=Toolkit.getDefaultToolkit().createImage("Accessories/m.jpg");
        ir=Toolkit.getDefaultToolkit().createImage("Accessories/r.jpg");
        ip=Toolkit.getDefaultToolkit().createImage("Accessories/p.jpg");
        ie=Toolkit.getDefaultToolkit().createImage("Accessories/explosion.jpg");
        img=ip;
        //add panels
        pp = new BombPanel(10,0);
        pp1 = new BombPanel(9,1);
        pp2 = new BombPanel(8,2);
        pp3 = new BombPanel(7,3);
        add(pp);
        add(pp1);
        add(pp2);
        add(pp3);
        add(jl);
        addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent ke){
                    int key=ke.getKeyCode();
                    switch(key){
                        case KeyEvent.VK_ENTER:
                        if(!shoot){
                            try{
                                Clip c=AudioSystem.getClip();
                                c.open(AudioSystem.getAudioInputStream(new java.io.File("Accessories/gun.wav")));
                                c.start(); 
                            }catch(Exception e){}
                            shoot=true;
                            if(am)cm++;else if(ar)cr++;
                            if((am&&cm>20) || (ar&&cr>30)){img=ip;c=175;r=800;rad=5;clr=Color.black;am=false;ar=false;}
                            posx=a;posy=b+32;pos=(int)(posy/165);
                        }
                        break;
                        case KeyEvent.VK_R:
                        if(cr<=30&&!shoot){img=ir;am=false;ar=true;c=150;r=1000;rad=10;clr=Color.red;}
                        else if(!shoot){img=ip;c=175;r=800;rad=5;clr=Color.black;ar=false;}
                        break;
                        case KeyEvent.VK_M:
                        if(cm<=20 && !shoot){img=im;am=true;ar=false;c=130;r=1200;rad=20;clr=Color.orange;}
                        else if(!shoot){img=ip;c=175;r=800;rad=5;clr=Color.black;am=false;}
                        break;
                        case KeyEvent.VK_P:
                        if(!shoot){
                            img=ip;c=175;r=800;rad=5;clr=Color.black;am=false;ar=false;}
                        break;
                        case KeyEvent.VK_RIGHT:
                        if(a>=1250)
                            a=0;
                        else
                            a+=5;
                        break;
                        case KeyEvent.VK_DOWN:
                        if(b>=650)
                            b=0;
                        else
                            b+=5;
                        break;
                        case KeyEvent.VK_UP:
                        if(b<=-50)
                            b=600;
                        else
                            b-=5;
                        break;
                        case KeyEvent.VK_LEFT:
                        if(a<=0)
                            a=1250;
                        else
                            a-=5;
                        break;
                    }
                    repaint();
                }
            });
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img,a,b,this);
        if(shoot){
            g.setColor(clr);g.fillOval(posx+c,posy,rad,rad);
            step();
        }
        if(target){
            try{
                Clip c=AudioSystem.getClip();
                c.open(AudioSystem.getAudioInputStream(new java.io.File("Accessories/expl.wav")));
                c.start(); 
            }catch(Exception e){}
            g.drawImage(ie,x[pos],y[pos],this);
            target=false;x[pos]=1350;y[pos]=(int)(Math.random()*100);++MnPanel.count[pos];
            if(++GameFrame.shot>=45){remove(pp);remove(pp1);jl.setFont(new Font("Dialog",Font.BOLD,95));ScoresSave.won=true;}
        }
    }

    private void step(){
        t=new Timer(1,new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    if((posy%165)+8>=y[pos] && (posy%165)-52<=y[pos] && posx+c==x[pos])
                        target=true;
                    if(++c>=r || target){shoot=false;
                        if(am)c=130;else if(ar)c=150;else c=175;}
                }
            });
        t.setRepeats(false);
        t.start();
    }
}