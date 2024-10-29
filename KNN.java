import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNN {
    private final List<CancerData> trainingData;
    private final int k;

    // Constructor para inicializar los datos de entrenamiento y el valor de k
    public KNN(List<CancerData> trainingData, int k) {
        this.trainingData = trainingData;
        this.k = k;
    }

    // Método para clasificar una nueva entrada de datos
    public ClassificationResult classify(CancerData newData) {
        System.out.println("Datos de entrada: " + newData); // Imprimir datos de entrada

        List<Neighbor> neighbors = new ArrayList<>();

        // Calcular la distancia entre la nueva entrada y cada dato de entrenamiento
        for (CancerData data : trainingData) {
            double distance = calculateDistance(newData, data);
            System.out.println("Comparando con: " + data + " -> Distancia: " + distance); // Imprimir datos del vecino y distancia
            neighbors.add(new Neighbor(distance, data.getType()));
        }

        // Ordenar los vecinos por distancia
        Collections.sort(neighbors, Comparator.comparingDouble(Neighbor::getDistance));
        Map<String, Integer> classCount = new HashMap<>();
        List<Neighbor> kNearestNeighbors = new ArrayList<>();

        // Contar las clases de los k vecinos más cercanos
        for (int i = 0; i < k; i++) {
            String type = neighbors.get(i).getType();
            kNearestNeighbors.add(neighbors.get(i));
            classCount.put(type, classCount.getOrDefault(type, 0) + 1);
        }

        // Encontrar la clase más frecuente entre los k vecinos más cercanos
        String mostFrequentClass = classCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        // Devolver el resultado de la clasificación
        return new ClassificationResult(kNearestNeighbors, mostFrequentClass, classCount);
    }

    // Método para calcular la distancia euclidiana entre dos entradas de datos
    private double calculateDistance(CancerData d1, CancerData d2) {
        return Math.sqrt(Math.pow(d1.getRadiusMean() - d2.getRadiusMean(), 2)
                + Math.pow(d1.getTextureMean() - d2.getTextureMean(), 2)
                + Math.pow(d1.getPerimeterMean() - d2.getPerimeterMean(), 2)
                + Math.pow(d1.getAreaMean() - d2.getAreaMean(), 2));
    }

    // Clase interna para representar un vecino
    public static class Neighbor {
        private final double distance;
        private final String type;

        // Constructor para inicializar la distancia y el tipo del vecino
        public Neighbor(double distance, String type) {
            this.distance = distance;
            this.type = type;
        }

        // Métodos get para la distancia y el tipo
        public double getDistance() {
            return distance;
        }

        public String getType() {
            return type;
        }

        // Método toString para imprimir la información del vecino
        @Override
        public String toString() {
            return "Distance: " + distance + ", Type: " + type;
        }
    }

    // Clase interna para representar el resultado de la clasificación
    public static class ClassificationResult {
        private final List<Neighbor> kNearestNeighbors;
        private final String mostFrequentClass;
        private final Map<String, Integer> classCount;

        // Constructor para inicializar los vecinos más cercanos, la clase más frecuente y el conteo de clases
        public ClassificationResult(List<Neighbor> kNearestNeighbors, String mostFrequentClass, Map<String, Integer> classCount) {
            this.kNearestNeighbors = kNearestNeighbors;
            this.mostFrequentClass = mostFrequentClass;
            this.classCount = classCount;
        }

        // Métodos get para los vecinos más cercanos, la clase más frecuente y el conteo de clases
        public List<Neighbor> getkNearestNeighbors() {
            return kNearestNeighbors;
        }

        public String getMostFrequentClass() {
            return mostFrequentClass;
        }

        public Map<String, Integer> getClassCount() {
            return classCount;
        }
    }
}