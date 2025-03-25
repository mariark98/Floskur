package Vinnsla;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Les inn skrá sem heldur utan um gögn
 * Uppfærir skrá þegar beðið er um uppfærslu
 * Hreinsar gögn þegar beðið er um að hreinsa skrá
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
            return new Floskur();
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

    public static void updateGreidaValues(int greida, int ISKGreida) {
        Floskur data = readFloskurData();
        data.setGreida(greida);
        data.setIskgreida(ISKGreida);
        writeFloskurData(data);
    }

    public static void clearGreidaValues() {
        Floskur data = readFloskurData();
        data.setGreida(0);
        data.setIskgreida(0);
        writeFloskurData(data);
    }
}
