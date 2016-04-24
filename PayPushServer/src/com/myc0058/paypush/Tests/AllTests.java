package com.myc0058.paypush.Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All Tests.
 *
 * @author YoungChul Mo
 * @since   2016-04-24
 */

@RunWith(Suite.class)
@SuiteClasses({ SendAndroPushsTest.class, SendAndroPushTest.class,
		SendAndroReceipt.class, SendIOSReceipt.class, SendIOSPushTest.class, 
		SendIOSPushsTest.class, SendIOSPushsProductionModeTest.class})
public class AllTests {

}
