package lk.ijse.moneylanding.tableModel;

/**
 *
 * @author ~saku~
 */
public class CollectorTM {
    private String clid;
    private String nic;
    private String name;
    private String address;
    private String contact;
    private String salary;

    public CollectorTM() {
    }

    public CollectorTM(String clid ,String nic, String name, String address, String contact, String salary) {
        this.clid = clid;
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "collectorTM{" + "nic=" + getNic()+ ", name=" + getName() + ", address=" + getAddress() + ", contact=" + getContact() + ", salary=" + getSalary() + '}';
    }
    
    public String getClid() {
        return clid;
    }
    
    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }
    
}
