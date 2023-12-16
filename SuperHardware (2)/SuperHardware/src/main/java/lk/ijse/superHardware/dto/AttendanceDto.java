package lk.ijse.superHardware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class AttendanceDto {
    private String attendance_id;

    private String employee_id;

    private Date date;

    private Time time;

}

