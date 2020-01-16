package com.prometheus.dao;


import com.prometheus.utils.GetHttpJsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

/**
 * 获取Prometheus数据信息dao层
 */
@Repository
@PropertySource("classpath:entry.properties")
public class GetPrometheusDataDao {

    //传输json数据url
    @Value("${prometheus.data_change_bydate_mock.url}")
    private String url;

    /**
     * 根据url获取具体的json数据并返回
     * @return
     */
    public String getValue(){
        String tempdata= GetHttpJsonUtil.HttpGet(url);
        return  tempdata;
    }
}