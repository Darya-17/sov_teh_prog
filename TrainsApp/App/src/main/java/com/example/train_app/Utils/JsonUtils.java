package com.example.train_app.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
    public static JsonUtils Instance = new JsonUtils();
    private JsonUtils(){
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }
    public String serializeObject(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
    private ObjectMapper mapper;
    public ObjectNode createJObject(){
        return mapper.createObjectNode();
    }
    public ArrayNode createJArray(){
        return mapper.createArrayNode();
    }
    public <T> T deserialize(String data, Class<T> valueType) throws JsonProcessingException {
        return mapper.readValue(data, valueType);
    }
    public ObjectNode getObjectNodeFromObject(Object obj) throws JsonProcessingException {
        return deserialize(serializeObject(obj), ObjectNode.class);
    }

}
