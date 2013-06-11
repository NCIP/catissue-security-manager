/*L
 *  Copyright Washington University in St. Louis
 *  Copyright SemanticBits
 *  Copyright Persistent Systems
 *  Copyright Krishagni
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/catissue-security-manager/LICENSE.txt for details.
 */

package gov.nih.nci.system.security.authentication.cagrid.executor.jboss;

import java.util.Hashtable;

import javax.naming.NamingException;

import org.springframework.jndi.JndiTemplate;

public class JNDIBinder
{

	public JNDIBinder(String jndiName, JndiTemplate template) throws NamingException
	{
		template.bind(jndiName, new Hashtable());
	}

}