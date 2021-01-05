package lk.ijse.moneylanding.tableModel;

/**
 *
 * @author ~saku~
 */
public class AmountTM {
    private String clid;
    private String cid;
    private String dailyamount;
    private String date;

    /**
     * @return the clid
     */
    public String getClid() {
        return clid;
    }

    /**
     * @param clid the clid to set
     */
    public void setClid(String clid) {
        this.clid = clid;
    }

    /**
     * @return the cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * @return the dailyamount
     */
    public String getDailyamount() {
        return dailyamount;
    }

    /**
     * @param dailyamount the dailyamount to set
     */
    public void setDailyamount(String dailyamount) {
        this.dailyamount = dailyamount;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AmountTM{" + "clid=" + clid + ", cid=" + cid + ", dailyamount=" + dailyamount + ", date=" + date + '}';
    }

    public AmountTM(String clid, String cid, String dailyamount, String date) {
        this.clid = clid;
        this.cid = cid;
        this.dailyamount = dailyamount;
        this.date = date;
    }
    public AmountTM(String dailyamount, String date) {
        this.clid =null;
        this.cid =null;
        this.dailyamount = dailyamount;
        this.date = date;
    }
    

    public AmountTM() {
    }

}
