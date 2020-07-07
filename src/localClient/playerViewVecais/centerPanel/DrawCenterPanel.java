package localClient.playerViewVecais.centerPanel;

import java.awt.Graphics;

import localClient.playerViewVecais.PlayerThread;
import localClient.playerViewVecais.centerPanel.lobby.Lobby;
import localClient.playerViewVecais.centerPanel.map.Map;

public class DrawCenterPanel {

	private Map map;
	private Lobby lobby;
	
	public void main(Graphics g, PlayerThread thread) {

		if (thread.dati.lobby) { //sadal�jums v�l nav gatavs
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
