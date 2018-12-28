package spring.springshiro;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/28<br/>
 * Time: 19:41<br/>
 * To change this template use File | Settings | File Templates.
 * created in Spring context bean
 */
public class UserFilter extends AuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.err.println("UserFilter.onAccessDenied");
        return false;
    }
}
