import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.util.Calendar;

import java.util.Formatter;

class ScoresSave{
    static boolean won=false;
    static void write() {
        String s="SCORE     : "+GameFrame.shot+"    OUT    OF    "+
        (byte)(MnPanel.count[0]+MnPanel.count[1]+MnPanel.count[2]+MnPanel.count[3]-4)+".          "+
        new Formatter().format("%tc",Calendar.getInstance());
        if(won)s="\nGame    won     .     "+s;else s="\nGame  lose     .       "+s;
        try{
            FileOutputStream fo=new FileOutputStream("Accessories/scoretxt.GASJB",true);
            fo.write(s.getBytes());
            fo.close();
        } catch (Exception exc) {}
    }

    static String read() {
        String s="";
        try {
            FileInputStream fi=new FileInputStream("Accessories/scoretxt.GASJB");
            while(fi.available()>0)s+=(char) fi.read();
            fi.close();
        }catch (Exception exc) {}
        return s;
    }
}