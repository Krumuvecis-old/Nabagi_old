package localClient.grafika;

import localClient.ClientThread;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grafika extends JPanel {
	public static ClientThread thread; //temporary lielums z�m��anas funkcij�m
	public JFrame ekrans;
	public Grafika grafika;

	//te nelikt main�gos!!! (var likt pie thread.dati vai thread.dati.grafikasDati)

	public void initialize(ClientThread threadTemp) {
		thread=threadTemp;
		ekrans = new JFrame(thread.dati.grafikasDati.windowTitle);
		ekrans.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		grafika = new Grafika();

		ekrans.getContentPane().add(grafika);
		ekrans.setResizable(true);
		ekrans.setSize(thread.dati.grafikasDati.ekranaPlatums, thread.dati.grafikasDati.ekranaAugstums);
		ekrans.setLocation(GrafikasDati.ekransLocation[0], GrafikasDati.ekransLocation[1]);

		ekrans.setVisible(true);
	}

	public void main (ClientThread threadTemp) {
		thread=threadTemp; //temporary update z�m��anas cikliem, jo repaint() nepie�em argumentus
		ekrans.repaint();
	}

	protected void paintComponent(Graphics g) {
		thread.dati.drawManagerList.get(thread.dati.modeCurrent).main(g, thread);
	}

}
