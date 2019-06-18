package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.douban.DouBanService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class DouBanController {

    @Autowired
    private DouBanService douBanService;

    @ResponseBody
    @RequestMapping("/getAllUrls")
    public List<String> getAllUrlsFromRedis(){

        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("World");

        return strings;
    }


    /**
     * http://localhost:8080/getMap
     * requires Spring.xml converter & pom.xml Jackson dependency
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMap")
    public HashMap<String, String> getMap(){

        HashMap<String, String> map = new HashMap<>();
        map.put("Hello", "World");
        map.put("Jack","Jones");

        return map;
    }
}
