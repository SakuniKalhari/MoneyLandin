package lk.ijse.moneylanding.tableModel;

/**
 *
 * @author ~saku~
 */
public class AttendanceTM {
    private String clid;
    private String date;
    private String attendance;

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

    public AttendanceTM() {
    }

    public AttendanceTM(String clid, String date, String attendance) {
        this.clid = clid;
        this.date = date;
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "attendanceTM{" + "clid=" + clid + ", date=" + date + ", attendance=" + attendance + '}';
    }  
}
