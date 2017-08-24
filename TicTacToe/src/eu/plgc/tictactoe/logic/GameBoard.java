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
		
		if (checkRow(x, fieldState)){
			return true;
		}
		
		if (checkColumn(y, fieldState)){
			return true;
		}
			
		if (checkDiag1(fieldState)){
			return true;
		}
		
		if (checkDiag2(fieldState)){
			return true;
		}
				
		return false;
	}
	
	private boolean checkDiag1(FieldState fieldState){
		for (int i = 0; i < n; i++) {
			if (fields[i][i] != fieldState){
				return false;
			}
		}
		return true;
	}
	
	private boolean checkDiag2(FieldState fieldState){
		for (int i = 0; i < m; i++) {
			if (fields[i][m-i-1] != fieldState){
				return false;
			}
		}
		return true;
	}

	private boolean checkColumn(int y, FieldState fieldState) {
		for (int i = 0; i < n; i++) {
			if (fields[i][y] != fieldState){
				return false;
			}
		}
		return true;
	}

	private boolean checkRow(int x, FieldState fieldState) {
		for (int i = 0; i < m; i++) {
			if (fields[x][i] != fieldState){
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
