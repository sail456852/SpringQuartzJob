package tij.nio.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {

    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        // create a file
        FileOutputStream fileInputStream = new FileOutputStream("data.txt");
        FileChannel channel = fileInputStream.getChannel();
        channel.write(ByteBuffer.wrap("Some Txt".getBytes()));
        channel.close();
        // append on the file

        // read the file

    }
}
