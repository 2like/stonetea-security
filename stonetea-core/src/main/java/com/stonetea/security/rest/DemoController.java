package com.stonetea.security.rest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.sedooe.com/2016/04/rest-authentication-using-spring-security-and-spring-session/
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    ThreadLocal<String> authContext = new ThreadLocal<>();

    //  此方法会在每次请求前调用（这个类的处理方法）
    @ModelAttribute
    public void initUser(HttpSession session) {
        Object obj = session.getAttribute("ss");
        //session.getAttributeNames()
        authContext.set(obj.toString());
    }

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public Map<String, String> getResource() {
        Map<String, String> resource = new HashMap<String, String>();
        resource.put("resource", "here is some resource");
        resource.put("ss",authContext.get());
        return resource;
    }
}