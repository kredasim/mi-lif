/**
 * 
 */
package cz.cvut.assignmentmanager.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import cz.cvut.applyforassignmentportlet.service.PersistentDtoService;
import cz.cvut.fit.bpm.api.dto.TaskDto;
import cz.cvut.portlet.common.CommonConstants;

/**
 * @author Simeon Kredatus
 * 
 */
@ManagedBean(name = "assignmentManagerViewController")
@ViewScoped
public class AssignmentManagerViewController {

	@ManagedProperty("#{dummyTaskDtoService}")
	private PersistentDtoService<TaskDto> persistentDtoService;

	public void view(long id) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Object responseObject = facesContext.getExternalContext().getResponse();
		if (responseObject != null && responseObject instanceof ActionResponse) {
			ActionResponse actionResponse = (ActionResponse) responseObject;
			actionResponse.setRenderParameter(
					CommonConstants.ASSIGNMENT_ID_PARAM, Long.toString(id));
		}
	}

	public int getDefaultAssignmentsCount() throws SystemException, PortalException {
		// load from portlet prefferences
		ExternalContext extcontext = FacesContext.getCurrentInstance().getExternalContext();
		RenderRequest renderRequest = (RenderRequest) extcontext.getRequest();
		HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));

		PortletPreferences pref = renderRequest.getPreferences();
		String portletResource = ParamUtil.getString(request, "portletResource");
		if (Validator.isNotNull(portletResource)) {
			pref = PortletPreferencesFactoryUtil.getPortletSetup(
					request, portletResource);
		}
		String val = pref.getValue("defaultAssignmentsCount", "1");
		Integer ret = Integer.parseInt(val);
		return ret.intValue();
	}

	public List<TaskDto> getTasks() {
		return persistentDtoService.getAll();
	}

	public PersistentDtoService<TaskDto> getPersistentDtoService() {
		return persistentDtoService;
	}

	public void setPersistentDtoService(
			PersistentDtoService<TaskDto> persistentDtoService) {
		this.persistentDtoService = persistentDtoService;
	}
}
