package lk.ijse.superHardware.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

public class CustomerDto {
    private String cust_id;

    private String cust_name;

    private String address;

    private int contact;

    public String getCust_id() {

        return cust_id;
    }

    public String getCust_name() {

        return cust_name;
    }

    public String getAddress() {

        return address;
    }

    public int getContact() {

        return contact;
    }
}