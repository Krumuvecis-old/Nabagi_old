package grafika.player;

import java.awt.Graphics;

class DrawCenterPanel {
	
	static PlayerThread thread;
	
	protected static void main(Graphics g, PlayerThread threadTemp) {
		thread = threadTemp;
		
		if (thread.dati.lobby) { //sadal�jums v�l nav gatavs
			drawLobby(g);
		} else {
			threadTemp.dati.drawMap.main(g, thread);
		}
	}
	
	private static void drawLobby(Graphics g) {
		
		//v�l nav gatavs
		
	}
	
}
