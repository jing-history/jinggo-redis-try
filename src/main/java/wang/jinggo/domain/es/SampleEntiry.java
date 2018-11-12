package wang.jinggo.domain.es;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

/**
 * @author wangyj
 * @description
 * @create 2018-11-12 15:35
 **/
@Document(indexName = "sample", type = "msg")
public class SampleEntiry {

    @Id
    private String id;
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
