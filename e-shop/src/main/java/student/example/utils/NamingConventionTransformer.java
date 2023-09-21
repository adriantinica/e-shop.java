package student.example.utils;


public class NamingConventionTransformer {

    //HW1: 
    // COMPLETE THIS METHODS;
    // A..Za..z : pascal
    // a..z_    : table
    // try to use regular expressions
    
    public String pascalToSnake(String name) {
        return name.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    public  String snakeToPascal(String name) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : name.toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    
    // HW2: 
    //    snake_case -> dot.notation
    //    dot.notation -> PascalCase

      // HW3:
    //       try to optimize the code

    


















}
