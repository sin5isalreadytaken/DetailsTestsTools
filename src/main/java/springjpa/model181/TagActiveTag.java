package springjpa.model181;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tag_active_tag", schema = "NR", catalog = "")
public class TagActiveTag {
    private int tagId;
    private String tagName;
    private byte permanent;
    private byte active;
    private int stage;
    private Timestamp updateTime;
    private Timestamp createTime;

    @Id
    @Column(name = "tagId")
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "tagName")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Basic
    @Column(name = "permanent")
    public byte getPermanent() {
        return permanent;
    }

    public void setPermanent(byte permanent) {
        this.permanent = permanent;
    }

    @Basic
    @Column(name = "active")
    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Basic
    @Column(name = "stage")
    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagActiveTag that = (TagActiveTag) o;

        if (tagId != that.tagId) return false;
        if (permanent != that.permanent) return false;
        if (active != that.active) return false;
        if (stage != that.stage) return false;
        if (tagName != null ? !tagName.equals(that.tagName) : that.tagName != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        result = 31 * result + (int) permanent;
        result = 31 * result + (int) active;
        result = 31 * result + stage;
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
