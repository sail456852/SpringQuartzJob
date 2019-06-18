package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping("/getUrls")
    public List<String> getAllUrlsFromRedis(){
        List<String> keys = douBanService.getTieziKeysRedis();
        List<String> list = douBanService.getTieziUrlsRedis(keys);
        return list;
    }

    /**
     * Postman usage
     * POST method
     * Put url key - value in params tab, always use url as key not url[] or url[1]
     * Put in Body -> form-data or json won't work
     * @param urls
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUrls")
    public String addUrls(@RequestParam(value="url")String[] urls){
        System.err.println("urls = " + urls.length);
        for (String url : urls) {
            System.err.println("url = " + url);
        }
        return null;
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
