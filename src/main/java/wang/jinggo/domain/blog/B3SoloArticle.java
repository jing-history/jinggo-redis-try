package wang.jinggo.domain.blog;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 博客实体 基于b3log
 * @author wangyj
 * @description
 * @create 2018-12-06 10:38
 **/
@ToString
@Entity
@Table(name = "b3_solo_article")
@TableName("b3_solo_article")
public class B3SoloArticle implements Serializable {

    @ApiModelProperty(value = "主键")
    private String oId;
    @ApiModelProperty(value = "文章标题")
    private String articleTitle;
    @ApiModelProperty(value = "文章摘要")
    private String articleAbstract;
    @ApiModelProperty(value = "文章标签，英文逗号分隔")
    private String articleTags;
    @ApiModelProperty(value = "文章作者邮箱")
    private String articleAuthorEmail;
    @ApiModelProperty(value = "文章评论计数")
    private int articleCommentCount;
    @ApiModelProperty(value = "文章浏览计数")
    private int articleViewCount;
    @ApiModelProperty(value = "文章正文内容")
    private String articleContent;
    @ApiModelProperty(value = "文章访问路径")
    private String articlePermalink;
    @ApiModelProperty(value = "文章是否已经发布过")
    private String articleHadBeenPublished;
    @ApiModelProperty(value = "文章是否处于已发布状态")
    private String articleIsPublished;
    @ApiModelProperty(value = "文章是否置顶")
    private String articlePutTop;
    @ApiModelProperty(value = "文章创建时间")
    private Timestamp articleCreateDate;
    @ApiModelProperty(value = "文章更新时间")
    private Timestamp articleUpdateDate;
    @ApiModelProperty(value = "文章随机数，用于快速查询随机文章列表")
    private double articleRandomDouble;
    @ApiModelProperty(value = "文章关联的签名档 id")
    private String articleSignId;
    @ApiModelProperty(value = "文章是否可以评论")
    private String articleCommentable;
    @ApiModelProperty(value = "文章浏览密码，留空为不设置访问密码")
    private String articleViewPwd;
    @ApiModelProperty(value = "文章编辑器类型，目前仅支持 CodeMirror-Markdown")
    private String articleEditorType;

    @Id
    @Column(name = "oId", nullable = false, length = 19)
    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    @Basic
    @Column(name = "articleTitle", nullable = false, length = 255)
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Basic
    @Column(name = "articleAbstract", nullable = false, length = -1)
    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    @Basic
    @Column(name = "articleTags", nullable = false, length = -1)
    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    @Basic
    @Column(name = "articleAuthorEmail", nullable = false, length = 255)
    public String getArticleAuthorEmail() {
        return articleAuthorEmail;
    }

    public void setArticleAuthorEmail(String articleAuthorEmail) {
        this.articleAuthorEmail = articleAuthorEmail;
    }

    @Basic
    @Column(name = "articleCommentCount", nullable = false)
    public int getArticleCommentCount() {
        return articleCommentCount;
    }

    public void setArticleCommentCount(int articleCommentCount) {
        this.articleCommentCount = articleCommentCount;
    }

    @Basic
    @Column(name = "articleViewCount", nullable = false)
    public int getArticleViewCount() {
        return articleViewCount;
    }

    public void setArticleViewCount(int articleViewCount) {
        this.articleViewCount = articleViewCount;
    }

    @Basic
    @Column(name = "articleContent", nullable = false, length = -1)
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Basic
    @Column(name = "articlePermalink", nullable = false, length = 255)
    public String getArticlePermalink() {
        return articlePermalink;
    }

    public void setArticlePermalink(String articlePermalink) {
        this.articlePermalink = articlePermalink;
    }

    @Basic
    @Column(name = "articleHadBeenPublished", nullable = false, length = 1)
    public String getArticleHadBeenPublished() {
        return articleHadBeenPublished;
    }

    public void setArticleHadBeenPublished(String articleHadBeenPublished) {
        this.articleHadBeenPublished = articleHadBeenPublished;
    }

    @Basic
    @Column(name = "articleIsPublished", nullable = false, length = 1)
    public String getArticleIsPublished() {
        return articleIsPublished;
    }

    public void setArticleIsPublished(String articleIsPublished) {
        this.articleIsPublished = articleIsPublished;
    }

    @Basic
    @Column(name = "articlePutTop", nullable = false, length = 1)
    public String getArticlePutTop() {
        return articlePutTop;
    }

    public void setArticlePutTop(String articlePutTop) {
        this.articlePutTop = articlePutTop;
    }

    @Basic
    @Column(name = "articleCreateDate", nullable = false)
    public Timestamp getArticleCreateDate() {
        return articleCreateDate;
    }

    public void setArticleCreateDate(Timestamp articleCreateDate) {
        this.articleCreateDate = articleCreateDate;
    }

    @Basic
    @Column(name = "articleUpdateDate", nullable = false)
    public Timestamp getArticleUpdateDate() {
        return articleUpdateDate;
    }

    public void setArticleUpdateDate(Timestamp articleUpdateDate) {
        this.articleUpdateDate = articleUpdateDate;
    }

