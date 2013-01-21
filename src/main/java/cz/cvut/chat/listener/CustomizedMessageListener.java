package cz.cvut.chat.listener;

import java.util.List;


public interface CustomizedMessageListener {

	/**
	 * 
	 * Returns all messages available which have been sent so far.
	 * @return
	 */
	public List<String> getAllMessages();
}
