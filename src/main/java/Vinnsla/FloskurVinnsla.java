package Vinnsla;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * @author María Rún Karlsdóttir
 */
public class FloskurVinnsla {
    private static final String FILE_PATH = "src/main/resources/org/example/floskur/floskur.json";

    // Read JSON file
    public static Floskur readFloskurData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(FILE_PATH), Floskur.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Floskur(); // Return empty/default object on error
        }
    }

    // Write JSON file (save new values)
    public static void writeFloskurData(Floskur data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(FILE_PATH), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
