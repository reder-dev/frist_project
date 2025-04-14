<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	com.itwill.salary.dto.SalaryEmployeeDTO loginUser = new com.itwill.salary.dto.SalaryEmployeeDTO();
	//loginUser.setEmpId("22100003"); // 최아영
	//loginUser.setEmpId("10100001");
	loginUser.setEmpId("15100002");
	session.setAttribute("loginUser", loginUser);
%>

<html>
<head>
  <title>급여 명세서 조회</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <style>
  <style>
    @font-face {
      font-family: 'Pretendard';
      src: url('/resources/fonts/Pretendard-Regular.otf') format('opentype');
    }

    html, body {
      font-family: 'Pretendard', sans-serif;
    }
  
    body {
      font-family: Arial, sans-serif;
      background-color: #f9f9f9;
      margin: 0;
      padding: 0;
    }

    .container {
      max-width: 900px;
      margin: 20px auto;
      background-color: white;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.05);
    }

    h2 {
      text-align: center;
      margin-bottom: 20px;
      font-size: 1.5rem;
    }

    .controls {
      text-align: center;
      margin-bottom: 15px;
    }

    select, button {
      padding: 5px 10px;
      margin: 5px;
      font-size: 1rem;
    }

    .table-wrapper {
      overflow-x: auto;
      margin-top: 15px;
    }

    table {
      border-collapse: collapse;
      width: 100%;
      min-width: 600px;
    }

    th, td {
      border: 1px solid #999;
      padding: 8px;
      text-align: center;
    }

    .readonly-box {
      display: inline-block;
      min-width: 100px;
    }

    .button-group {
      margin-top: 30px;
      text-align: center;
    }

    .button-group button {
      margin: 5px;
      padding: 8px 20px;
      font-size: 1rem;
    }

    .btn-icon {
      width: 20px !important;
      height: 20px !important;
      vertical-align: middle;
      margin-right: 6px;
      object-fit: contain;
    }

    .net-row td {
      border: none !important;
      font-weight: bold;
      background-color: #fff;
    }

    @media screen and (max-width: 600px) {
      h2 {
        font-size: 1.2rem;
      }

      .controls {
        display: flex;
        flex-direction: column;
        align-items: center;
      }

      select, button {
        width: 100%;
        margin: 5px 0;
      }

      .container {
        padding: 15px;
      }
    }
  </style>
</head>
<body>

<div class="container">

  <h2>급여명세서 조회</h2>

  <!-- 연월 선택 -->
  <div class="controls">
    <label>연도:</label>
    <select id="yearSelect"></select>

    <label>월:</label>
    <select id="monthSelect"></select>

    <button id="loadBtn" style="padding: 7px 16px;
		  font-size: 15px;
		  background: #f0f0f0;
		  color: black;
		  border: none;
		  border-radius: 5px;
		  cursor: pointer;">조회하기</button>
  </div>

  <!-- 사원 기본 정보 -->
  <div class="table-wrapper">
    <table>
      <tr>
        <td>이름</td>
        <td><span id="empName" class="readonly-box"></span></td>
        <td>부서</td>
        <td><span id="deptName" class="readonly-box"></span></td>
      </tr>
      <tr>
        <td>지급일</td>
        <td><span id="payDate" class="readonly-box"></span></td>
        <td>산정기간</td>
        <td><span id="workRange" class="readonly-box"></span></td>
      </tr>
    </table>
  </div>

  <!-- 급여 상세 테이블 -->
  <div class="table-wrapper">
    <table id="salaryTable">
      <thead>
        <tr>
          <th>지급항목</th>
          <th>금액(원)</th>
          <th>공제항목</th>
          <th>금액(원)</th>
        </tr>
      </thead>
      <tbody id="tableBody">
      </tbody>
      <tfoot>
        <tr>
          <td>지급총액</td>
          <td><span id="totalPay"></span> 원</td>
          <td>공제총액</td>
          <td><span id="totalDeduct"></span> 원</td>
        </tr>
        <tr class="net-row">
          <td colspan="3" style="text-align: right;">실지급액</td>
          <td><span id="netPay"></span> 원</td>
        </tr>
      </tfoot>
    </table>
  </div>

  <!-- 다운로드 버튼 -->
  <div class="button-group">
    <button type="button" id="excelBtn" onclick="downloadExcel()" disabled 
    	style="background-color: #007bff; color: white; border: none; padding: 10px 20px; border-radius: 5px;
		  cursor: pointer;">
		  
      <img src="/resources/img/excel-icon.png" class="btn-icon" />
      엑셀 다운로드
    </button>
    <button type="button" id="pdfBtn" onclick="downloadPdf()" disabled 
    	style="background-color: #007bff; color: white; border: none; padding: 10px 20px; border-radius: 5px;
		  cursor: pointer;">
      <img src="/resources/img/pdf-icon.png" class="btn-icon" />
      PDF 다운로드
    </button>
  </div>

