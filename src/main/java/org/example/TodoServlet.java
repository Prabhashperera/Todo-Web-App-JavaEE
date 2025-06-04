package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoApp", "root", "1234");
            PreparedStatement stm = connection.prepareStatement("select * from todo");
            ResultSet rs = stm.executeQuery();
            List<Map<String, String>> todoList = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> todo = new HashMap<>();
                todo.put("name" , rs.getString("name"));
                todo.put("description" , rs.getString("description"));
                todo.put("time", rs.getString("time"));
                todoList.add(todo);
            }
            resp.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(resp.getWriter(), todoList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoApp", "root", "1234");

            BufferedReader reader = req.getReader();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> todoData = mapper.readValue(reader, Map.class);

            String name = todoData.get("name");
            String time = todoData.get("time");
            String description = todoData.get("description");

            PreparedStatement stm = connection.prepareStatement("INSERT INTO todo(name, description, time) values (?,?,?)");
            stm.setString(1, name);
            stm.setString(2, description);
            stm.setString(3, time);
            stm.executeUpdate();

            resp.setContentType("application/json");
            resp.getWriter().write("{\"success\": true}");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
