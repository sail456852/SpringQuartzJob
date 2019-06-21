package spring.utils;

//import parquet.it.unimi.dsi.fastutil.ints.IntIterators;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: yz<br/>
 * Date: 6/5/2019<br/>
 * Time: 10:51 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class IntUtils {

    /**
     * will be running on the first run
     */
    private static Random random = new Random();

    /**
     * simple singleton demo
      * @return
     */
    public static int randomIntSingleton(int bound){
        int i = random.nextInt(bound);
        return i;
    }
}
