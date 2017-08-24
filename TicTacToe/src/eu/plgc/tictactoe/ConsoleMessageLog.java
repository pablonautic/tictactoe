package eu.plgc.tictactoe;

import eu.plgc.tictactoe.logic.IMessageLog;

public class ConsoleMessageLog implements IMessageLog {

	@Override
	public void log(String message) {
		System.out.println(message);		
	}

}
