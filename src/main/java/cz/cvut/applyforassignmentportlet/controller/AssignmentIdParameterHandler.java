/**
 * 
 */
package cz.cvut.applyforassignmentportlet.controller;

import javax.el.ELContext;
import javax.faces.context.FacesContext;
import javax.portlet.faces.BridgePublicRenderParameterHandler;

/**
 * @author Simeon Kredatus
 *
 */
public class AssignmentIdParameterHandler implements BridgePublicRenderParameterHandler {

	 /**
	  * {@inheritDoc}
	  */
	@Override
	public void processUpdates(FacesContext context) {
//		ELContext elContext = context.getELContext();
//		ViewTaskController bean = elContext.getELResolver().getValue(arg0, arg1, arg2)
	}

}
