package com.stonetea.webserver.rest;

import com.stonetea.apistore.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
    public class HelloController {

        private Logger logger = LoggerFactory.getLogger(HelloController.class);

    ThreadLocal<String> authContext = new ThreadLocal<>();

    //  此方法会在每次请求前调用（这个类的处理方法）
    @ModelAttribute
    public void initSession(HttpSession session) {
        Object obj = session.getAttribute("ss");
        authContext.set(obj.toString());
    }
        @Autowired
        DemoService demoService;
        /**
         * 测试hello
         * @return
         */
        @RequestMapping("/hello")
        public String hello() {
            //String name = "jack";
            return demoService.hello(authContext.get());
        }

        //@RequestMapping("/")
        public String index() {
            return "index";
        }

        //@RequestMapping("/login")
        public String login() {
            return "login";
        }

}