package spring.douban;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Okita.<br/>
 * User: yz<br/>
 * Date: 1/11/19<br/>
 * Time: 11:35 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class MapConvertFile {
    private final static String fileName = "mapConvertFile";

    @Test
    public void map2file()
            throws IOException, ClassNotFoundException {
        Map<String, String> paramMap = getParamMap();
        outputFile(paramMap, fileName);
        HashMap<String, String> mapFromFile = file2HashMap(fileName);
        System.err.println("mapFromFile = " + mapFromFile);
        Assert.assertEquals(paramMap.hashCode(), mapFromFile.hashCode());
        Assert.assertEquals(paramMap.toString(), mapFromFile.toString());
        Assert.assertTrue(paramMap.equals(mapFromFile));
    }

    public static void outputFile(Map<String, String> map, String fileName) throws IOException {
        ObjectOutputStream s = new ObjectOutputStream(new DataOutputStream
                (new BufferedOutputStream(new FileOutputStream(fileName))));
        try {
            URL resource = Thread.currentThread().getContextClassLoader().getResource("spring/timedjob/doubanLogonCookies");
            String path = resource.toURI().getPath();
            System.err.println("path = " + path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        s.writeObject(map);
        s.flush();
        s.close();
    }

    public static void outputFileJackson(Map<String, String> map, String fileName) throws IOException {
        ObjectOutputStream s = new ObjectOutputStream(new DataOutputStream
                (new BufferedOutputStream(new FileOutputStream(fileName))));
//         serialize map object to store it in a file
        //<editor-fold desc="Jackson Object">
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        s.writeObject(jsonResult);
        //</editor-fold>
        s.flush();
        s.close();
    }

    /**
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * read from project root, or webapps folder root on the server,
     */
    public static HashMap<String, String> file2HashMap(String fileName) throws IOException, ClassNotFoundException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        ObjectInputStream s = new ObjectInputStream(dis);
        HashMap<String, String> hashMap = (HashMap<String, String>) s.readObject();
        return hashMap;
    }

    public static HashMap<String, String> inputFileJackson(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream s = null;
//        try {
        String strFromFile = FileUtil.readAsString(new File(fileName));
        System.err.println("strFromFile = " + strFromFile);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> typeReference = new TypeReference<HashMap<String, String>>() {
        };
        Map<String, String> map = mapper.readValue(strFromFile, typeReference);
        System.err.println("map = " + map);
        return (HashMap<String, String>) map;
//        } finally {
//            s.close(); // null pointer exception
//        }
    }

    public static Map<String, String> getParamMap() throws FileNotFoundException {
        Map<String, String> params = new HashMap<>();
        params.put("ck", "fnPz");
        params.put("k", "176233839:6645873e4815c0cf2257c526e58b9a1ddb73928e");
        params.put("from_push", "undefined");
        params.put("callback", "jsonp_j0x74alhmjxly0y");
        return params;
    }

    /**
     *
     * @param str must be valid json like format e.g. { key=val, key=val}
     * @return
     */
    public static Map<String, String> string2HashMap(String str){
        str = str.substring(1, str.length() - 1);
        Map<String, String> reconstructedUtilMap = Arrays.stream(str.split(","))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        return reconstructedUtilMap;
    }

    /**
     *
     * @param str must be valid json like format e.g. { key=val, key=val}
     * @return
     */
    public static Map<String, String> string2HashMapColon(String str){
        str = str.substring(1, str.length() - 1);
        Map<String, String> reconstructedUtilMap = Arrays.stream(str.split(";"))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        return reconstructedUtilMap;
    }
}
