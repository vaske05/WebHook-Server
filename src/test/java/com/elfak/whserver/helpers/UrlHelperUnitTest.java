package com.elfak.whserver.helpers;

import org.junit.Assert;
import org.junit.Test;

public class UrlHelperUnitTest {

    @Test
    public void testUrlHelperExample1() {
        String newString = UrlHelper.replaceStringOccurrence("http://api.airvisual.com/v2/cities?country=Serbia&state=Central%20Serbia&key=cf8b831a-1234-42dd-ad9f-4f6d07f63161", "%20", " ");
        Assert.assertEquals(newString, "http://api.airvisual.com/v2/cities?country=Serbia&state=Central Serbia&key=cf8b831a-1234-42dd-ad9f-4f6d07f63161");
    }

    @Test
    public void testUrlHelperExample2() {
        String newString = UrlHelper.replaceStringOccurrence("http://api.airvisual.com/v2/cities?country=Serbia&state=Autonomna%20Pokrajina%20Vojvodina&key=cf8b831a-1234-42dd-ad9f-4f6d07f63161", "%20", " ");
        Assert.assertEquals(newString, "http://api.airvisual.com/v2/cities?country=Serbia&state=Autonomna Pokrajina Vojvodina&key=cf8b831a-1234-42dd-ad9f-4f6d07f63161");
    }
}
