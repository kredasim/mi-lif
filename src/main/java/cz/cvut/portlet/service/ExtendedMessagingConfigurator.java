package cz.cvut.portlet.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.config.PluginMessagingConfigurator;

/**
 * Bug fix for {@link PluginMessagingConfigurator} which is not implementing {@link InitializingBean} but
 * expects its method {@link PluginMessagingConfigurator#afterPropertiesSet()} to get called...
 */
public class ExtendedMessagingConfigurator extends PluginMessagingConfigurator implements InitializingBean {

	@Override
	public void setDestinations(List<Destination> destinations) {
		for (Destination dest : destinations) {
			dest.open();
		}
		super.setDestinations(destinations);
	}
}
