package wang.jinggo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 笔记本组实体类
 * @author wangyj
 * @description
 * @create 2018-08-14 19:07
 **/
@Entity
@Table(name = "tcnotebookgroup")
public class NoteBookGroup implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notebook_group_id")
    private long noteBookGroupId;

    @Column(name = "notebook_group_name")
    private String noteBookGroupName;

    @Column(name = "text_sum")
    private int textSum;// 统计该笔记本组下面有多少文本

    @Column(name = "flag")
    private Integer flag;

    @Column(name = "create_date")
    private Date createdate;

    public long getNoteBookGroupId() {
        return noteBookGroupId;
    }

    public void setNoteBookGroupId(long noteBookGroupId) {
        this.noteBookGroupId = noteBookGroupId;
    }

    public String getNoteBookGroupName() {
        return noteBookGroupName;
    }

    public void setNoteBookGroupName(String noteBookGroupName) {
        this.noteBookGroupName = noteBookGroupName;
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

    @Override
    public String toString() {
        return "NoteBookGroup{" +
                "noteBookGroupId=" + noteBookGroupId +
                ", noteBookGroupName='" + noteBookGroupName + '\'' +
                ", textSum=" + textSum +
                ", flag=" + flag +
                ", createdate=" + createdate +
                '}';
    }
}
