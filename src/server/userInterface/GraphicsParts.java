package server.userInterface;

import javax.swing.*;
import java.awt.*;

abstract class GraphicsParts {

    static JLabel label1, label2;

    static class Ekrans extends JFrame {

        private static GrafikasPanelis graphicsPanel;

        public Ekrans(String threadTitle, int[] windowSize, int[] windowLocation){
            Color backgroundColor = new Color(80,120,70);
            String windowTitle = "Nabagi - " + threadTitle;

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLocation(windowLocation[0], windowLocation[1]);
            setSize(windowSize[0], windowSize[1]);
            setTitle(windowTitle);
            getContentPane().setBackground(backgroundColor);

            label1 = new JLabel();
            label2 = new JLabel();

            label1.setLocation(50, 50);
            label1.setBackground(Color.green);
            getContentPane().add(BorderLayout.BEFORE_FIRST_LINE, label1);


            graphicsPanel = new GrafikasPanelis(new int[]{150,150}, true);
            getContentPane().add(graphicsPanel);

            setVisible(true);
        }

        public void refresh(){

            label1.setText("label1 " + ServerUIThread.skaitlis);

            graphicsPanel.refresh(true);


            repaint(); //repaint nepieòem argumentus

        }
    }

    public static class GrafikasPanelis extends JPanel {

        public boolean demo;

        public GrafikasPanelis(int[] size, boolean _demo){
            demo = _demo;

            setSize(size[0], size[1]);
            setBackgroundColor(true);
            //setVisible(true);
        }

        private void setBackgroundColor(boolean transparent){
            int[] defCol = {0,0,0}; //black
            double defTransparency = 0; //0 = transparent, 255 = opaque
            if(transparent) setBackground(new Color(defCol[0], defCol[1], defCol[2], (float)defTransparency));
            else setBackground(new Color(defCol[0], defCol[1], defCol[2], 255));
        }

        public void refresh(boolean demo){
            //repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(demo) drawDemo(g);
        }

        private void drawDemo(Graphics g){
            g.setColor(Color.pink);
            g.drawOval(ServerUIThread.skaitlis,50,50,50);

            int maksimums = 100;
            if(ServerUIThread.skaitlis >= maksimums) ServerUIThread.skaitlis -= maksimums;
        }
    }

}
