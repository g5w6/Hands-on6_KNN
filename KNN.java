import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KNN {
    private final List<CancerData> trainingData;
    private final int k;

    public KNN(List<CancerData> trainingData, int k) {
        this.trainingData = trainingData;
        this.k = k;
    }

    public ClassificationResult classify(CancerData newData) {
        List<Neighbor> neighbors = new ArrayList<>();
        
        for (CancerData data : trainingData) {
            double distance = calculateDistance(newData, data);
            // Comentado para evitar la impresión
            // System.out.println("Comparando con: " + data + " -> Distancia: " + distance); 
            neighbors.add(new Neighbor(distance, data.getType()));
        }
        
        Collections.sort(neighbors, Comparator.comparingDouble(Neighbor::getDistance));

        Map<String, Integer> classCount = new HashMap<>();
        List<Neighbor> kNearestNeighbors = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            String type = neighbors.get(i).getType();
            kNearestNeighbors.add(neighbors.get(i));
            classCount.put(type, classCount.getOrDefault(type, 0) + 1);
        }

        String mostFrequentClass = classCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        return new ClassificationResult(kNearestNeighbors, mostFrequentClass, classCount);
    }

    private double calculateDistance(CancerData d1, CancerData d2) {
        return Math.sqrt(Math.pow(d1.getRadiusMean() - d2.getRadiusMean(), 2)
                + Math.pow(d1.getTextureMean() - d2.getTextureMean(), 2)
                + Math.pow(d1.getPerimeterMean() - d2.getPerimeterMean(), 2)
                + Math.pow(d1.getAreaMean() - d2.getAreaMean(), 2));
    }

    public static class Neighbor {
        private final double distance;
        private final String type;

        public Neighbor(double distance, String type) {
            this.distance = distance;
            this.type = type;
        }

        public double getDistance() {
            return distance;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return "Distance: " + distance + ", Type: " + type;
        }
    }

    public static class ClassificationResult {
        private final List<Neighbor> kNearestNeighbors;
        private final String mostFrequentClass;
        private final Map<String, Integer> classCount;

        public ClassificationResult(List<Neighbor> kNearestNeighbors, String mostFrequentClass, Map<String, Integer> classCount) {
            this.kNearestNeighbors = kNearestNeighbors;
            this.mostFrequentClass = mostFrequentClass;
            this.classCount = classCount;
        }

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