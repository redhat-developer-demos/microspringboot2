package com.redhat.developers.osio.microspringboot2;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class TheRESTController {


    RestTemplate restTemplate;

    @Value("${microspringboot2.orders_url}")
    String orderServiceUrl;

    @Autowired
    public TheRESTController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    public String getCustomers() {
        return "C200,C201,C203";
    }

    @RequestMapping(value = "/api/customer/orders", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "ordersFallback")
    public String getCustomerOrders(@RequestBody String customers) {
        System.out.println("Finding orders for customers " + customers);

        final URI customersOrdersUri = UriComponentsBuilder
            .fromHttpUrl(orderServiceUrl)
            .path("/api/orders")
            .build()
            .toUri();

        System.out.println("Customer Order Uri " + customersOrdersUri.toString());
        String results = restTemplate.postForObject(customersOrdersUri, customers, String.class);

        System.out.println("Results:" + results);

        return String.format("Orders for Customers %s is %s", results, customers);
    }


    public String ordersFallback(String customers) {
        System.out.println("Unable to query Orders Service, using Fallback");
        return "F997,F998,F999";
    }

}
