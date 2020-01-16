package com.prometheus.controller;

import com.prometheus.service.GetPrometheusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 获取Prometheus数据信息controller层
 */
@Controller
@RequestMapping("/prometheus")
public class GetPrometheusDataController {

    @Autowired
    private GetPrometheusDataService getPrometheusDataService;

    /**
     * 获取具体数据信息并以json格式进行返回
     * @return
     */
    @ResponseBody
    @GetMapping("/getJson")
    public HashMap<String,Object> getJson(){
        HashMap<String,Object> hashMap = getPrometheusDataService.getValue();
        return hashMap;
    }
}
