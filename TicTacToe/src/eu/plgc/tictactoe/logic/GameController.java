package eu.plgc.tictactoe.logic;

import eu.plgc.tictactoe.logic.bus.Message;
import eu.plgc.tictactoe.logic.bus.MessageQueue;
import eu.plgc.tictatoe.utils.Tuple;

public class GameController {

	@SuppressWarnings("unused")
	private GameState gameState = GameState.NotStarted;
	
	private GameMode gameMode;

	private GameBoard gameBoard;
	private MessageQueue messageQueue;

	private AbstractPlayer player1;
	private AbstractPlayer player2;
	
	private AbstractPlayer activePlayer;

	public GameController(int n, int m, MessageQueue messageLog) {
		this.messageQueue = messageLog;
		this.gameBoard = new GameBoard(m, n);
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public void start(GameMode gameMode) {
		this.gameMode = gameMode;
		messageQueue.log("Staring game in mode: " + gameMode);

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
		
		messageQueue.postMessage(Message.GameReset, null);
		
		setState(GameState.Started);
	}
	
	public void reset() {
		gameBoard.reset();
		start(this.gameMode);
	}
	
	private void togglePlayer(){
		if (activePlayer == player1){
			activePlayer = player2;
		}
		else{
			activePlayer = player1;
		}
		
		messageQueue.log("Now moving: " + activePlayer.getName());
		
		if (activePlayer.isAsync()){
			return;
		}
		else{
			Tuple<Integer, Integer> move = activePlayer.getNextMove();
			processMove(move.x, move.y);
			togglePlayer();
		}
	}
	
	private void processMove(int x, int y){
		messageQueue.log("Received move: " + x + " " + y);
		FieldState state = activePlayer.getMyState();
		//TODO validate
		gameBoard.setField(x, y, state);
		
		messageQueue.postMessage(Message.PlayerMoved, new PlayerMoveData(x, y, state));
		
		boolean won = gameBoard.checkIfWon(x, y, state);
		if (won){
			messageQueue.postMessage(Message.GameEnd, activePlayer.getName() + " has won !");
			setState(GameState.NotStarted);
		}
	}

	public void syncPlayerMove(int x, int y) {
		processMove(x, y);
		togglePlayer();
	}

	private void setState(GameState gameState) {
		this.gameState = gameState;
		messageQueue.log("State changed: " + gameState);
	}
	
	public MessageQueue getMessageQueue() {
		return messageQueue;
	}


}
