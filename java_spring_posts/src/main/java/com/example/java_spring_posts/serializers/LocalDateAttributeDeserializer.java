package com.example.java_spring_posts.serializers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAttributeDeserializer implements Converter<String, LocalDate>,
        com.fasterxml.jackson.databind.util.Converter<String, LocalDate> {

    private DateTimeFormatter _formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(String.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(LocalDate.class);
    }

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source, _formatter);
    }
}
