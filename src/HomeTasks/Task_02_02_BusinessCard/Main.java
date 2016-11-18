package HomeTasks.Task_02_02_BusinessCard;
/*
2. Распарсить следующую структуру данных:
    {
        “name”: “…”,
        “surname”: “…”,
        “phones”: [“044-256-78-90”, “066-123-45-67”, …],
        “sites”: [“http://site1.com”, “http://site2.com”, …],
        “address”: {
            “country”: “…”,
            “city”: “…”,
            “street”: “…”
        }
    }
*/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        String path = "D:\\Java\\JavaPRO\\src\\HomeTasks\\Task_02_02_BusinessCard\\json.txt";
        String result = performRequest(path);

        Gson gson = new GsonBuilder()
                                .setPrettyPrinting()
                                .create();
        JSON json = (JSON) gson.fromJson(result, JSON.class);

        System.out.println(json);
        System.out.println();
        System.out.println("JSON: \t" + gson.toJson(json));
    }

    private static String performRequest(String path) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))){
            char[] buf = new char[1000000];

            int r = 0;
            do {
                if ((r = br.read(buf)) > 0)
                    sb.append(new String(buf, 0, r));
            } while (r > 0);
        }

        return sb.toString();
    }

}