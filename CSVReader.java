import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVReader {
    // Logger para registrar mensajes de error
    private static final Logger logger = Logger.getLogger(CSVReader.class.getName());

    // Método para leer el archivo CSV y devolver una lista de objetos CancerData
    public static List<CancerData> readCSV(String filePath) {
        List<CancerData> dataList = new ArrayList<>();
        String csvSplitBy = ",";

        // Intentar leer el archivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Saltar la cabecera
            String line;
            // Leer cada línea del archivo
            while ((line = br.readLine()) != null) {
                // Dividir la línea en valores usando la coma como separador
                String[] values = line.split(csvSplitBy);
                String id = values[0];
                String type = values[1];
                double radius = Double.parseDouble(values[2]);
                double texture = Double.parseDouble(values[3]);
                double perimeter = Double.parseDouble(values[4]);
                double area = Double.parseDouble(values[5]);

                // Crear un objeto CancerData y añadirlo a la lista
                CancerData data = new CancerData(id, type, radius, texture, perimeter, area);
                dataList.add(data);
            }
        } catch (IOException e) {
            // Registrar un mensaje de error si ocurre una excepción
            logger.log(Level.SEVERE, "Error reading CSV file", e);
        }

        // Devolver la lista de datos leídos
        return dataList;
    }
}