package wang.jinggo.domain.es;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @author wangyj
 * @description
 * @create 2018-10-05 17:52
 **/
@Data
@ToString
@Document(indexName = "product", type = "book")
public class Book {

    @Id
    String id;
    String name;
    String message;
    Date postDate;

}
