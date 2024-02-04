package com.example.innotech_hw1.servlets;

import com.example.innotech_hw1.services.HelpService;
import com.example.innotech_hw1.services.IHelpService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;


@WebServlet(value = "/help-service/v1/support")
public class HelpServlet extends HttpServlet {
    private IHelpService helpService;

    @Override
    public void init() {
        helpService = new HelpService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String phrase = req.getReader().lines().collect(Collectors.joining("\n"));
        helpService.addPhrase(phrase);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.println(helpService.getPhrase());
            printWriter.flush();
        }
    }
}
