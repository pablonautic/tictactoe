package eu.plgc.tictactoe;

import javax.swing.JButton;

import eu.plgc.tictactoe.logic.FieldState;

public class GameButton extends JButton {

	private int x;
	private int y;
	private FieldState fieldState;

	public GameButton(int x, int y, FieldState fieldState) {
		super();
		this.x = x;
		this.y = y;
		setFieldState(fieldState);
	}

	public FieldState getFieldState() {
		return fieldState;
	}

	public void setFieldState(FieldState fieldState) {
		this.fieldState = fieldState;
		this.setText(stateToLabel(fieldState));
	}

	private static String stateToLabel(FieldState fieldState) {
		switch (fieldState) {
		case Empty:
			return "";
		case O:
			return "O";
		case X:
			return "X";
		default:
			throw new ArrayIndexOutOfBoundsException(fieldState.ordinal());
		}
	}

	public int getpX() {
		return x;
	}

	public int getpY() {
		return y;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7330986718128711758L;
}
