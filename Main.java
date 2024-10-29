import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Mario GGS\\Downloads\\AviñaUnidad1_MarioGomez\\Hands-on6_KNN\\Cancer_Data.csv";

        // Generar un valor aleatorio para k entre 3 y 10
        Random random = new Random();
        int k = 3 + random.nextInt(8); // Genera un número entre 3 y 10

        List<CancerData> dataList = CSVReader.readCSV(filePath);
        KNN knn = new KNN(dataList, k);

        // Generar valores aleatorios para la nueva entrada
        double radiusMean = 10 + (20 - 10) * random.nextDouble();
        double textureMean = 10 + (30 - 10) * random.nextDouble();
        double perimeterMean = 50 + (150 - 50) * random.nextDouble();
        double areaMean = 200 + (1000 - 200) * random.nextDouble();

        CancerData newData = new CancerData("", "", radiusMean, textureMean, perimeterMean, areaMean);
        KNN.ClassificationResult result = knn.classify(newData);

        System.out.println("Datos de entrada generados aleatoriamente:");
        System.out.println("radius_mean: " + radiusMean);
        System.out.println("texture_mean: " + textureMean);
        System.out.println("perimeter_mean: " + perimeterMean);
        System.out.println("area_mean: " + areaMean);

        System.out.println("\nDistancias y clasificaciones K:");
        for (KNN.Neighbor neighbor : result.getkNearestNeighbors()) {
            System.out.println(neighbor);
        }

        System.out.println("\nResultados de clasificacion:");
        for (Map.Entry<String, Integer> entry : result.getClassCount().entrySet()) {
            System.out.println("Clase: " + entry.getKey() + " - Repeticiones: " + entry.getValue());
        }

        System.out.println("\nClase mas frecuente: " + result.getMostFrequentClass());
    }
}