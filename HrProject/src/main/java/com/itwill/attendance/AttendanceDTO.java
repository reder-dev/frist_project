package com.itwill.attendance;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 출퇴근 및 근무 상태 DTO 클래스
 */
@Data
public class AttendanceDTO {

    private String attendance_id;        // 출퇴근 기록 ID (예: 250302250010001att)
    private Long emp_id;                 // 사원 ID

    private LocalDateTime checkInTime;   // 출근 시간
    private LocalDateTime checkOutTime;  // 퇴근 시간

    private boolean isBusinessTrip;      // 출장 여부 (오늘 기준)
    private boolean isAbsent;            // 결근 여부 (오늘 기준)

    private LocalDateTime createdAt;     // 기록 생성 시간
    private LocalDateTime updatedAt;     // 기록 수정 시간

    private String workStatus;           // 근무 상태 (출근, 퇴근, 출장, 결근, 미출근)

    /**
     * 오늘 날짜 기준으로 동적으로 근무 상태 계산
     * 우선순위: 출장 > 결근 > 퇴근 > 출근 > 미출근
     * @return 근무 상태 문자열
     */
    public String calculateWorkStatus() {
        LocalDate today = LocalDate.now();

        if (isBusinessTrip) {
            return "출장";
        }

        if (isAbsent) {
            return "결근";
        }

        if (checkOutTime != null && checkOutTime.toLocalDate().isEqual(today)) {
            return "퇴근";
        }

        if (checkInTime != null
                && checkInTime.toLocalDate().isEqual(today)
                && (checkOutTime == null || !checkOutTime.toLocalDate().isEqual(today))) {
            return "출근";
        }

        return "미출근";
    }

    /**
     * 기본 생성자
     */
    public AttendanceDTO() {
        // 기본 생성자에서도 workStatus를 자동 계산할 수 있지만, 보통 빈 생성자에서는 생략
    }

    /**
     * 모든 필드를 초기화하는 생성자 (workStatus 자동 설정 포함)
     */
    public AttendanceDTO(String attendance_id, Long emp_id,
                         LocalDateTime checkInTime, LocalDateTime checkOutTime,
                         boolean isBusinessTrip, boolean isAbsent,
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.attendance_id = attendance_id;
        this.emp_id = emp_id;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.isBusinessTrip = isBusinessTrip;
        this.isAbsent = isAbsent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.workStatus = calculateWorkStatus(); // 자동 계산
    }

    /**
     * 정적 팩토리 메서드: Entity → DTO 변환 시 사용하면 좋음
     */
    public static AttendanceDTO fromEntity(Attendance entity) {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setAttendance_id(entity.getAttendanceId());
        dto.setEmp_id(entity.getEmployeeId());
        dto.setCheckInTime(entity.getCheckInTime());
        dto.setCheckOutTime(entity.getCheckOutTime());
        dto.setBusinessTrip(entity.isBusinessTrip());
        dto.setAbsent(entity.isAbsent());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        // workStatus 자동 계산
        dto.setWorkStatus(dto.calculateWorkStatus());

        return dto;
    }
}
