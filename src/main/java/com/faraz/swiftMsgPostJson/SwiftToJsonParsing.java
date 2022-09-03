package com.faraz.swiftMsgPostJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prowidesoftware.swift.model.mt.AbstractMT;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SwiftToJsonParsing extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        String jsonString = "";

        from("file:C:/Users/Dell/Desktop/Folder One")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        Message input = exchange.getIn();
                        // reading body as a string from input message
                        String data = input.getBody(String.class);
                        AbstractMT msg = AbstractMT.parse(data);
                        String modifiedData = msg.toJson();


                        Message output = exchange.getMessage();
                        output.setBody(modifiedData);

//                        Map map = new HashMap();
//                        map.put("sender", jsonData.getSender());
//                        map.put("message type", jsonData.getMessageType());


//                        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//                        String jsonString = gson.toJson(map);
//
//                        System.out.println(jsonString);


                    }

                })
                .to("file:C:/Users/Dell/Desktop/Folder Two?fileName=${file:name.noext}.json");
    }
}
