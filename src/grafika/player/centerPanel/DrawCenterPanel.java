package grafika.player.centerPanel;

import java.awt.Graphics;

import grafika.player.PlayerThread;
import grafika.player.centerPanel.lobby.Lobby;
import grafika.player.centerPanel.map.Map;

public class DrawCenterPanel {
	
	static PlayerThread thread;
	private Map map = new Map();
	private Lobby lobby = new Lobby();
	
	public void main(Graphics g, PlayerThread threadTemp) {
		thread = threadTemp;

		if (thread.dati.lobby) { //sadalîjums vçl nav gatavs
			lobby.main(g);
		} else {
			map.main(g, thread);
		}
	}

	
}
