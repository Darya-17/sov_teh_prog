package com.example.building_materials_server.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpEntity;

public class ResponseUtils {
    public static String formJsonAnswer(ResponseType type, String info, String data) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseObj = mapper.createObjectNode();
        responseObj.put("type", type.toString());
        responseObj.put("info", info);
        responseObj.put("data", data);
        return responseObj.toString();
    }

    public static ObjectNode readJsonFromResponse(HttpEntity<String> httpEntity) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.readValue(httpEntity.getBody(), ObjectNode.class);
    }

    public static String SerializeObject(Object obj) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);

    }
}
