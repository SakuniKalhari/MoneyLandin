package lk.ijse.moneylanding.model;

/**
 *
 * @author ~saku~
 */
public class Customer {
    private String nic;
    private String name;
    private String address;
    private String contact;
    private double amount;
    private String date;


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
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
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

    public Customer( String nic, String name, String address, String contact, double amount, String date) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.amount = amount;
        this.date = date;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +  ", nic=" + nic + ", name=" + name + ", address=" + address + ", contact=" + contact + ", amount=" + amount + ", date=" + date + '}';
    }

    
}
