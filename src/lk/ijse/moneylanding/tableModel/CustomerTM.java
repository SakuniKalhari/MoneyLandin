package lk.ijse.moneylanding.tableModel;

/**
 *
 * @author ~saku~
 */
public class CustomerTM {
    private String cid;
    private String nic;
    private String name;
    private String address;
    private String contact;
    private String amount;
    private String date;

    public CustomerTM(String nic,String cid, String name, String address, String contact, String amount, String date) {
        this.nic = nic;
        this.cid=cid;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.amount = amount;
        this.date = date;
    }
    
    public String getCid(){
        return cid;
    }
    
    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "customerTM{" + "nic=" + nic + ", name=" + name + ", address=" + address + ", contact=" + contact + ", amount=" + amount + ", date=" + date + '}';
    }
    
}
