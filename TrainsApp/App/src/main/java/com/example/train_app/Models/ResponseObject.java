package com.example.train_app.Models;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ResponseObject {
    private final int _code;

    public int get_code() {
        return _code;
    }
    private final ObjectNode _responseInfo;



    public ObjectNode get_responseInfo() {
        return _responseInfo;
    }
    public ResponseObject(int code, ObjectNode responseInfo){
        _code = code;
        _responseInfo = responseInfo;
    }
}
