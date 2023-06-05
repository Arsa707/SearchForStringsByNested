import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static HashMap<String, String> map = new HashMap<>();
    static String delimiter = "!!!!!";

    public static void main(String[] args) {
        //Ловим ошибки
        try (FileInputStream fileInputStream = new FileInputStream(".\\src\\Text.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader buffer = new BufferedReader(inputStreamReader)) {
            //Выгружаем файл в колелкцию, добавляя разделитель в строки
            while (buffer.ready()) {
                list.add(buffer.readLine() + delimiter);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Ищем вхождение строки в другие строки коллекции
        list.stream().forEach(s ->
                list.stream().filter(p ->
                        (p.indexOf(s) > 0)).collect(Collectors.toList()).forEach(f -> {
                    if (!s.equals(f)) map.put(s.replaceAll(delimiter, "  - "), f.replaceAll(delimiter, ";"));
                }));

        //Для теста в консоли
        // map.forEach((k, v) -> System.out.println(k + v));


        File file = new File(".\\src\\Duplicates.txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
            map.forEach((k, v) -> {
                try {
                    fileWriter.write(k + v + "\r\n");
                    } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}