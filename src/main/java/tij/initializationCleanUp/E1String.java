package tij.initializationCleanUp;

public class E1String {
    private static String fieldStr;

    public static void main(String[] args) {
         String localStr = "compilerForceYouInitializeThis";
        System.out.println("localStr = " + localStr);
        System.out.println("fieldStr = " + fieldStr); // null by default
    }
}
