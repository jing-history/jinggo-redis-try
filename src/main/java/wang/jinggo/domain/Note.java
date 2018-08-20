package wang.jinggo.domain;

import wang.jinggo.annation.RedisCache;
import wang.jinggo.annation.RedisFieldNotCache;
import wang.jinggo.annation.RedisQuery;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * 笔记实体类
 * @author wangyj
 * @description
 * @create 2018-08-14 18:55
 **/
@Entity
@Table(name = "tcnote")
@RedisCache
public class Note implements Serializable {

    @RedisFieldNotCache
    private static final String className = "Note";
    @RedisFieldNotCache
    private static final String primaryKey = "noteId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private long noteId;

    @Column(name = "note_name")
    private String noteName;// 笔记名称

    @RedisQuery
    @Column(name = "author_name")
    private String authorName;// 作者名称

    @RedisQuery
    @Column(name = "from_url")
    private String fromUrl;// 文本来源
//    @Column(name = "content")
//    private String content;// 文本内容

    @ManyToOne(targetEntity = NoteBook.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="noteBook", referencedColumnName = "noteBook_id")
    private NoteBook noteBook;// 笔记本id

    @ManyToOne(targetEntity = NoteBookGroup.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="noteBookGroup",referencedColumnName = "notebook_group_id")
    private NoteBookGroup noteBookGroup;// 笔记本组

    @RedisQuery
    @Column(name = "flag")
    private Integer flag;// 放到BaseBean里面，反射获取不到field值

    @RedisQuery
    @Column(name = "create_date")
    private Date createdate;

    @RedisFieldNotCache
    @Column(name = "blob_content")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] blobContent;

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public byte[] getBlobContent() {
        return blobContent;
    }

    public void setBlobContent(byte[] blobContent) {
        this.blobContent = blobContent;
    }

    public NoteBook getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(NoteBook noteBook) {
        this.noteBook = noteBook;
    }

    public NoteBookGroup getNoteBookGroup() {
        return noteBookGroup;
    }

    public void setNoteBookGroup(NoteBookGroup noteBookGroup) {
        this.noteBookGroup = noteBookGroup;
    }

    @Override
    public String toString() {
        return "输出值==>id=" + noteId + " 笔记本名称：" + noteName + "   文本来源：" + fromUrl + "  作者名称:" + authorName;
    }
}
