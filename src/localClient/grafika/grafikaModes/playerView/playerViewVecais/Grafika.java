package localClient.grafika.grafikaModes.playerView.playerViewVecais;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Grafika extends JPanel {
	
	protected JFrame ekrans;
	private Grafika grafika;

	private static PlayerThread thread; //temporary lielums z�m��anas funkcij�m

	//te nelikt main�gos!!! (var likt pie thread.dati)
	
	protected void initialize(PlayerThread threadTemp) {

		
		if (thread.dati.fullscreen) ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);



	}
	
	void main (PlayerThread threadTemp) {
		thread=threadTemp; //temporary update z�m��anas cikliem, jo repaint() nepie�em argumentus
		
		ekrans.repaint();
	}
	
	protected void paintComponent(Graphics g) {

		
		thread.grafika2.main(g, thread); //z�m� s�nu un centra pane�us

	}

}
