package eu.plgc.tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import eu.plgc.tictactoe.logic.FieldState;
import eu.plgc.tictactoe.logic.GameBoard;
import eu.plgc.tictactoe.logic.GameController;
import eu.plgc.tictactoe.logic.GameMode;


public class MasterPane  {
	
	
    static final String gapList[] = {"0", "10", "15", "20"};
    final static int maxGap = 20;
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;
    JButton applyButton = new JButton("Apply gaps");
    //GridLayout experimentLayout = new GridLayout(3,3);
    
	private final GameController gameController;
    
    public MasterPane(GameController controller, Container parentPane){
		this.gameController = controller;
		
		addComponentsToPane(parentPane);
		
		this.gameController.start(GameMode.TwoPlayer);
    }
    
    public void initGaps() {
        horGapComboBox = new JComboBox(gapList);
        verGapComboBox = new JComboBox(gapList);
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
        
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		JButton button = new GameButton(i, j, FieldState.Empty);
        		button.addActionListener((event) -> {
        			GameButton src = (GameButton)event.getSource();
        			FieldState newState = gameController.syncPlayerMove(src.getX(), src.getY());
        			src.setFieldState(newState);
        		});
        		primaryPanel.add(button);		
			}			
		}

        //Add controls to set up horizontal and vertical gaps
        menuPanel.add(new Label("Horizontal gap:"));
        menuPanel.add(new Label("Vertical gap:"));
        menuPanel.add(new Label(" "));
        menuPanel.add(horGapComboBox);
        menuPanel.add(verGapComboBox);
        menuPanel.add(applyButton);
        
        //Process the Apply gaps button press
        applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                String horGap = (String)horGapComboBox.getSelectedItem();
                //Get the vertical gap value
                String verGap = (String)verGapComboBox.getSelectedItem();
                //Set up the horizontal gap value
                //experimentLayout.setHgap(Integer.parseInt(horGap));
                //Set up the vertical gap value
                //experimentLayout.setVgap(Integer.parseInt(verGap));
                //Set up the layout of the buttons
                //experimentLayout.layoutContainer(compsToExperiment);
            }
        });
        
        pane.add(menuPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(primaryPanel, BorderLayout.SOUTH);


    }
    

}