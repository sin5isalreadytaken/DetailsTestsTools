package news_top_server;

/**
 * Created by byzuse
 * datetime: 2017/2/7 17:38.
 */
public class RawNewsItem {
    private String docId;
    private String oid;
    /**
     * ct - 新闻创建时间
     */
    private long createTime;
    /**
     * ds - 详情地址
     */
    private String dataSource;
    /**
     * 媒体账号
     */
    private String media;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 子分类
     */
    private String subChannel;

    /**
     * 父分类
     */
    private String channel;

    /**
     * dj - 一小时内推荐pv
     */
    private long recomClick;
    /**
     * tj - 一小时推荐ev
     */
    private long recomExpose;
    /**
     * 1小时内收藏
     */
    private long collect;
    /**
     * 1小时内评论
     */
    private long comment;
    /**
     * 一小时内客户端总pv
     */
    private long formalClick;
    /**
     * 一小时内客户端总ev
     */
    private long formalExpose;
    /**
     * 一小时内分享
     */
    private long share;

    /**
     * 新闻总收藏
     */
    private long totalCollect;
    /**
     * 新闻总评论
     */
    private long totalComment;
    /**
     * 新闻总客户端PV
     */
    private long totalFormalClick;
    /**
     * 新闻总客户端曝光EV
     */
    private long totalFormalExpose;
    /**
     * 新闻总推荐pv
     */
    private long totalRecomClick;
    /**
     * 新闻总推荐ev
     */
    private long totalRecomExpose;
    /**
     * 新闻总分享
     */
    private long totalShare;

    private NewsScoreItem scoreItem;

    @Override
    public String toString() {
        return "RawNewsItem{" +
                "docId='" + docId + '\'' +
                ", oid='" + oid + '\'' +
                ", createTime=" + createTime +
                ", dataSource='" + dataSource + '\'' +
                ", media='" + media + '\'' +
                ", title='" + title + '\'' +
                ", subChannel='" + subChannel + '\'' +
                ", recomClick=" + recomClick +
                ", recomExpose=" + recomExpose +
                ", collect=" + collect +
                ", comment=" + comment +
                ", formalClick=" + formalClick +
                ", formalExpose=" + formalExpose +
                ", share=" + share +
                ", totalCollect=" + totalCollect +
                ", totalComment=" + totalComment +
                ", totalFormalClick=" + totalFormalClick +
                ", totalFormalExpose=" + totalFormalExpose +
                ", totalRecomClick=" + totalRecomClick +
                ", totalRecomExpose=" + totalRecomExpose +
                ", totalShare=" + totalShare +
                '}';
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }

    public String getChannel() {
        if (channel == null || channel.isEmpty()) {
            if (subChannel != null && subChannel.length() > 4) {
                channel = subChannel.replaceAll("\\d{4}$", "0000");
            } else {
                channel = "-1";
            }
        }
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public long getRecomClick() {
        return recomClick;
    }

    public void setRecomClick(long recomClick) {
        this.recomClick = recomClick;
    }

    public long getRecomExpose() {
        return recomExpose;
    }

    public void setRecomExpose(long recomExpose) {
        this.recomExpose = recomExpose;
    }

    public long getCollect() {
        return collect;
    }

    public void setCollect(long collect) {
        this.collect = collect;
    }

    public long getComment() {
        return comment;
    }

    public void setComment(long comment) {
        this.comment = comment;
    }

    public long getFormalClick() {
        return formalClick;
    }

    public void setFormalClick(long formalClick) {
        this.formalClick = formalClick;
    }

    public long getFormalExpose() {
        return formalExpose;
    }

    public void setFormalExpose(long formalExpose) {
        this.formalExpose = formalExpose;
    }

    public long getShare() {
        return share;
    }

    public void setShare(long share) {
        this.share = share;
    }

    public long getTotalCollect() {
        return totalCollect;
    }

    public void setTotalCollect(long totalCollect) {
        this.totalCollect = totalCollect;
    }

    public long getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(long totalComment) {
        this.totalComment = totalComment;
    }

    public long getTotalFormalClick() {
        return totalFormalClick;
    }

    public void setTotalFormalClick(long totalFormalClick) {
        this.totalFormalClick = totalFormalClick;
    }

    public long getTotalFormalExpose() {
        return totalFormalExpose;
    }

    public void setTotalFormalExpose(long totalFormalExpose) {
        this.totalFormalExpose = totalFormalExpose;
    }

    public long getTotalRecomClick() {
        return totalRecomClick;
    }

    public void setTotalRecomClick(long totalRecomClick) {
        this.totalRecomClick = totalRecomClick;
    }

    public long getTotalRecomExpose() {
        return totalRecomExpose;
    }

    public void setTotalRecomExpose(long totalRecomExpose) {
        this.totalRecomExpose = totalRecomExpose;
    }

    public long getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(long totalShare) {
        this.totalShare = totalShare;
    }

    public NewsScoreItem getScoreItem() {
        return scoreItem;
    }

    public void setScoreItem(NewsScoreItem scoreItem) {
        this.scoreItem = scoreItem;
    }
}
