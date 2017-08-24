package eu.plgc.tictactoe.logic.bus;

public class Message {
	
	public static final String PlayerMoved = "PlayerMoved";
	
	private String messageType;
	private Object messageData;
		
	public Message(String messageType, Object messageData) {
		this.messageType = messageType;
		this.messageData = messageData;
	}
	
	public String getMessageType() {
		return messageType;
	}

	public Object getMessageData() {
		return messageData;
	}

	@Override
	public String toString() {
		return "Message [messageType=" + messageType + ", messageData=" + messageData + "]";
	}
	

}
