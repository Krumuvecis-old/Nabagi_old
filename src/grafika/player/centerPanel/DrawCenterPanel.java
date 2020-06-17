package grafika.player.centerPanel;

import java.awt.Graphics;

import grafika.player.PlayerThread;
import grafika.player.centerPanel.lobby.Lobby;
import grafika.player.centerPanel.map.Map;

public class DrawCenterPanel {

	private Map map;
	private Lobby lobby;
	
	public void main(Graphics g, PlayerThread thread) {

		if (thread.dati.lobby) { //sadalîjums vçl nav gatavs
			lobby.main(g);
		} else {
			map.main(g, thread);
		}
	}

	public void initialize(PlayerThread threadTemp){
		map = new Map();

		lobby = new Lobby();
	}
	
}
