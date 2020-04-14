package ru.job4j.items;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 13.04.2020
 */

public class UpdateStatusServlet extends HttpServlet {

    private final Store database = ItemDatabase.getInstance();

    /**
     * Метод получает данные о выбранном идентификаторе задания и его статусе, передает эти данные в обработку БД для обновления
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String done = request.getParameter("done");
        database.updateItem(id, done);
    }
}
