package eu.plgc.tictactoe;

import eu.plgc.tictactoe.logic.bus.IMessageListener;
import eu.plgc.tictactoe.logic.bus.Message;

public class ConsoleMessageLog implements IMessageListener {


	@Override
	public void onMessageReceived(Message message) {
		System.out.println(message);
		
	}

}
