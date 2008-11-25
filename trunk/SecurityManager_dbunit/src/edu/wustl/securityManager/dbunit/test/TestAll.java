
package edu.wustl.securityManager.dbunit.test;

/**
 * Main class to run junit test cases.
 */

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * @author prafull_kadam
 * Test Suite for testing all Query Interface related classes.
 */
public class TestAll
{
	/**
	 * Main method called by junit target in build.xml.
	 * @param args arguments to main method.
	 */
	public static void main(String[] args)
	{
		junit.swingui.TestRunner.run(TestAll.class);
	}
	/**
	 *
	 * @return Test object.
	 */
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Test suite for Query Interface Classes");
		suite.addTestSuite(TestSecurityManager.class);
		suite.addTestSuite(TestPrivilegeCache.class);
		suite.addTestSuite(TestPrivilegeManager.class);
		suite.addTestSuite(TestPrivilegeUtility.class);
		suite.addTestSuite(TestAuthorizationDAOImpl.class);
	//	suite.addTestSuite(TestAuthorizationDAOImpl.class);
		return suite;
	}
}