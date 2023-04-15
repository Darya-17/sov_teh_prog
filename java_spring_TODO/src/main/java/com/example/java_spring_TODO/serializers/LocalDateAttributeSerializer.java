package com.example.java_spring_TODO.serializers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAttributeSerializer implements Converter<LocalDate, String>,
        com.fasterxml.jackson.databind.util.Converter<LocalDate, String> {
    private DateTimeFormatter _formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(LocalDate.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(String.class);
    }

    @Override
    public String convert(LocalDate source) {
        return source.format(_formatter);
    }
}
