package wang.jinggo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 笔记本实体类
 * @author wangyj
 * @description
 * @create 2018-08-14 19:07
 **/
@Entity
@Table(name = "tcnotebook")
public class NoteBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noteBook_id")
    private long noteBookId;

    @Column(name = "notebook_name")
    private String noteBookName;

    @Column(name = "text_sum")
    private int textSum;// 统计该笔记本下面有多少文本

    @ManyToOne(targetEntity = NoteBookGroup.class, fetch = FetchType.LAZY)
    @JoinColumn(name="notebook_group",referencedColumnName = "notebook_group_id")
    private NoteBookGroup noteBookGroup;

    @Column(name = "flag")
    private Integer flag;

    @Column(name = "create_date")
    private Date createdate;

    public long getNoteBookId() {
        return noteBookId;
    }

    public void setNoteBookId(long noteBookId) {
        this.noteBookId = noteBookId;
    }

    public String getNoteBookName() {
        return noteBookName;
    }

    public void setNoteBookName(String noteBookName) {
        this.noteBookName = noteBookName;
    }

    public int getTextSum() {
        return textSum;
    }

    public void setTextSum(int textSum) {
        this.textSum = textSum;
    }

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

    public NoteBookGroup getNoteBookGroupVO() {
        return noteBookGroup;
    }

    public void setNoteBookGroupVO(NoteBookGroup noteBookGroupVO) {
        this.noteBookGroup = noteBookGroupVO;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "noteBookId=" + noteBookId +
                ", noteBookName='" + noteBookName + '\'' +
                ", textSum=" + textSum +
                ", noteBookGroup=" + noteBookGroup +
                ", flag=" + flag +
                ", createdate=" + createdate +
                '}';
    }
}
