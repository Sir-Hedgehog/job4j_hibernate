package ru.job4j.items;

import com.google.gson.Gson;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.04.2020
 */

public class ItemListServlet extends HttpServlet {

    private final Store database = ItemDatabase.getInstance();

    /**
     * Метод получает данные о статусе регулятора и передает их БД для получения результирующего списка задач
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String regulator = request.getParameter("regulator");
        String json = new Gson().toJson(database.getItems(regulator));
        response.getWriter().write(json);
    }
}
