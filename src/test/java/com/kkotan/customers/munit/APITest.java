package com.kkotan.customers.munit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.munit.common.mocking.MessageProcessorMocker;
import org.mule.munit.runner.functional.FunctionalMunitSuite;

public class APITest extends FunctionalMunitSuite {

	@Override
	protected String getConfigResources() {
		return "customersApi.xml";
	}

	@Override
	protected boolean haveToMockMuleConnectors() {
		return false;
	}

	@Override
	protected boolean haveToDisableInboundEndpoints() {
		return false;
	}

	@Override
	protected void muleContextStarted(MuleContext muleContext) {
		System.out.println("Mule Context Started at" + new Date(muleContext.getStartDate()));
	}

	@Test
	public void postReturnsSavedCustomer() throws Exception {
		String stringPayload = "{ \"id\" : 9, \"firstname\" : \"Alice\", \"lastname\" : \"Smith\" , \"address\" : \"Myers Grove 5, Auckland 1294\"}";
		MuleEvent resultMuleEvent = runFlow("post:/customers:application/json:customersApi-config",
				testEvent(stringPayload));
		String payloadAsAnObjectString = "{id=9, firstname=Alice, lastname=Smith, address=Myers Grove 5, Auckland 1294}";
		Assert.assertEquals(payloadAsAnObjectString, resultMuleEvent.getMessage().getPayload().toString());
	}

	@Test
	public void getReturnsDataInFormattedString() throws Exception {
		String myMockPayload = "{id=9, firstname=Alice, lastname=Smith, address=Myers Grove 5, Auckland 1294}";
		Map<String, String> map = new HashMap<>();
		map.put("9", myMockPayload);
		MuleMessage messageToBeReturned = muleMessageWithPayload(map);
		MessageProcessorMocker mocker = whenMessageProcessor("retrieve").ofNamespace("objectstore");
		mocker.thenReturn(messageToBeReturned);
		MuleEvent resultMuleEvent = runFlow("get:/customers/{customerid}:customersApi-config", testEvent(""));
		Assert.assertEquals(map, resultMuleEvent.getMessage().getPayload().toString());
	}
}
