package com.xebia.fulfillment.v2.domain;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentTest {

    @Test
    public void testDocumentCanBeParsedFromJSON() throws Exception {
        String doc = "{\"uuid\":\"cb62a983-ffb5-471e-9547-71d6d46a6440\",\"status\":0,\"shipment\":{\"uuid\":\"3866bb81-5ef3-431a-9c2b-bfabc9ccfad9\",\"address\":\"myAddress\",\"status\":\"SHIPPED\"}}";
        Clerk clerk = new Clerk(doc);
        assertEquals("cb62a983-ffb5-471e-9547-71d6d46a6440", clerk.getUuid().toString());
        assertEquals("myAddress", clerk.getShipment().getAddress());
    }

    @Test
    public void testExtraDataRemainsUnchangedAfterParse() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("clerk.json").getFile());
        Clerk clerk = new Clerk(file);
        assertEquals("a89a65ae-6d1a-42e5-a8f1-11b6d190286e", clerk.getUuid().toString());
        String newData = clerk.getDocument();
        assertTrue(newData.indexOf("\"uuid\":\"" + clerk.getUuid() + "\",") >= 0);
        assertTrue(newData.indexOf("\"address\":\"" + clerk.getShipment().getAddress() + "\"") >= 0);
        assertTrue(newData.indexOf("\"price\":51.420808805416065") >= 0);
    }
}
