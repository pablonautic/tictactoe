package eu.plgc.tictactoe.logic;

import eu.plgc.tictatoe.utils.Tuple;

/**
 * A very sophisticated AI player
 * 
 * @author pawel_000
 *
 */
public class AiPlayer extends AbstractPlayer {
	
	private GameBoard gameBoard;
	
	public AiPlayer(GameBoard gameBoard, FieldState myState, String name) {
		super(myState, name);
		this.gameBoard = gameBoard;
	}
	
	public Tuple<Integer, Integer> getNextMove(){
		for (int i = 0; i < gameBoard.getM(); i++) {
			for (int j = 0; j < gameBoard.getN(); j++) {
				if (gameBoard.getField(i, j) == FieldState.Empty){
					return new Tuple<>(i, j);
				}
			}
		}
		throw new RuntimeException("unreachable code");
	}

	@Override
	public boolean isAsync() {
		return false;
	}

}
