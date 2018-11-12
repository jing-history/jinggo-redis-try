package wang.jinggo.domain.es;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @description
 * @create 2018-11-12 17:04
 **/
@Document(indexName = "mybook",type = "book",shards = 1, replicas = 0,indexStoreType = "memory",refreshInterval = "-1")
public class MyBook {

    @Id
    private String id ;
    private String name;
    private  Long price;
    @Version
    private Long version;

    public MyBook() {
    }

    public MyBook(String id, String name, Long price, Long version) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.version = version;
    }

    @Field(type = FieldType.Nested)
    private Map<Integer,Collection<String>> buckets = new HashMap<>();

    public Map<Integer,Collection<String>> getBuckets(){
        return buckets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setBuckets(Map<Integer, Collection<String>> buckets) {
        this.buckets = buckets;
    }
}
