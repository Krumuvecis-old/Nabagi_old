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

	private static PlayerThread thread; //temporary lielums zîmçðanas funkcijâm

	//te nelikt mainîgos!!! (var likt pie thread.dati)
	
	protected void initialize(PlayerThread threadTemp) {

		
		if (thread.dati.fullscreen) ekrans.setExtendedState(JFrame.MAXIMIZED_BOTH);



	}
	
	void main (PlayerThread threadTemp) {
		thread=threadTemp; //temporary update zîmçðanas cikliem, jo repaint() nepieòem argumentus
		
		ekrans.repaint();
	}
	
	protected void paintComponent(Graphics g) {

		
		thread.grafika2.main(g, thread); //zîmç sânu un centra paneïus

	}

}
