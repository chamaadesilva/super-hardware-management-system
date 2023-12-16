package lk.ijse.superHardware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto  {
    private String emp_id;

    private String emp_name;

    private String address;

    public String getEmp_id() {
        return emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getAddress() {
        return address;
    }
}
