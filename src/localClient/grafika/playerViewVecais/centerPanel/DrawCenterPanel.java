package localClient.grafika.playerViewVecais.centerPanel;

import java.awt.Graphics;

import localClient.grafika.playerViewVecais.PlayerThread;
import localClient.grafika.playerViewVecais.centerPanel.lobby.Lobby;
import localClient.grafika.playerViewVecais.centerPanel.map.Map;

public class DrawCenterPanel {

	private Map map;
	private Lobby lobby;
	
	public void main(Graphics g, PlayerThread thread) {

		if (thread.dati.lobby) { //sadalîjums vçl nav gatavs
			lobby.main(g);
		} else {
//			map.main(g, thread);
		}
	}

	public void initialize(PlayerThread threadTemp){
		map = new Map();

		lobby = new Lobby();
	}
	
}
