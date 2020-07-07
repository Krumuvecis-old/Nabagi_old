package localClient.playerViewVecais;

import localClient.playerViewVecais.centerPanel.DrawCenterPanel;
import localClient.playerViewVecais.sidePanels.DrawSidePanels;

import java.awt.*;

public class GrafikaSupplement {

    private DrawCenterPanel centerPanel = new DrawCenterPanel();
    private DrawSidePanels sidePanels  = new DrawSidePanels();

    public void main(Graphics g, PlayerThread thread){
        centerPanel.main(g, thread);
        sidePanels.main(g, thread);
    }

    public void initialize(PlayerThread thread){
        centerPanel.initialize(thread);

    }

}
