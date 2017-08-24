package eu.plgc.tictactoe;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import eu.plgc.tictactoe.logic.GameController;
import eu.plgc.tictactoe.logic.bus.MessageQueue;

public class Program {

	public static void main(String[] args) {

		try {
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		//UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			
		MessageQueue queue = new MessageQueue();
		queue.addListener(new ConsoleMessageLog());
			
		GameController controller = new GameController(3, 3, queue);
		frame.getContentPane().add(new MasterPane(controller));
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
			
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
	                if (e.getKeyChar() == 'q'){
	                	frame.dispose();
	                }
	                if (e.getKeyChar() == 'r'){
	                	controller.reset();
	                }
	            } 
				return false;
			}
		});

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.setSize(500, 500);

	}
	

}
