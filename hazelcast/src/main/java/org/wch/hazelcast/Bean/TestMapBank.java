package org.wch.hazelcast.Bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.*;
import com.netfinworks.recon.domainservice.recon.vo.BankGlide;
import org.wch.util.JSONUtil;

public class TestMapBank {
    public static void main(String[] args) throws Exception {

        ClientConfig clientConfig = new ClientConfig();
        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
        networkConfig.addAddress("10.5.6.34", "10.5.6.35");

        // clientConfig.addAddress("10.5.6.34:5701");
        // client初始化时会创建一系列service（线程池管理器、集群客户端服务、虚拟节点管理、动态扩展服务等），先启动ClientClusterServiceImpl，读取当前活动的实际节点（先根据clientConfig指定的地址获取connection，然后基于这个连接，再发起读取实际节点的请求），然后启动ClientPartitionServiceImpl，向各个实际活动节点发起请求获取其上的虚拟节点，记录到一个ConcurrentHashMap里。
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(clientConfig);
        // 这里的map并不是真的map，而是一个mapProxy
        // 并且这里要指定key和value的类型


        IMap<String, Object> mapCustomers = instance.getMap("bankGlideDatas");
        BankGlide glide = new BankGlide();
        glide.setBankDate("20160912");
        glide.setBatchNo("1");

        Map<String, String> map = new HashMap<>();
        map.put("bankDate", "20160913");
        map.put("name", "2");

        mapCustomers.put("2", map);
        System.out.println("--------- " + mapCustomers.size());
//        mapCustomers.values()
        Predicate predicate = Predicates.lessThan(QueryConstants.THIS_ATTRIBUTE_NAME.value()+".name", 11);

        for (Object key : mapCustomers.values(predicate)) {

            System.out.println("--------- " + JSONUtil.toJson(key));
//		System.out.println("--------- " + key+"   "+mapCustomers.get(key));
        }


    }
}

class MyPredicate implements Predicate<String, Map>, Serializable {

    @Override
    public boolean apply(Map.Entry<String, Map> mapEntry) {
        return (mapEntry.getValue().get("bankDate").equals("20160913"));
    }
}