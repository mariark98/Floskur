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

    /**
     * Les inn Json skrá
     * @return Fjölda af flöskum og skilagjald
     */
    public static Floskur lesaFloskurData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(FILE_PATH), Floskur.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Floskur();
        }
    }

    /**
     * Skrifar inn í Json skrá
     * @param data gögnin sem fara inn í skránna
     */
    public static void skrifaFloskurData(Floskur data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(FILE_PATH), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uppfærir gildið á greiða - uppfærir skránna
     * <p>Setur nýtt gjald fyrir greiða og skilagkald,
     * uppfæriri það sem er búið að setja í kerfið.</p>
     * @param greida fjöldi sem á að vista
     * @param ISKGreida skilagjald  sem á að vista
     */
    public static void uppfaeraGreidaValues(int greida, int ISKGreida) {
        Floskur data = lesaFloskurData();
        data.setGreida(greida);
        data.setIskgreida(ISKGreida);
        skrifaFloskurData(data);
    }

    /**
     * Hreinsar gjalidð á greiða - hreinsar skránna
     * <p>Setur nýtt gjald fyrir greiða og skilagjald, setur gildið 0 og hreinsar þá skjalið.</p>
     */
    public static void hreinsaGreidaValues() {
        Floskur data = lesaFloskurData();
        data.setGreida(0);
        data.setIskgreida(0);
        skrifaFloskurData(data);
    }
}
