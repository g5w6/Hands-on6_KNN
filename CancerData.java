public class CancerData {
    private final String id;
    private final String type;
    private final double radiusMean;
    private final double textureMean;
    private final double perimeterMean;
    private final double areaMean;

    public CancerData(String id, String type, double radiusMean, double textureMean, double perimeterMean, double areaMean) {
        this.id = id;
        this.type = type;
        this.radiusMean = radiusMean;
        this.textureMean = textureMean;
        this.perimeterMean = perimeterMean;
        this.areaMean = areaMean;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getRadiusMean() {
        return radiusMean;
    }

    public double getTextureMean() {
        return textureMean;
    }

    public double getPerimeterMean() {
        return perimeterMean;
    }

    public double getAreaMean() {
        return areaMean;
    }

    @Override
    public String toString() {
        return "CancerData{id='" + id + "', type='" + type + "', radiusMean=" + radiusMean +
            ", textureMean=" + textureMean + ", perimeterMean=" + perimeterMean + ", areaMean=" + areaMean + '}';
    }
}