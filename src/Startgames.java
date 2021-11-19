import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

class Startgames extends JFrame {

    Startgames(){
        add(new GameArea());
    }


    public static class GameArea extends JPanel{
        URL imageURL = this.getClass().getResource("image/car.png");
        Image image = new ImageIcon(imageURL).getImage();

        //==================================================mushroom====================================================
        URL imageURL2 = this.getClass().getResource("image/mushroom.png");
        Image image2 = new ImageIcon(imageURL2).getImage();

        URL imageURL3 = this.getClass().getResource("image/mushroom.png");
        Image image3 = new ImageIcon(imageURL3).getImage();

        URL imageURL4 = this.getClass().getResource("image/mushroom.png");
        Image image4 = new ImageIcon(imageURL4).getImage();

        URL imageURL5 = this.getClass().getResource("image/mushroom.png");
        Image image5 = new ImageIcon(imageURL5).getImage();

        URL imageURL6 = this.getClass().getResource("image/mushroom.png");
        Image image6 = new ImageIcon(imageURL6).getImage();

        URL imageURL7 = this.getClass().getResource("image/mushroom.png");
        Image image7 = new ImageIcon(imageURL7).getImage();

        URL imageURL8 = this.getClass().getResource("image/mushroom.png");
        Image image8 = new ImageIcon(imageURL8).getImage();

        URL imageURL9 = this.getClass().getResource("image/mushroom.png");
        Image image9 = new ImageIcon(imageURL9).getImage();
        //==============================================================================================================

        URL BackgroundURL = this.getClass().getResource("image/bg1.jpg");
        Image bg = new ImageIcon(BackgroundURL).getImage();

        URL BackgroundURL1 = this.getClass().getResource("image/bgstart.png");
        Image bg1 = new ImageIcon(BackgroundURL1).getImage();

        URL BackgroundURL2 = this.getClass().getResource("image/bg2.jpg");
        Image bg2 = new ImageIcon(BackgroundURL2).getImage();

        int x1 = 90; //x of a car
        int y1 = 250; //y of a car

        int x[] = {300,300,550,800,800,1150,1300,1300}; //mushroom
        int y[] = {120,450,290,120,450,290,120,450}; //mushroom

        public int times = 20 ;
        boolean timestart = false;

        int score = 100;
        int distanceP;

        char KeyChar = 'A';

        Thread time = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(10);
                    }catch(Exception e){ }

                    if(timestart == false){
                        repaint();
                    }
                }
            }
        });
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(timestart == false){
                        times = (times-1);
                    }
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });

        GameArea(){
            setFocusable(true);
            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode())
                    {
                        case KeyEvent.VK_DOWN: y1+=20; break;
                        case KeyEvent.VK_UP: y1-=20; break;
                        case KeyEvent.VK_LEFT: x1-=20; break;
                        case KeyEvent.VK_RIGHT: x1+=20; break;
                        default: KeyChar = e.getKeyChar();
                    }
                    for(int i=0;i<8;i++){
                        distanceP = (int)Math.sqrt((Math.pow(Math.abs(x1-x[i]),2))+(Math.pow(Math.abs(y1-y[i]),2)));
                        if(distanceP<100){
                            score-=50;
                        }
                    }
                    repaint();
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
            //=================
            time.start();
            t.start();
            //=================
        }
        class Listener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        }

        public void paintComponent(Graphics g){
            super.paintComponents(g);
            if(times <= 0 || score<=0){
                this.setLayout(null);
                g.drawImage(bg1,0,0,this.getWidth(),this.getHeight(),this);
                g.setColor(Color.WHITE);
                //g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,40));
                g.setFont(new Font("2005_iannnnnTKO",Font.CENTER_BASELINE,90));
                g.drawString("SCORE   "+score,650,390);
                //g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,70));
                g.setColor(Color.black);
                g.drawString("GAME OVER",650,300);
            }else if(times <= 10) {
                if(x1>getWidth()) x1=0;
                g.drawImage(bg2,0,0,this.getWidth(),this.getHeight(),this); //bg state2
                g.drawImage(image,x1,y1,100,100,this); //car
                //===========================mushroom==========================
                g.drawImage(image2, x[0],y[0] , 50,50,this);
                g.drawImage(image3, x[1],y[1] , 50,50,this);
                g.drawImage(image4, x[2],y[2], 50,50,this);
                if(y[2]>0){
                    y[2]-=2;
                }
                g.drawImage(image5, x[3],y[3] , 50,50,this);
                g.drawImage(image6, x[4],y[4] , 50,50,this);
                g.drawImage(image7, x[5],y[5], 50,50,this);
                if(y[5]>0) {
                    y[5] += 2;
                }
                g.drawImage(image8, x[6],y[6] , 50,50,this);
                g.drawImage(image9, x[7],y[7] , 50,50,this);
                //=============================================================
                //========================================
                g.setColor(Color.black);
                g.setFont(new Font("2005_iannnnnTKO",Font.CENTER_BASELINE,45));
                g.drawString("Score : "+score, 50, 50);
                g.drawString("Time "+times,400,50);
                //========================================
            }else if(times <= 0 || score<=0){
                this.setLayout(null);
                g.drawImage(bg1,0,0,this.getWidth(),this.getHeight(),this);
                g.setColor(Color.WHITE);
                //g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,40));
                g.setFont(new Font("2005_iannnnnTKO",Font.CENTER_BASELINE,90));
                g.drawString("SCORE   "+score,650,390);
                //g.setFont(new Font("Hobo Std",Font.HANGING_BASELINE,70));
                g.setColor(Color.black);
                g.drawString("GAME OVER",650,300);
            }else{
                if(x1>getWidth()) x1=0;
                g.drawImage(bg,0,0,this.getWidth(),this.getHeight(),this); //bg state1
                g.drawImage(image,x1,y1,100,100,this); //car
                //===========================mushroom==========================
                g.drawImage(image2, x[0],y[0] , 50,50,this);
                g.drawImage(image3, x[1],y[1] , 50,50,this);
                g.drawImage(image4, x[2],y[2], 50,50,this);
                g.drawImage(image5, x[3],y[3] , 50,50,this);
                g.drawImage(image6, x[4],y[4] , 50,50,this);
                g.drawImage(image7, x[5],y[5], 50,50,this);
                g.drawImage(image8, x[6],y[6] , 50,50,this);
                g.drawImage(image9, x[7],y[7] , 50,50,this);
                //=============================================================
                //========================================
                g.setColor(Color.black);
                g.setFont(new Font("2005_iannnnnTKO",Font.CENTER_BASELINE,45));
                g.drawString("Score : "+score, 50, 50);
                g.drawString("Time "+times,400,50);
                //========================================
            }
        }
    }
}