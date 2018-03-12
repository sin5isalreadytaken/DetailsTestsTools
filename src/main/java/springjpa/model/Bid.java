
package springjpa.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wenxiangzhou214164 on 2017/8/11.
 */
@Entity
@Table(name = "promotion_bid", schema = "market_test")
public class Bid {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 出价
     */
    private Double bid;

    /**
     * 位置
     */
    private Integer storeSectionId;

    @Column(insertable = false, updatable = false,
            columnDefinition = "DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createtime;

    @Column(insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatetime;

    public Bid() {}

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Integer getStoreSectionId() {
        return storeSectionId;
    }

    public void setStoreSectionId(Integer storeSectionId) {
        this.storeSectionId = storeSectionId;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }
}
