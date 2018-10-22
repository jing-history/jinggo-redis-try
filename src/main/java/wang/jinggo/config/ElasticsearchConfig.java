package wang.jinggo.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wangyj
 * @description
 * @create 2018-10-19 9:59
 **/
//@Configuration
public class ElasticsearchConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchConfig.class);
    /**
     * elk集群地址
     */
    @Value("${elasticsearch.ip}")
    private String hostName;
    /**
     * 端口
     */
    @Value("${elasticsearch.port}")
    private String port;
    /**
     * 集群名称
     */
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    /**
     * 连接池
     */
    @Value("${elasticsearch.pool}")
    private String poolSize;

    @Bean
    public TransportClient transportClient() {
        LOGGER.info("============ES初始化开始================");
        TransportClient transportClient = null;

        try {
            LOGGER.info("ip:" + hostName);
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
                    .put("cluster.name", clusterName)
                    .build();
            //配置信息Settings自定义,下面设置为EMPTY
            transportClient = new PreBuiltTransportClient(esSetting);
        //    addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("hostName"), Integer.valueOf(9300)));
            TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port));
            transportClient.addTransportAddress(transportAddress);
        } catch (Exception e) {
            LOGGER.error("elasticsearch TransportClient create error!!!", e);
        }
        LOGGER.info("ES初始化完毕");
        return transportClient;
    }
}
