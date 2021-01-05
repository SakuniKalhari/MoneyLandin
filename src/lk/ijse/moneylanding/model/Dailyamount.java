package lk.ijse.moneylanding.model;

/**
 *
 * @author ~saku~
 */
public class Dailyamount {
    private String clid;
    private String cid;
    private double dailyamount;
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
    public double getDailyamount() {
        return dailyamount;
    }

    /**
     * @param dailyamount the dailyamount to set
     */
    public void setDailyamount(double dailyamount) {
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

    public Dailyamount() {
    }

    public Dailyamount(String clid, String cid, double dailyamount, String date) {
        this.clid = clid;
        this.cid = cid;
        this.dailyamount = dailyamount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Dailyamount{" + "clid=" + clid + ", cid=" + cid + ", dailyamount=" + dailyamount + ", date=" + date + '}';
    }
}
