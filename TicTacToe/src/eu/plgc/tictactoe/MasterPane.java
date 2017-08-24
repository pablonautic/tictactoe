package eu.plgc.tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import eu.plgc.tictactoe.logic.FieldState;
import eu.plgc.tictactoe.logic.GameBoard;
import eu.plgc.tictactoe.logic.GameController;
import eu.plgc.tictactoe.logic.GameMode;
import eu.plgc.tictactoe.logic.PlayerMoveData;
import eu.plgc.tictactoe.logic.bus.IMessageListener;
import eu.plgc.tictactoe.logic.bus.Message;


public class MasterPane implements IMessageListener {
	
	
    static final String gapList[] = {"0", "10", "15", "20"};
    final static int maxGap = 20;

    JButton applyButton = new JButton("Apply gaps");
    //GridLayout experimentLayout = new GridLayout(3,3);
    
	private final GameController gameController;
	
    private GameButton[][] buttons;
	
    public MasterPane(GameController controller, Container parentPane){
		this.gameController = controller;
		this.gameController.getMessageQueue().addListener(this);
		
		addComponentsToPane(parentPane);
		
		this.gameController.start(GameMode.PlayerVsAi);
    }
    
	@Override
	public void onMessageReceived(Message message) {
		//TODO move check logic to bus
		if (message.getMessageType() == Message.PlayerMoved){
			PlayerMoveData data = (PlayerMoveData) message.getMessageData();
			buttons[data.getX()][data.getY()].setFieldState(data.getNewState());
		}
		
	}
    
    public void initGaps() {

    }
    
    public void addComponentsToPane(final Container pane) {
        initGaps();
      
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2,3));
                
        pane.setLayout(new FlowLayout());
             
        GameBoard board = gameController.getGameBoard();
        int m = board.getM();
        int n = board.getN();
        
        final JPanel primaryPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(m,n);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        primaryPanel.setLayout(gridLayout);
        
        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        primaryPanel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));
        
        buttons = new GameButton[m][n];
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		JButton button = buttons[i][j] = new GameButton(i, j, FieldState.Empty);
        		button.addActionListener((event) -> {
        			GameButton src = (GameButton)event.getSource();
        			gameController.syncPlayerMove(src.getX(), src.getY());
        		});
        		primaryPanel.add(button);		
			}			
		}

        //Add controls to set up horizontal and vertical gaps
        menuPanel.add(new Label("Horizontal gap:"));
        menuPanel.add(new Label("Vertical gap:"));
        menuPanel.add(new Label(" "));

        menuPanel.add(applyButton);
        
        
        pane.add(menuPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(primaryPanel, BorderLayout.SOUTH);


    }


    

}