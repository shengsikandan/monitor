package com.prometheus.service.impl;

import com.prometheus.Entry.CpuData;
import com.prometheus.dao.GetPrometheusDataDao;
import com.prometheus.service.GetPrometheusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class GetPrometheusDataServiceImpl implements GetPrometheusDataService {
    @Autowired
    private GetPrometheusDataDao getPrometheusDataDao;

    private List<Map<String, Object>> valueList = new ArrayList<Map<String, Object>>();//单个数据情况集合

    private CpuData cpuData;

    private String cpu = "";//获取数据标识

    long addSeconds=0;//模拟时间增长

    public CpuData getValue(){
        return handleValue(getPrometheusDataDao.getValue());
    }

    /**
     * 处理json数据
     * @param jsonStr
     * @return
     */
    public CpuData handleValue(String jsonStr){
        JSONArray provinceArray;
        if (jsonStr.startsWith("[")&&jsonStr.endsWith("]")){
            provinceArray = JSONArray.fromObject(jsonStr);
        }else{
            provinceArray = JSONArray.fromObject("["+jsonStr+"]");
        }
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) provinceArray;
        cpuData = forMapInList(mapList);
        return cpuData;
    }

    /**
     * 进一步遍历json深层数据，并通过map集合进行返回
     * @param mapList
     * @return
     */
    public CpuData forMapInList(List<Map<String, Object>> mapList){
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> obj = mapList.get(i);
            for (Map.Entry<String, Object> entry : obj.entrySet()) {
                String strkey1 = entry.getKey();
                Object strval1 = entry.getValue();
                if (strkey1.equals("values")){
                    List<Object> list =(List<Object>) strval1;
                    valueList = forList(list,new ArrayList<Map<String, Object>>());
                    cpuData.setId(cpu);
                    cpuData.setValueList(valueList);
                    valueList=new ArrayList<Map<String, Object>>();
                    cpu="";
                }else if(strkey1.equals("cpu")){
                    cpu = strval1.toString();
                }else if(strval1 instanceof JSONArray||strval1 instanceof JSONObject){
                    handleValue(strval1.toString());
                }
            }
        }
        return  cpuData;
    }

    /**
     * 遍历key为Values的数组值，并通过list集合的方式进行返回
     * @param list
     * @param valueList
     * @return
     */
    public List<Map<String,Object>> forList(List<Object> list,List<Map<String,Object>> valueList){
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        for (Object object:list) {
            if (object instanceof JSONArray){
                List<Object> objectList =(List<Object>) object;
                forList(objectList,valueList);
            }else if(object instanceof Double){
                addSeconds=addSeconds+30;
                Double value = (Double) object;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dataStr = sdf.format(new Date(Long.valueOf(value.longValue()+addSeconds)*1000));
                hashMap.put("date",dataStr);
            }else if (object instanceof String){
                hashMap.put("fluctuation",(int)(Math.random()*100));
                valueList.add(hashMap);
            }
        }
        return valueList;
    }
}
