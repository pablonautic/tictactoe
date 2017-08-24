package eu.plgc.tictactoe.logic;

import eu.plgc.tictatoe.utils.Tuple;

public class HumanPlayer extends AbstractPlayer {

	public HumanPlayer(FieldState myState, String name) {
		super(myState, name);
	}

	@Override
	public boolean isAsync() {
		return true;
	}

	@Override
	public Tuple<Integer, Integer> getNextMove() {
		throw new RuntimeException("not supported");
	}


}
