package com.faraz.swiftMsgPostJson;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class PostResponse extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("jetty").host("0.0.0.0").port(8081).bindingMode(RestBindingMode.auto).enableCORS(true);
//        rest("/api").post("/test").consumes("C:/Users/Dell/Desktop/Folder Two").produces("application/json").to("direct:foo");
        ResponseData responseData = new ResponseData("hello world");
        rest("/api/test")
                .post().outType(ResponseData.class).to("direct:too");
//
//        from("direct:foo")
//                .transform().constant(responseData.getMessage());


        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "faraz");
        employeeDetails.put("lastName", "Khan");
        employeeDetails.put("website", "ai_farazkhan.com");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);

        from("direct:too")
                .transform().constant(employeeObject);
    }
}
