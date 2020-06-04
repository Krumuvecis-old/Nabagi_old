package grafika.player;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

class Input {
	
	protected int xPele=0, yPele=0;
	protected boolean peleClick=false;
	protected int[] pogas=new int[] {};
	
	protected void initialize(PlayerThread thread) {
		
		thread.grafika.ekrans.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("PlayerWindow open.");
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("PlayerWindow closed.");
				thread.running=false;
			}
			
			@Override public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {
				thread.minimized=true;
				pogas=new int[] {};
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				thread.minimized=false;
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				thread.windowActive=true;
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				thread.windowActive=false;
				pogas=new int[] {};
			}
			
		});
		
		thread.grafika.ekrans.addKeyListener(new KeyListener() {
			
			@Override public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int poga=e.getKeyCode();
				boolean jauns=true;
				
				for (int i=0; i<pogas.length; i++) { //pârbauda vai piespiestâ poga ir jauna
					if (poga==pogas[i]) {
						jauns=false;
						break;
					}
				}
				
				if (jauns) {
					int[] pogasTemp = new int[pogas.length + 1];
					
					for (int i=0; i<pogas.length; i++) {
						pogasTemp[i]=pogas[i];
					}
					pogasTemp[pogas.length]=poga;
					
					pogas = pogasTemp;
				}
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (pogas.length>1) {
					int poga=e.getKeyCode();
					int[] pogasTemp = new int[pogas.length - 1];
					
					for (int i=0, j=0; i<pogas.length; i++) {
						if (poga!=pogas[i]) {
							pogasTemp[j]=pogas[i];
							j++;
						}
					}
					
					pogas = pogasTemp;
				} else pogas = new int[] {};
				
			}
			
		});
		
		thread.grafika.ekrans.getContentPane().addMouseListener(new MouseListener() {
			
			@Override public void mouseClicked(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				peleClick=true;
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				peleClick=false;
				
			}
			
			@Override public void mouseEntered(MouseEvent e) {}
			
			@Override public void mouseExited(MouseEvent e) {}
			
		});
		
		thread.grafika.ekrans.getContentPane().addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				xPele=e.getX();
				yPele=e.getY();
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				xPele=e.getX();
				yPele=e.getY();
			}
			
		});
		
		thread.grafika.ekrans.addMouseWheelListener(new MouseWheelListener() {
			@Override public void mouseWheelMoved(MouseWheelEvent e) {}
		});
		
		
	}
	
	
}
