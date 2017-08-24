package eu.plgc.tictactoe.logic;

import eu.plgc.tictatoe.utils.Tuple;

public class GameController {

	private GameState gameState = GameState.NotStarted;
	private GameMode gameMode;

	private GameBoard gameBoard;
	private IMessageLog messageLog;

	private AbstractPlayer player1;
	private AbstractPlayer player2;
	
	private AbstractPlayer activePlayer;

	public GameController(int n, int m, IMessageLog messageLog) {
		this.messageLog = messageLog;
		this.gameBoard = new GameBoard(m, n);
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public void start(GameMode gameMode) {
		this.gameMode = gameMode;
		messageLog.log("Staring game in mode: " + gameMode);

		player1 = new HumanPlayer(FieldState.O, "Human Player 1");

		switch (this.gameMode) {
		case PlayerVsAi:
			player2 = new AiPlayer(gameBoard, FieldState.X, "The mighty AI");
			break;
		case TwoPlayer:
			player2 = new HumanPlayer(FieldState.X, "Human Player 2");
			break;
		default:
			throw new RuntimeException("out of range: " + this.gameMode);
		}

		activePlayer = player1;
		
		setState(GameState.Started);
	}
	
	private void togglePlayer(){
		if (activePlayer == player1){
			activePlayer = player2;
		}
		else{
			activePlayer = player1;
		}
		
		messageLog.log("Now moving: " + activePlayer.getName());
		
		if (activePlayer.isAsync()){
			return;
		}
		else{
			Tuple<Integer, Integer> move = activePlayer.getNextMove();
			processMove(move.x, move.y);
			togglePlayer();
		}
	}
	
	private FieldState processMove(int x, int y){
		messageLog.log("Received move: " + x + " " + y);
		FieldState state = activePlayer.getMyState();
		//TODO validate
		gameBoard.setField(x, y, state);
		boolean won = gameBoard.checkIfWon(x, y, state);
		if (won){
			messageLog.log(activePlayer.getName() + " has won !");
			setState(GameState.NotStarted);
		}
		return state;
	}

	public FieldState syncPlayerMove(int x, int y) {
		FieldState state = processMove(x, y);
		togglePlayer();
		return state;
	}

	private void setState(GameState gameState) {
		this.gameState = gameState;
		messageLog.log("State changed: " + gameState);
	}
}
