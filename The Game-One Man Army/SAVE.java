import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.DataInputStream;

import java.io.DataOutputStream;

class SAVE   {
    static boolean read() {
        boolean b=true;
        try {
            DataInputStream in=new DataInputStream(new FileInputStream("Accessories/savetxt.GASJB"));
            b=in.readBoolean();
            for(int i=0;i<=3;i++)
                MnPanel.x[i]=in.readInt();
            for(int i=0;i<=3;i++)
                MnPanel.y[i]=in.readInt();
            for(int i=0;i<=3;i++)
                MnPanel.count[i]=in.readByte();
            GameFrame.shot=in.readByte();
            MnPanel.cm=in.readByte();
            MnPanel.cr=in.readByte();
            in.close(); 
        }catch (java.io.IOException exc) {throw new RuntimeException();}
        return b;
    }

    static void write(String s) {
        if(MAIN.start){
            try {
                java.util.Scanner sc=new java.util.Scanner(s);
                DataOutputStream f= new DataOutputStream(new FileOutputStream("Accessories/savetxt.GASJB"));
                f.writeBoolean(sc.nextBoolean());
                for(int i=1;i<=8;i++)
                    f.writeInt(sc.nextInt());
                for(int i=1;i<=7;i++)
                    f.writeByte(sc.nextByte());
                f.close();
            } catch (Exception exc) {}
        }
    }
}