package lk.ijse.superHardware.dto.tm;

public class CustomerTm {
    private String id;

    private String name;

    private String address;

    private Integer contact;


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getContact() {
        return contact;
    }

    public CustomerTm(String id, String name, String address, int contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }
}