</div>

<script>
$(document).ready(function () {
  const currentDate = new Date();
  const currentYear = currentDate.getFullYear();
  const currentMonth = currentDate.getMonth() + 1;

  const $yearSelect = $('#yearSelect');
  const $monthSelect = $('#monthSelect');

  // 연도 select
  for (let y = currentYear; y >= currentYear - 2; y--) {
    $yearSelect.append('<option value="' + y + '">' + y + '년</option>');
  }

  // 월 select 생성
  function populateMonthOptions(limit = 12) {
    $monthSelect.empty();
    for (let m = 1; m <= limit; m++) {
      const mm = m < 10 ? '0' + m : m;
      $monthSelect.append('<option value="' + mm + '">' + m + '월</option>');
    }
  }

  // 초기 월 제한
  populateMonthOptions(currentMonth - 1);

  // 연도 선택 시 월 조절
  $yearSelect.on('change', function () {
    const selectedYear = parseInt($(this).val());
    if (selectedYear === currentYear) {
      populateMonthOptions(currentMonth - 1);
    } else {
      populateMonthOptions(12);
    }
  });

  // 조회
  $('#loadBtn').click(function () {
    const year = $('#yearSelect').val();
    const month = $('#monthSelect').val();
    const salMonth = year + month;

    $.ajax({
      url: '/salary/view',
      data: { salMonth: salMonth },
      success: function (res) {
    	$('#excelBtn').prop('disabled', false);
    	$('#pdfBtn').prop('disabled', false);  
    	  
        $('#empName').text(res.empName);
        $('#deptName').text(res.deptName);
        $('#payDate').text(res.payDate);
        $('#workRange').text(year + "-" + month + "-01 ~ " + year + "-" + month + "-31");

        $('#totalPay').text(res.totalPay.toLocaleString());
        $('#totalDeduct').text(res.totalDeductions.toLocaleString());
        $('#netPay').text(res.netPay.toLocaleString());

        const pays = res.detailList.filter(i => i.itemType === 'P' && i.amount > 0);
        const deducts = res.detailList.filter(i => i.itemType === 'D' && i.amount > 0);

        let rows = '';
        const len = Math.max(pays.length, deducts.length);
        for (let i = 0; i < len; i++) {
          const p = pays[i] || { itemName: '', amount: '' };
          const d = deducts[i] || { itemName: '', amount: '' };
          rows += '<tr>' +
              '<td>' + p.itemName + '</td><td>' + (p.amount ? p.amount.toLocaleString() : '') + '</td>' +
              '<td>' + d.itemName + '</td><td>' + (d.amount ? d.amount.toLocaleString() : '') + '</td>' +
              '</tr>';
        }
        $('#tableBody').html(rows);
      },
      error: function () {
    	$('#excelBtn').prop('disabled', true);
    	$('#pdfBtn').prop('disabled', true);
        alert("해당 연, 월의 급여명세서가 존재하지 않습니다.");
      }
    });
  });
});

// 다운로드 함수
function downloadExcel() {
  const year = $('#yearSelect').val();
  const month = $('#monthSelect').val();
  const salMonth = year + month;

  // 먼저 백엔드에 존재 여부 확인
  fetch('/salary/view?salMonth=' + salMonth)
    .then(res => {
      if (!res.ok) {
        alert("해당 월의 급여 명세서가 없습니다.");
      } else {
        // 다운로드 시작
        window.location.href = '/salary/download/excel?salMonth=' + salMonth;
      }
    })
    .catch(() => {
      alert("서버 오류 발생 - 잠시 후 다시 시도해주세요.");
    });
}

function downloadPdf() {
  const year = $('#yearSelect').val();
  const month = $('#monthSelect').val();
  const salMonth = year + month;
  window.location.href = '/salary/download/pdf?salMonth=' + salMonth;
}
</script>

</body>
</html>