package com.sdw.soft.demo.service;

import org.springframework.stereotype.Service;

/**
 * Created by shangyindong on 2016/7/18.
 */
@Service
public class MoniterService {


    @MoniterCache
    public void getCache(){
        System.out.println("getCache finished...");
    }
}
