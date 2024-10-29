import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Ruta del archivo CSV
        String filePath = "C:\\Users\\Mario GGS\\Downloads\\AviñaUnidad1_MarioGomez\\prueba\\Cancer_Data.csv";
          // Generar un valor aleatorio para k entre 3 y 10
        Random random = new Random();
          int k = random.nextInt(8) + 3; // (10 - 3 + 1) = 8, + 3 para el rango de 3 a 10

        // Leer los datos del archivo CSV
        List<CancerData> dataList = CSVReader.readCSV(filePath);
        // Inicializar el algoritmo KNN con los datos leídos y el valor de k
        KNN knn = new KNN(dataList, k);

        // Crear una nueva entrada de datos para clasificar
        CancerData newData = new CancerData("", "", 12.17, 20.47, 85.72, 586.3);
        // Clasificar la nueva entrada usando el algoritmo KNN
        KNN.ClassificationResult result = knn.classify(newData);

        // Imprimir las distancias y clasificaciones de los k vecinos más cercanos
        System.out.println("Distancias y clasificaciones K:");
        for (KNN.Neighbor neighbor : result.getkNearestNeighbors()) {
            System.out.println(neighbor);
        }

        // Imprimir los resultados de la clasificación
        System.out.println("\nResultados de clasificacion:");
        for (Map.Entry<String, Integer> entry : result.getClassCount().entrySet()) {
            System.out.println("Clase: " + entry.getKey() + " - Repeticiones: " + entry.getValue());
        }

        // Imprimir la clase más frecuente entre los k vecinos más cercanos
        System.out.println("\nClase mas frecuente: " + result.getMostFrequentClass());
    }
}