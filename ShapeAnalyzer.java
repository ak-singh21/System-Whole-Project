public class ShapeAnalyzer {
    private static final Object EMPTY_PROPERTY = new Object();

    //Analyze and extract kind and property and return object "Machine"
    public static Machine analyze(String json) {
        //Split JSON-like string into key-value pairs
        String[][] entries = parseEntries(json);

        //Extract kind and properties
        String kind = reifyKind(entries);
        Object[] properties = reifyProperties(entries);

        //Determine humanoid status based on properties
        boolean humanConstrained = SystemWhole.isHumanoid(properties);

        //Create and return a new Machine object
        return new Machine(kind, properties, humanConstrained);
    }

    //Splits JSON-like strings into key-value pairs
    public static String[][] parseEntries(String flatJson) {
        //Split into key-value pairs
        String[] pairs = flatJson.replaceAll("[{}\"]", "").split(", ");
        String[][] entries = new String[pairs.length][2];

        for (int i = 0; i < pairs.length; i++) {
            String[] keyValue = pairs[i].split(": ");
            entries[i][0] = keyValue[0];
            entries[i][1] = keyValue[1];
        }
        return entries;
    }

    //Extracts the kind from entries
    public static String reifyKind(String[][] entries) {
        //Find the entry with the key "kind" and return value
        for (String[] entry : entries) {
            if ("kind".equals(entry[0])) {
                return entry[1];
            }
        }
        return null; //Return null if kind is not found
    }

    //Extracts properties from entries
    public static Object[] reifyProperties(String[][] entries) {
        PartState[] properties = new PartState[entries.length];
        int index = 0;

        for (String[] entry : entries) {
            if (!"kind".equals(entry[0])) {
                properties[index++] = new PartState(entry[0], inferObject(entry[1]));
            }
        }

        //Make a new array with the right size and copy the elements
        PartState[] result = new PartState[index];
        for (int i = 0; i < index; i++) {
            result[i] = properties[i];
        }
        return result;
    }

    //Check if a character is a number between 0-9
    public static boolean isDigit(char c) {
        return (c >= '0' && c <= '9' );
    }

    //Checks if the string contains a non-numeric value
    public static boolean hasNonNumbers(String value) {
        char[] valChars = value.toCharArray();
        for (char c : valChars) {
            if (!isDigit(c) && c != '.') {
                return true;
            }
        }
        return false;
    }

    //Figures out object type from the string value given
    public static Object inferObject(String value) {
        if (value == null || value.isEmpty()) {
            return EMPTY_PROPERTY;
        } else if (hasNonNumbers(value)) {
            return value;
        } else if (value.contains(".")) {
            return Double.parseDouble(value);
        } else {
            return Integer.parseInt(value);
        }
    }
}
