import java.awt.*;

import javax.swing.*;

class BombPanel extends JPanel implements Runnable {
    Image img;Thread t;
    private int l=0,a=0;
    BombPanel(int p,int a) {
        this.a=a;
        setOpaque(false);
        img =  Toolkit.getDefaultToolkit().createImage("Accessories/bomb.jpg");
        setPreferredSize(new Dimension(1350,165));
        do{MnPanel.y[a]=(int)(Math.random()*500);}while(MnPanel.y[a]>132);
        do{l=(int)(Math.random()*10);}while(l>4||l<=0);
        t=new Thread(this);
        t.setPriority(p);
        t.start();
    }

    public void run(){
        for(;MnPanel.x[a]>=0 && MnPanel.count[a]<=25 && !ScoresSave.won;MnPanel.x[a]--){
            if(MnPanel.x[a]==0){
                MnPanel.x[a]=1350;
                do{MnPanel.y[a]=(int)(Math.random()*500);}while(MnPanel.y[a]>132);
                do{l=(int)(Math.random()*10);}while(l>5||l<=2);
                ++MnPanel.count[a];
            }
            try{Thread.sleep(l);}catch(Exception e){}
            repaint();
            MnPanel.jl.setText("MISSILES SHOT : "+GameFrame.shot+" OUT OF "+
                (byte)(MnPanel.count[0]+MnPanel.count[1]+MnPanel.count[2]+MnPanel.count[3]-4));
            if((byte)(MnPanel.count[0]+MnPanel.count[1]+MnPanel.count[2]+MnPanel.count[3]-4)==100)
                MnPanel.jl.setText("                                                           "+"GAME OVER ."+MnPanel.jl.getText());
        }
        if(ScoresSave.won)MnPanel.jl.setText("              GAME WON");
        ++GameFrame.c;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img,MnPanel.x[a],MnPanel.y[a],this);
    }
}