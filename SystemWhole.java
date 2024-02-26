public class SystemWhole {
    public static String[] emergences; // To store JSON strings representing emergences
    public static Machine[] parts; // To store Machine objects directly as an array

    public static void main(String[] args) {
        // Sample JSON strings representing different "emergences"
        String[] emergences = {
                "{\"kind\": \"Humanoid\", \"bodyType\": \"physical\", \"faceType\": \"anthropomorphic\", \"reverie\": \"mechatypical\"}",
                "{\"kind\": \"Humanoid\", \"bodyType\": \"physical\", \"faceType\": \"anthropomorphic\", \"reverie\": \"biotypical\"}",
                "{\"kind\": \"Robot\", \"material\": \"metal\", \"function\": \"industrial\"}",
                "{\"kind\": \"Humanoid\", \"bodyType\": \"physical\", \"faceType\": \"anthropomorphic\"}"
        };

        //Parse the emergences and set them to the SystemWhole state
        emergencesFromPhenomena(emergences);
        //Analyze the shapes based on the set emergences
        reifyFrameOfReference();
        //Create a string object to print parts
        String partString = "";
        for (Machine part : parts) {
            partString += part.toString();
        }
        System.out.println("Prelude of the Rise of the Machines: " + partString);
        parts[0].emergeFromLimitations();
        //Track humanoid machines and identify singularities
        Machine[] singularities = trackSingularityMachines();
        //Create a string object to print singularities
        String singString = "";
        for (Machine sing : singularities) {
            singString += Machine.propertiesToString(sing.getProperties());
        }
        System.out.println("Singularities: " + singString);
    }

    //Visibility modifiers: public vs private
    public static void emergencesFromPhenomena(String[] emergences) {
        //Class and Object State: static vs this
        //Storing JSON strings in "emergences"
        SystemWhole.emergences = emergences;
    }

    public static void reifyFrameOfReference() {
        SystemWhole.parts = new Machine[emergences.length];
        int i = 0;
        for (String emergence : emergences) {
            //Analyze emergences and create object "machine" with specific properties
            SystemWhole.parts[i++] = ShapeAnalyzer.analyze(emergence);
        }

    }

    public static boolean isHumanoid(Object[] machineProperties) {
        //Check if the machine is humanoid based on given properties
        //Go through properties and check bodyType, faceType, and reverie
        boolean physicalBody = false;
        boolean anthropomorphicFace = false;
        boolean biotypicalReverie = false;

        for (Object property : machineProperties) {

            String propertyString = property.toString();
            //Split at '=' to get property name and value
            String[] parts = propertyString.split("=");
            String propertyName = parts[0];
            String propertyValue = parts[1];

            if ("bodyType".equals(propertyName) && "physical".equals(propertyValue)) {
                physicalBody = true;
            }
            if ("faceType".equals(propertyName) && "anthropomorphic".equals(propertyValue)) {
                anthropomorphicFace = true;
            }
            if ("reverie".equals(propertyName) && "biotypical".equals(propertyValue)) {
                biotypicalReverie = true;
            }
        }

        //If all conditions for are met
        return physicalBody && anthropomorphicFace && biotypicalReverie;
    }

    // SystemWhole's logic to determine if a Machine is humanoid and count them
    public static int identitySingularityMachines() {
        int singularityCount = 0;

        for (Machine machine : parts) {
            if (machine.isHumanoid() && !isHumanoid(machine.getProperties())) {
                singularityCount++;
            }
        }

        //Return singularities count
        return singularityCount;
    }

    //Collects and returns singularities
    public static Machine[] trackSingularityMachines() {
        int count = identitySingularityMachines();

        Machine[] singularities = new Machine[count];
        int index = 0;
        for (Machine machine : parts) {
            if (machine.isHumanoid() && !isHumanoid(machine.getProperties())) {
                singularities[index++] = machine;
            }
        }

        return singularities;
    }
}
