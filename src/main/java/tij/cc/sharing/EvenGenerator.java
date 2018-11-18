package tij.cc.sharing;

public class EvenGenerator  extends IntGenerator{
    private int currentEvenValue = 0;
    @Override
    public int next() {
        ++currentEvenValue;// Danger point here
        ++currentEvenValue;// Danger point here
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
