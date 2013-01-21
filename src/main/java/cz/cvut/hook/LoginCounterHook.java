package cz.cvut.hook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import cz.cvut.portlet.common.CommonConstants;

/**
 * Counts each time the user logs in and records this value.
 * 
 * @author Simeon Kredatus
 * 
 */
public class LoginCounterHook extends Action {
	private static Log log = LogFactoryUtil.getLog(LoginCounterHook.class);

	public static int COUNT = 0;

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
			throws ActionException {
		request.setAttribute(CommonConstants.COUNT_CONST, Integer.toString(COUNT++));
		System.out.println("Setting count to " + COUNT);
		log.debug("Increasing logging count");
	}

}
