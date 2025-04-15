package com.itwill.salary.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SalaryDetailDTO {
	private String salId; // 급여 ID (사번 + 연월)
	private String empId; // 사번
	private String empName; // 이름
	private String deptName; // 부서명
	private String salMonth; // 지급연월 (yyyyMM)
	private int totalPay; // 총 지급액
	private int totalDeductions; // 총 공제액
	private int netPay; // 실 수령액
	private String payDate; // 지급일자
	private String register; // 등록자 (급여 확정자 사번)

	private List<SalaryItemDTO> detailList; // 항목별 지급/공제 내역

	@Data
	public static class SalaryItemDTO {
		private String itemId; // 항목 코드
		private String itemName; // 항목명
		private String itemType; // 'P' or 'D'
		private BigDecimal amount; // 금액
		
		public SalaryItemDTO() {
	        // Jackson용 기본 생성자 (필수)
	    }

		public SalaryItemDTO(String itemId, String itemName, String itemType, BigDecimal amount) {
			this.itemId = itemId;
			this.itemName = itemName;
			this.itemType = itemType;
			this.amount = amount;
		}
	}
}