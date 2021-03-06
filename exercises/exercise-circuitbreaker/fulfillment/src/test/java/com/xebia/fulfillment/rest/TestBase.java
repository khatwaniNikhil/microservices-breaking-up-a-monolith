package com.xebia.fulfillment.rest;


import com.xebia.fulfillment.FulfillmentApplication;
import com.xebia.fulfillment.repositories.*;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringApplicationConfiguration(classes = FulfillmentApplication.class)
@WebAppConfiguration
public class TestBase {

    protected MediaType jsonContentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    protected MediaType textType = new MediaType(MediaType.TEXT_PLAIN.getType());

    protected MockMvc mockMvc;

    protected HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected OrderRepository orderRepository;
    @Autowired
    protected LineItemRepository lineItemRepository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
      /*
    protected Account createAndSaveAccount() {
        UUID accountUuid = UUID.randomUUID();
        String id = accountUuid.toString().substring(1, 5);
        Account account = new Account("address " + id, "+31355381921", "info@xebia.com");
        return accountRepository.save(account);
    }

    protected Account createAccount() {
        UUID accountUuid = UUID.randomUUID();
        String id = accountUuid.toString().substring(1, 5);
        return new Account("address " + id, "+31355381921", "info@xebia.com");
    }

    protected LineItem createAndSaveLineItem() {
        UUID lineItem1Uuid = UUID.randomUUID();
        LineItem item1 = new LineItem(lineItem1Uuid, 1, 10, new Product(UUID.randomUUID(), "product1", "supplier1", 10.0));
        return lineItemRepository.save(item1);
    }
       */
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

   protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}