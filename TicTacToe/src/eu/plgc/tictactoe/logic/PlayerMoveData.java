package eu.plgc.tictactoe.logic;

public class PlayerMoveData {
	
	private int x;
	private int y;
	private FieldState newState;
	
	public PlayerMoveData(int x, int y, FieldState newState) {
		super();
		this.x = x;
		this.y = y;
		this.newState = newState;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public FieldState getNewState() {
		return newState;
	}

	@Override
	public String toString() {
		return "PlayerMoveData [x=" + x + ", y=" + y + ", newState=" + newState + "]";
	}
	

}
