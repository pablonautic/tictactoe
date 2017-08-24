package eu.plgc.tictactoe;

import java.awt.*;
import javax.swing.*;

import eu.plgc.tictactoe.logic.FieldState;
import eu.plgc.tictactoe.logic.GameBoard;
import eu.plgc.tictactoe.logic.GameController;
import eu.plgc.tictactoe.logic.GameMode;
import eu.plgc.tictactoe.logic.PlayerMoveData;
import eu.plgc.tictactoe.logic.bus.IMessageListener;
import eu.plgc.tictactoe.logic.bus.Message;


public class MasterPane extends JSplitPane implements IMessageListener {
	
	private final GameController gameController;
	
    private GameButton[][] buttons;
	
    public MasterPane(GameController controller){
    	super(JSplitPane.VERTICAL_SPLIT);
    	
		this.gameController = controller;
		this.gameController.getMessageQueue().addListener(this);
		
        JPanel primaryPanel = createGamePanel();    
        JPanel menuPanel = createMenu();
        
        setLeftComponent(menuPanel);
        setRightComponent(primaryPanel);

		this.gameController.start(GameMode.TwoPlayer);
    }
    
	@Override
	public void onMessageReceived(Message message) {

		if (message.getMessageType() == Message.PlayerMoved){
			PlayerMoveData data = (PlayerMoveData) message.getMessageData();
			buttons[data.getX()][data.getY()].setFieldState(data.getNewState());
		}
		
		if (message.getMessageType() == Message.GameReset){
			
			GameBoard board = gameController.getGameBoard();
	        int m = board.getM();
	        int n = board.getN();
			
	        for (int i = 0; i < m; i++) {
	        	for (int j = 0; j < n; j++) {
	        		buttons[i][j].setFieldState(FieldState.Empty);
				}			
			}
		}
		
		if (message.getMessageType() == Message.GameEnd){
			JOptionPane.showMessageDialog(null, "Game ended: " + message.getMessageData());
			this.gameController.reset();
		}
	}
    
    
	private JPanel createGamePanel() {
		GameBoard board = gameController.getGameBoard();
        int m = board.getM();
        int n = board.getN();
        
        JPanel primaryPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(m,n);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        
        primaryPanel.setLayout(gridLayout);
        
        buttons = new GameButton[m][n];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		JButton button = buttons[i][j] = new GameButton(i, j, FieldState.Empty);
        		button.addActionListener((event) -> {
        			GameButton src = (GameButton)event.getSource();
        			gameController.syncPlayerMove(src.getpX(), src.getpY());
        		});
        		primaryPanel.add(button);		
			}			
		}
		return primaryPanel;
	}

	private JPanel createMenu() {
		JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0,3));
                
        JButton b1 = new JButton("New PvP");
        b1.addActionListener((e) -> {
        	this.gameController.start(GameMode.TwoPlayer);
        });
        menuPanel.add(b1);
        
        JButton b2 = new JButton("New PvAi");
        b2.addActionListener((e) -> {
        	this.gameController.start(GameMode.PlayerVsAi);
        });
        menuPanel.add(b2);
        
        JButton b3 = new JButton("Reset");
        b3.addActionListener((e) -> {
        	this.gameController.reset();
        });
        menuPanel.add(b3);
        
		return menuPanel;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}