package grafika.player.centerPanel;

import java.awt.Graphics;

import grafika.player.PlayerThread;

public class DrawCenterPanel {
	
	static PlayerThread thread;
	protected DrawMap drawMap = new DrawMap();
	
	public static void main(Graphics g, PlayerThread threadTemp) {
		thread = threadTemp;
		
		if (thread.dati.lobby) { //sadalîjums vçl nav gatavs
			drawLobby(g);
		} else {
			thread.dati.drawCenterPanel.drawMap.main(g, thread);
		}
	}
	
	private static void drawLobby(Graphics g) {
		
		//vçl nav gatavs
		
	}
	
}
