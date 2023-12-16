package lk.ijse.superHardware.dto.tm;

public class EmployeeTm {
    private String emp_id;

    private String emp_name;

    private String address;

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmployeeTm(String emp_id, String emp_name, String address) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.address = address;
    }
}
