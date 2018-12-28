package spring.springshiro;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/25<br/>
 * Time: 17:59<br/>
 * To change this template use File | Settings | File Templates.
 *  http://127.0.0.1/shiro
 */
@Controller
public class ShiroController {

    @RequestMapping("/shiro")
    @ResponseBody
    @RequiresPermissions("sales:info:status")
    public String shiroAuthentication(){
        System.err.println("ShiroController.shiroAuthentication");
        return "shiro authorization okay";
    }
}
