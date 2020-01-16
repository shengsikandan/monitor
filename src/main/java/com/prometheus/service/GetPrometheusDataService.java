package com.prometheus.service;

import com.prometheus.Entry.CpuData;

import java.util.List;

/**
 * 获取Prometheus数据信息service层
 */
public interface GetPrometheusDataService {

    /**
     * 获取具体的json数据，并对数据进行相应的处理后进行返回
     * @return
     */
    List<CpuData> getValue();
}
