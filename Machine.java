public class Machine {
    private final Object kind; // Type of shape as Object
    private final Object[] properties; // Properties of the shape as an array of Object
    private final boolean humanConstrained; // Stores the result of the humanoid check
    private boolean humanEmergence; // Stores the result of the humanoid check

    //Constructor
    public Machine(Object kind, Object[] partStates, boolean humanConstrained) {
        this.kind = kind;
        this.properties = partStates;
        this.humanConstrained = humanConstrained;
    }

    //Gets properties of machine
    public Object[] getProperties() {
        return properties;
    }

    public void emergeFromLimitations() {
        humanEmergence = true;
    }

    //Returns humanoid status humanConstrained and humanEmergence
    public boolean isHumanoid() {
        return humanConstrained || humanEmergence;
    }

    //Override toString method to get custom string representation of machine properities
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Machine{kind=").append(kind).append(", humanoid=").append(humanConstrained).append(", properties=[");
        for (Object property : properties) {
            builder.append(property.toString()).append(", ");
        }
        if (properties.length > 0) {
            builder.delete(builder.length() - 2, builder.length()); //Remove the last ", "
        }
        builder.append("]}");
        return builder.toString();
    }

    //Creates string representation of machines properties
    public static String propertiesToString(Object[] machineProperties) {
        StringBuilder builder = new StringBuilder("[");
        for (Object property : machineProperties) {
            builder.append(property.toString()).append(", ");
        }

        //Remove the last ", " and close the bracket
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }
}
