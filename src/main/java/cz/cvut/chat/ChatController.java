package cz.cvut.chat;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

import cz.cvut.chat.listener.CustomizedMessageListener;
import cz.cvut.portlet.common.CommonConstants;
import cz.cvut.portlet.common.CommonUtils;

@ManagedBean(name = "chatController")
@ViewScoped
public class ChatController {

	@ManagedProperty("#{chatListener}")
	private CustomizedMessageListener customizedMessageListener;

	public void sendMessage(String msg) {
		Message myMessage = new Message();
		myMessage.put(CommonConstants.MESSAGE_KEY, "[" + new Date().toString() + "]" + CommonUtils.getCurrentUserScreenName() + ":" + msg);
		MessageBusUtil.sendMessage(CommonConstants.CHAT_DESTINATION, myMessage);
	}
	
	public List<String> getUnreadMessages() {
		return customizedMessageListener.getAllMessages();
	}

	public CustomizedMessageListener getCustomizedMessageListener() {
		return customizedMessageListener;
	}

	public void setCustomizedMessageListener(CustomizedMessageListener customizedMessageListener) {
		this.customizedMessageListener = customizedMessageListener;
	}
}
