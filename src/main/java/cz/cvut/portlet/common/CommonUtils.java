package cz.cvut.portlet.common;

import javax.faces.context.FacesContext;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

public class CommonUtils {
    public static String getCurrentUserScreenName() {
        String result = null;
        User user = getThemeDisplay().getUser();
        if (user != null) {
            result = user.getScreenName();
        }
        return result;
    }

    public static ThemeDisplay getThemeDisplay() {
        return (ThemeDisplay) FacesContext
            .getCurrentInstance()
            .getExternalContext()
            .getRequestMap()
            .get(WebKeys.THEME_DISPLAY);
    }
    
    public static User getCurrentUser() {
    	return getThemeDisplay().getUser();
    }
}
