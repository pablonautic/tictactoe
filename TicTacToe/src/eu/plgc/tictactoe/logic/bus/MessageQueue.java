package eu.plgc.tictactoe.logic.bus;

import java.util.LinkedList;
import java.util.List;

public class MessageQueue {
	
	private List<IMessageListener> listeners = new LinkedList<IMessageListener>();
	
	public void addListener(IMessageListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(IMessageListener listener){
		listeners.remove(listener);
	}
	
	public void postMessage(String messageType, Object data){
		Message m = new Message(messageType, data);
		for (IMessageListener iMessageListener : listeners) {
			iMessageListener.onMessageReceived(m);
		}
	}
	
	public void log(String message){
		postMessage(Message.Info, message);
	}

}
