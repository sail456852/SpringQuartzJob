package tij.enumeration.example;

public class Meal {
    public static void main(String[] args) {
        Course[] courses = Course.values();
        for (int i = 0; i < 5; i++) {
            for (Course cours : courses) {
                Food food = cours.randomSelection();
                System.out.println("food = " + food);
            }
        }
    }
}
