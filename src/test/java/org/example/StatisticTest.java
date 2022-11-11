package org.example;

import com.google.gson.Gson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование: Statistic")
class StatisticTest {
    public static Statistic statistic;
    public Gson gson = new Gson();

    @BeforeEach
    public void createStatistic() {
        statistic = new Statistic();
    }

    @Test
    @DisplayName("Тестирование: сумма трат у категории с максимальными тратами")
    void getMaxCategoryBySum() {

        Request clientRequest = gson.fromJson("{\"title\": \"булка\", \"date\": \"2012.11.11\", \"sum\": 100}", Request.class);
        statistic.addItem(clientRequest);

        clientRequest = gson.fromJson("{\"title\": \"колбаса\", \"date\": \"2012.11.11\", \"sum\": 699}", Request.class);
        statistic.addItem(clientRequest);

        clientRequest = gson.fromJson("{\"title\": \"шапка\", \"date\": \"2012.11.11\", \"sum\": 750}", Request.class);
        statistic.addItem(clientRequest);

        Assertions.assertEquals(799, statistic.getMaxCategory().getSum());
    }

    @Test
    @DisplayName("Тестирование: название категории с максимальными тратами")
    void getMaxCategoryName() {

        Request clientRequest = gson.fromJson("{\"title\": \"булка\", \"date\": \"2012.11.11\", \"sum\": 50}", Request.class);
        statistic.addItem(clientRequest);

        clientRequest = gson.fromJson("{\"title\": \"колбаса\", \"date\": \"2012.11.11\", \"sum\": 699}", Request.class);
        statistic.addItem(clientRequest);

        clientRequest = gson.fromJson("{\"title\": \"конструктор\", \"date\": \"2012.11.11\", \"sum\": 2990}", Request.class);
        statistic.addItem(clientRequest);

        Assertions.assertEquals("другое", statistic.getMaxCategory().getCategoryName());
    }

    @Test
    @DisplayName("Тестирование: отправляемый ответ")
    void getServerResponse() {
        Request clientRequest = gson.fromJson("{\"title\": \"колбаса\", \"date\": \"2012.11.11\", \"sum\": 699}", Request.class);
        statistic.addItem(clientRequest);

        clientRequest = gson.fromJson("{\"title\": \"тапки\", \"date\": \"2012.11.11\", \"sum\": 475}", Request.class);
        statistic.addItem(clientRequest);

        clientRequest = gson.fromJson("{\"title\": \"шапка\", \"date\": \"2012.11.11\", \"sum\": 2500}", Request.class);
        statistic.addItem(clientRequest);

        Assertions.assertEquals("\"maxCategory\": {" +
                "    \"category\": \"" + "одежда" + "\"," +
                "    \"sum\": \"" + "2975" + "\"" +
                "  }", statistic.getServerResponse());
    }
}