    @Basic
    @Column(name = "articleRandomDouble", nullable = false, precision = 0)
    public double getArticleRandomDouble() {
        return articleRandomDouble;
    }

    public void setArticleRandomDouble(double articleRandomDouble) {
        this.articleRandomDouble = articleRandomDouble;
    }

    @Basic
    @Column(name = "articleSignId", nullable = false, length = 19)
    public String getArticleSignId() {
        return articleSignId;
    }

    public void setArticleSignId(String articleSignId) {
        this.articleSignId = articleSignId;
    }

    @Basic
    @Column(name = "articleCommentable", nullable = false, length = 1)
    public String getArticleCommentable() {
        return articleCommentable;
    }

    public void setArticleCommentable(String articleCommentable) {
        this.articleCommentable = articleCommentable;
    }

    @Basic
    @Column(name = "articleViewPwd", nullable = false, length = 100)
    public String getArticleViewPwd() {
        return articleViewPwd;
    }

    public void setArticleViewPwd(String articleViewPwd) {
        this.articleViewPwd = articleViewPwd;
    }

    @Basic
    @Column(name = "articleEditorType", nullable = true, length = 20)
    public String getArticleEditorType() {
        return articleEditorType;
    }

    public void setArticleEditorType(String articleEditorType) {
        this.articleEditorType = articleEditorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        B3SoloArticle that = (B3SoloArticle) o;

        if (articleCommentCount != that.articleCommentCount) return false;
        if (articleViewCount != that.articleViewCount) return false;
        if (Double.compare(that.articleRandomDouble, articleRandomDouble) != 0) return false;
        if (oId != null ? !oId.equals(that.oId) : that.oId != null) return false;
        if (articleTitle != null ? !articleTitle.equals(that.articleTitle) : that.articleTitle != null) return false;
        if (articleAbstract != null ? !articleAbstract.equals(that.articleAbstract) : that.articleAbstract != null)
            return false;
        if (articleTags != null ? !articleTags.equals(that.articleTags) : that.articleTags != null) return false;
        if (articleAuthorEmail != null ? !articleAuthorEmail.equals(that.articleAuthorEmail) : that.articleAuthorEmail != null)
            return false;
        if (articleContent != null ? !articleContent.equals(that.articleContent) : that.articleContent != null)
            return false;
        if (articlePermalink != null ? !articlePermalink.equals(that.articlePermalink) : that.articlePermalink != null)
            return false;
        if (articleHadBeenPublished != null ? !articleHadBeenPublished.equals(that.articleHadBeenPublished) : that.articleHadBeenPublished != null)
            return false;
        if (articleIsPublished != null ? !articleIsPublished.equals(that.articleIsPublished) : that.articleIsPublished != null)
            return false;
        if (articlePutTop != null ? !articlePutTop.equals(that.articlePutTop) : that.articlePutTop != null)
            return false;
        if (articleCreateDate != null ? !articleCreateDate.equals(that.articleCreateDate) : that.articleCreateDate != null)
            return false;
        if (articleUpdateDate != null ? !articleUpdateDate.equals(that.articleUpdateDate) : that.articleUpdateDate != null)
            return false;
        if (articleSignId != null ? !articleSignId.equals(that.articleSignId) : that.articleSignId != null)
            return false;
        if (articleCommentable != null ? !articleCommentable.equals(that.articleCommentable) : that.articleCommentable != null)
            return false;
        if (articleViewPwd != null ? !articleViewPwd.equals(that.articleViewPwd) : that.articleViewPwd != null)
            return false;
        if (articleEditorType != null ? !articleEditorType.equals(that.articleEditorType) : that.articleEditorType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = oId != null ? oId.hashCode() : 0;
        result = 31 * result + (articleTitle != null ? articleTitle.hashCode() : 0);
        result = 31 * result + (articleAbstract != null ? articleAbstract.hashCode() : 0);
        result = 31 * result + (articleTags != null ? articleTags.hashCode() : 0);
        result = 31 * result + (articleAuthorEmail != null ? articleAuthorEmail.hashCode() : 0);
        result = 31 * result + articleCommentCount;
        result = 31 * result + articleViewCount;
        result = 31 * result + (articleContent != null ? articleContent.hashCode() : 0);
        result = 31 * result + (articlePermalink != null ? articlePermalink.hashCode() : 0);
        result = 31 * result + (articleHadBeenPublished != null ? articleHadBeenPublished.hashCode() : 0);
        result = 31 * result + (articleIsPublished != null ? articleIsPublished.hashCode() : 0);
        result = 31 * result + (articlePutTop != null ? articlePutTop.hashCode() : 0);
        result = 31 * result + (articleCreateDate != null ? articleCreateDate.hashCode() : 0);
        result = 31 * result + (articleUpdateDate != null ? articleUpdateDate.hashCode() : 0);
        temp = Double.doubleToLongBits(articleRandomDouble);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (articleSignId != null ? articleSignId.hashCode() : 0);
        result = 31 * result + (articleCommentable != null ? articleCommentable.hashCode() : 0);
        result = 31 * result + (articleViewPwd != null ? articleViewPwd.hashCode() : 0);
        result = 31 * result + (articleEditorType != null ? articleEditorType.hashCode() : 0);
        return result;
    }
}
