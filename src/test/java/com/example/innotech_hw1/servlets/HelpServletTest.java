package com.example.innotech_hw1.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelpServletTest {
    private HelpServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter responseWriter;

    @BeforeEach
    public void setUp() throws IOException {
        servlet = new HelpServlet();
        servlet.init();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        responseWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    @AfterEach
    public void close() throws IOException {
        responseWriter.close();
    }

    @Test
    @SneakyThrows
    public void doPost_OK() {
        // given
        String phrase = "Help phrase for test";
        BufferedReader reader = mock(BufferedReader.class);

        Mockito.when(request.getReader()).thenReturn(reader);
        Mockito.when(reader.lines()).thenReturn(Stream.of(phrase));

        // when
        servlet.doPost(request, response);

        // then
        Mockito.verify(response).setStatus(Mockito.eq(200));
    }

    @Test
    @SneakyThrows
    public void doGet_OK() {
        // given
        List<String> defaultWords = Arrays.asList("У тебя все получится", "Все будет хорошо", "Не унывай");

        // when
        servlet.doGet(request, response);

        // then
        Mockito.verify(response).setContentType(Mockito.eq("text/plain"));
        assertTrue(
                defaultWords.contains(responseWriter.toString().trim())
        );
    }
}
