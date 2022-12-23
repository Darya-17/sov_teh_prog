package Utils;

import com.github.cliftonlabs.json_simple.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUtils {
    // Вспомогательные функции для работы с json
    public static Jsonable getJsonFromFile(String file_name) {
        // Получение объекта json из файла
        try (FileReader reader = new FileReader(file_name)) {
            var sb = new StringBuffer();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            return (Jsonable) Jsoner.deserialize(sb.toString());
        } catch (IOException ex) {
            return null;
        } catch (JsonException ex) {
            saveJsonToFile(file_name, "[]");
            return null;
        }
    }

    public static boolean saveJsonToFile(String file_name, String json) {
        // Сохранение строки json в файл
        try (FileWriter writer = new FileWriter(file_name)) {
            writer.write(json);
            writer.flush();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }


}
