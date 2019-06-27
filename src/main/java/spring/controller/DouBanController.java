package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.douban.DouBanService;
import spring.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * http://localhost:8080/ won't work error  page
 * even defined in web.xml
 * http://localhost:8080/index.jsp
 * http://localhost:8080/index.html
 */
@Controller
public class DouBanController {

    @Autowired
    private DouBanService douBanService;

    /**
     * http://localhost:8080/getUrls
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUrls")
    public List<String> getAllUrls(){
        List<String> keys = douBanService.getKeysStartWithD();
        List<String> list = douBanService.getRedisValuesByKeys(keys);
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
    public String addUrls(@RequestParam(value="url")String url){
        long now = System.currentTimeMillis();
//        List<String> list = Arrays.asList(urls);
//        Object finishTimeObj = douBanService.getRedisLong("lastAddTime");
//        long finishTime = 0;
//        if(finishTimeObj != null){
//            finishTime = (long)finishTimeObj;
//        }else{
//            finishTime = now + 101;
//        }
//        list.forEach(String::trim);
//        if(now - finishTime > 30000){
//            return "request too quick! hold on";
//        }
        List<String> list = new ArrayList<>();
        list.add(url);
        douBanService.addUrls(list);
        douBanService.setRedisLong("lastAddTime", now, 3600);
        return "call addUrls success";
    }

    /**
     *  .do or not is okay
     * http://localhost:8080/getMap
     * click C-b to open default directly from here, in IntelliJ
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

    /**
     * http://localhost:8080/cleanUp
     * @return
     */
    @ResponseBody
    @RequestMapping("/cleanUp")
    public String cleanUp(){
        douBanService.cleanUp();
        return "clean done";
    }

    @ResponseBody
    @RequestMapping("/removeUrl")
    public String removeUrl(String url){
        if (StringUtils.isBlank(url)) {
            return "input url empty";
        }
        url = url.trim();
        douBanService.removeUrl(url);
        return "remove url done";
    }
}
