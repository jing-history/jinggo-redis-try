package wang.jinggo.domain.blog;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import wang.jinggo.base.XbootBaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author wangyj
 * @description
 * @create 2018-12-10 16:27
 **/
@Data
@ToString
@Entity
@Table(name = "blog_article", schema = "xboot", catalog = "")
@TableName("blog_article")
public class BlogArticle extends XbootBaseEntity {

    @ApiModelProperty(value = "主键")
    private String oId;
    private String articleTitle;
    private String articleAbstract;
    private String articleTags;
    private String articleAuthorEmail;
    private int articleCommentCount;
    private int articleViewCount;
    private String articleContent;
    private String articlePermalink;
    private String articleHadBeenPublished;
    private String articleIsPublished;
    private String articlePutTop;
    private Timestamp articleCreateDate;
    private Timestamp articleUpdateDate;
    private double articleRandomDouble;
    private String articleSignId;
    private String articleCommentable;
    private String articleViewPwd;
    private String articleEditorType;
}
