package eu.plgc.tictactoe.logic;

import eu.plgc.tictatoe.utils.Tuple;

public abstract class AbstractPlayer {
	
	private FieldState myState;
	private String name;
		
	public AbstractPlayer(FieldState myState, String name) {
		this.myState = myState;
		this.name = name;
	}

	public abstract boolean isAsync();
	
	public abstract Tuple<Integer, Integer> getNextMove();
	
	public FieldState getMyState() {
		return myState;
	}

	public String getName() {
		return name;
	}

	
}
