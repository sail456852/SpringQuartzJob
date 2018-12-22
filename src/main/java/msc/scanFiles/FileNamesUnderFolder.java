package msc.scanFiles;

import java.io.File;

/**
 * Created by Okita.<br/>
 * User: yz<br/>
 * Date: 12/1/18<br/>
 * Time: 9:25 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class FileNamesUnderFolder {
    public static void main(String[] args) {
       File file = new File("/home/yz/MyProjects/Java8Tests");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.err.println("file1 = " + file1); 
        }
    }
}
