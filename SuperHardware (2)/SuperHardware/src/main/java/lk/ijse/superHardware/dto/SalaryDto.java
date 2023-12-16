package lk.ijse.superHardware.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryDto {

    private String salary_id;

    private String emp_id;

    private Double salary_amount;

    private Date date;
}

