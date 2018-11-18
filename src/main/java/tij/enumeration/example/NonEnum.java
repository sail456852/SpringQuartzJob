package tij.enumeration.example;

public class NonEnum {
    public static void main(String[] args) {
        Class<Integer> integerClass = Integer.class;
        try {
            for(Object en : integerClass.getEnumConstants())
                System.out.println("en = " + en); // nullpoint exception !
        } catch (Exception e) {
            System.out.println("e = " + e);
            e.printStackTrace();
        }
    }
}
