package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 8989;

    public static void main(String[] args) {
        while (true) {
            try (Socket clientSocket = new Socket(IP, PORT);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Введите через пробел: наименование товара, дату покупки в формате ГГГГ.ММ.ДД. и сумму покупки в руб.");
                String input = reader.readLine();
                String[] inputSplit = input.split(" ");
                String purchaseTitle = inputSplit[0];
                String purchaseDate = inputSplit[1];
                int purchaseSum = Integer.parseInt(inputSplit[2]);

                out.println("{\"title\": \"" + purchaseTitle + "\", \"date\": \"" + purchaseDate + "\", " +
                        "\"sum\": " + purchaseSum + "}");
                System.out.println(in.readLine());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        }
    }
}