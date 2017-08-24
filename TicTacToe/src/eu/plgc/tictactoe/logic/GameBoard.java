package eu.plgc.tictactoe.logic;

public class GameBoard {

	private int m; //columns
	private int n; //rows
	
	private FieldState[][] fields;

	public GameBoard(int m, int n) {
		super();
		this.m = m;
		this.n = n;
		fields = new FieldState[m][n];
		reset();
	}
	
	public void reset(){
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				fields[i][j] = FieldState.Empty;
			}
		}
	}
	
	public boolean checkIfWon(int x, int y, FieldState fieldState){
		
		//check row
		for (int i = 0; i < m; i++) {
			if (fields[x][i] != fieldState){
				return false;
			}
		}
		
		//check column
		for (int i = 0; i < n; i++) {
			if (fields[i][y] != fieldState){
				return false;
			}
		}
		
		//check first diag
		//TODO generalize
		for (int i = 0; i < n; i++) {
			if (fields[i][i] != fieldState){
				return false;
			}
		}
		
		//check second diag
		//TODO generalize
		for (int i = 0; i < n; i++) {
			if (fields[i][n-i+1] != fieldState){
				return false;
			}
		}
		
		return true;
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}
	
	public FieldState getField(int x, int y){
		return fields[x][y];
	}
	
	public void setField(int x, int y, FieldState fieldState){
		fields[x][y] = fieldState;
	}

	public FieldState[][] getFields() {
		return fields;
	}
	
}
