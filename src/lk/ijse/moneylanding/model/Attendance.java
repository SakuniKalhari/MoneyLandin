package lk.ijse.moneylanding.model;

/**
 *
 * @author ~saku~
 */
public class Attendance {
    private String clid;
    private String date;
    private String attendance;

    @Override
    public String toString() {
        return "attendance{" + "clid=" + getClid() + ", date=" + getDate() + ", attendance=" + getAttendance() + '}';
    }

    public Attendance(String clid, String date, String attendance) {
        this.clid = clid;
        this.date = date;
        this.attendance = attendance;
    }

    public Attendance() {
    }

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

    /**
     * @return the attendance
     */
    public String getAttendance() {
        return attendance;
    }

    /**
     * @param attendance the attendance to set
     */
    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
