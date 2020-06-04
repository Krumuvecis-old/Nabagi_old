package grafika.player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import grafika.player.centerPanel.DrawCenterPanel;
import grafika.player.sidePanels.DrawSidePanels;

@SuppressWarnings("serial")
class Grafika extends JPanel {
	
	protected JFrame ekrans;
	private Grafika grafika;
	
	private static PlayerThread thread; //temporary lielums zîmçðanas funkcijâm
	
	
	
	//te nelikt mainîgos!!! (var likt pie thread.dati)
	
	protected void initialize(PlayerThread threadTemp) {
		thread=threadTemp;
		ekrans = new JFrame(thread.dati.windowTitle);
		ekrans.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		grafika = new Grafika();
		
		ekrans.getContentPane().add(BorderLayout.CENTER, grafika);
		ekrans.setResizable(true);
		ekrans.setSize(thread.dati.ekranaPlatums, thread.dati.ekranaAugstums);
		//ekrans.setLocationByPlatform(true);
		ekrans.setLocation(150,20);
		
		ekrans.setVisible(true);
		
		if (thread.dati.fullscreen) ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	void main (PlayerThread threadTemp) {
		thread=threadTemp; //temporary update zîmçðanas cikliem, jo repaint() nepieòem argumentus
		
		ekrans.repaint();
	}
	
	protected void paintComponent(Graphics g) {
		
		drawFons(g);
		
		DrawCenterPanel.main(g, thread);
		DrawSidePanels.main(g, thread);
		
		
	}
	
	private void drawFons(Graphics g) {
		g.setColor(Color.black); // vispârçjs default fons
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Dati.nosaukumaKrasa);
		g.drawString(thread.dati.windowTitle, Dati.nosaukumsX, Dati.nosaukumsY);
	}
	
	
	
	
}
