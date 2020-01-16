package com.prometheus.dao;


import com.prometheus.utils.GetHttpJsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:entry.properties")
public class GetPrometheusDataDao {

    @Value("${prometheus.data_change_bydate_mock.url}")
    private String url;

    public String getValue(){
        String tempdata= GetHttpJsonUtil.HttpGet(url);
        return  tempdata;
    }
}