package server.userInterface;

import javax.swing.*;
import java.awt.*;

abstract class GraphicsParts {

    static JLabel label1, label2;

    static class Ekrans extends JFrame {

        private static GrafikasPanelis graphicsPanel;
        private static GrafikasPanelis graphicsPanel2;

        public Ekrans(String threadTitle, int[] windowSize, int[] windowLocation){
            Color backgroundColor = new Color(80,120,70);
            String windowTitle = "Nabagi - " + threadTitle;

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLocation(windowLocation[0], windowLocation[1]);
            setSize(windowSize[0], windowSize[1]);
            setTitle(windowTitle);
            getContentPane().setBackground(backgroundColor);



            label1 = new JLabel();
            label1.setLocation(50, 50);
            label1.setPreferredSize(new Dimension(50,50));
            getContentPane().add(BorderLayout.BEFORE_FIRST_LINE, label1);

            label2 = new JLabel();

            label2.setPreferredSize(new Dimension(50,50));
            label2.setBackground(Color.red);
            label2.setForeground(Color.green);

            getContentPane().add(label2);

            label2.setLocation(150, 50);



            graphicsPanel = new GrafikasPanelis(new int[]{0,0}, new int[]{150,150}, true, true);
            getContentPane().add(BorderLayout.CENTER, graphicsPanel);

            graphicsPanel2 = new GrafikasPanelis(new int[]{100,100}, new int[]{150,150}, true, true);
            //getContentPane().add(BorderLayout.CENTER, graphicsPanel2);

            setVisible(true);
        }

        public void refresh(){

            label1.setText("label1 " + ServerUIThread.skaitlis);

            label2.setText("label2 " + (ServerUIThread.skaitlis * 2));
            label2.setLocation(200,100);

            repaint();

        }
    }

    public static class GrafikasPanelis extends JPanel {

        public boolean demo;

        public GrafikasPanelis(int[] location, int[] size, boolean transparent, boolean _demo){
            demo = _demo;
            setLocation(location[0],location[1]);
            setSize(size[0], size[1]);
            setBackgroundColor(transparent);
            //setVisible(true);
        }

        private void setBackgroundColor(boolean transparent){
            int[] defCol = {0,0,0}; //black
            double defTransparency = 0; //0 = transparent, 255 = opaque
            if(transparent) setBackground(new Color(defCol[0], defCol[1], defCol[2], (float)defTransparency));
            else setBackground(new Color(defCol[0], defCol[1], defCol[2], 255));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            drawBorder(g, Color.darkGray);
            if(demo) drawDemo(g);

        }

        private void drawBorder(Graphics g, Color borderColor){
            g.setColor(borderColor);
            g.drawRect(0,0, getWidth(), getHeight());
        }

        private void drawDemo(Graphics g){
            g.setColor(Color.pink);
            g.drawOval(ServerUIThread.skaitlis,50,50,50);

            int maksimums = 100;
            if(ServerUIThread.skaitlis >= maksimums) ServerUIThread.skaitlis -= maksimums;
        }
    }

}
