package lk.ijse.moneylanding.model;

/**
 *
 * @author ~saku~
 */
public class Collector {
    private String nic;
    private String name;
    private String address;
    private String contact;
    private double salary;

    @Override
    public String toString() {
        return "Collector{" + "nic=" + nic + ", name=" + name + ", address=" + address + ", contact=" + contact + ", salary=" + salary + '}';
    }

    public Collector(String nic, String name, String address, String contact, double salary) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.salary = salary;
    }

    public Collector() {
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
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    } 
}
