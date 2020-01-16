package com.prometheus.service;

import java.util.HashMap;

/**
 * 获取Prometheus数据信息service层
 */
public interface GetPrometheusDataService {

    /**
     * 获取具体的json数据，并对数据进行相应的处理后进行返回
     * @return
     */
    HashMap<String,Object> getValue();
}
