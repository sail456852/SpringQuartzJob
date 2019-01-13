package msc.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

/**
 * Created by Okita.<br/>
 * User: yz<br/>
 * Date: 1/12/19<br/>
 * Time: 1:56 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class MapFileTest {

    public static void main(String[] args) throws IOException {
        long l = crcValue();
        System.err.println("l = " + l);
    }

    private static long crcValue() throws IOException {
        CRC32 crc = new CRC32();
        FileChannel fileChannel = new FileInputStream("doubanLogonCookies").getChannel();
        long size = fileChannel.size();
        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
//        while(map.hasRemaining()){
//            byte b = map.get();
//            crc.update(b);
//        }
//        long value = crc.getValue();
        for (int i = 0; i < size ; i++) {
            byte b = map.get(i);
            crc.update(b);
        }
        long value = crc.getValue();
        return value;
    }
}
