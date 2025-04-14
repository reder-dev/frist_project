package com.itwill.attendance.mapper;

import com.itwill.attendance.record.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AttendanceMapper {

    Attendance findByEmployeeIdAndDate(@Param("employeeId") String employeeId, @Param("date") LocalDate date);

    List<Attendance> findByEmployeeId(@Param("employeeId") String employeeId);

    List<Attendance> findByDate(@Param("date") LocalDate date);

    Attendance findById(@Param("id") Long id);

    void save(Attendance attendance);

    void update(Attendance attendance);
}
