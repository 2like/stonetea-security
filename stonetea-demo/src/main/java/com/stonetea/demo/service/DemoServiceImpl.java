package com.stonetea.demo.service;

import com.stonetea.apistore.demo.DemoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;


/**
 * Created by zhubaojun on 2018/4/10.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
