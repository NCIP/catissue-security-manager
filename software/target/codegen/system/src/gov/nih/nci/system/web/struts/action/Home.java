/*L
 *  Copyright Washington University in St. Louis
 *  Copyright SemanticBits
 *  Copyright Persistent Systems
 *  Copyright Krishagni
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/catissue-security-manager/LICENSE.txt for details.
 */

package gov.nih.nci.system.web.struts.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class Home extends ActionSupport {

	private static final long serialVersionUID = 1234567890L;

	private static Logger log = Logger.getLogger(Home.class
			.getName());
	
	public String execute() throws Exception {

		log.debug("Home.action called so that localized messages are available on Home.jsp");
		
		return SUCCESS;
	}
}