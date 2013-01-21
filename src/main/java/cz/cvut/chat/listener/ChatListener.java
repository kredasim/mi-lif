package cz.cvut.chat.listener;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import cz.cvut.portlet.common.CommonConstants;

public class ChatListener implements CustomizedMessageListener, MessageListener {

	private List<Message> messages = new ArrayList<Message>();
	
	@PostConstruct
	public void init() {
		System.out.println("LISTENER JE!");
	}
	@Override
	public void receive(Message message) throws MessageListenerException {
		messages.add(message);
	}

	@Override
	public List<String> getAllMessages() {
		List<String> ret = new ArrayList<String>();
		for (Message m : messages) {
			ret.add(m.getString(CommonConstants.MESSAGE_KEY));
		}
		return ret;
	}

}
