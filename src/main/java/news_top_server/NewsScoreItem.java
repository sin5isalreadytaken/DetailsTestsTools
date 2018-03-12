package news_top_server;

import java.io.Serializable;

/**
 * Created by byzuse
 * datetime: 2017/3/16 17:19.
 * 新闻打分模型
 */
public class NewsScoreItem implements Serializable {
    private static final long serialVersionUID = 8684269897179560260L;

    private double shareNormal;
    private double totalShareNormal;
    private double recomClickNormal;
    private double formalClickNormal;
    private double ctr;
    private double createTimeScore;
    private double shareClickScore;

    private double normalScore;
    private double ctrScore;

    public double getShareNormal() {
        return shareNormal;
    }

    public void setShareNormal(double shareNormal) {
        this.shareNormal = shareNormal;
    }

    public double getTotalShareNormal() {
        return totalShareNormal;
    }

    public void setTotalShareNormal(double totalShareNormal) {
        this.totalShareNormal = totalShareNormal;
    }

    public double getRecomClickNormal() {
        return recomClickNormal;
    }

    public void setRecomClickNormal(double recomClickNormal) {
        this.recomClickNormal = recomClickNormal;
    }

    public double getFormalClickNormal() {
        return formalClickNormal;
    }

    public void setFormalClickNormal(double formalClickNormal) {
        this.formalClickNormal = formalClickNormal;
    }

    public double getCtr() {
        return ctr;
    }

    public void setCtr(double ctr) {
        this.ctr = ctr;
    }

    public double getCreateTimeScore() {
        return createTimeScore;
    }

    public void setCreateTimeScore(double createTimeScore) {
        this.createTimeScore = createTimeScore;
    }

    public double getShareClickScore() {
        return shareClickScore;
    }

    public void setShareClickScore(double shareClickScore) {
        this.shareClickScore = shareClickScore;
    }

    public double getCtrScore() {
        return ctrScore;
    }

    public void setCtrScore(double ctrScore) {
        this.ctrScore = ctrScore;
    }

    public double getNormalScore() {
        return normalScore;
    }

    public void setNormalScore(double normalScore) {
        this.normalScore = normalScore;
    }
}
