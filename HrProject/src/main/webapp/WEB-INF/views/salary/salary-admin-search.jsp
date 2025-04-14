<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	com.itwill.salary.dto.SalaryEmployeeDTO loginUser = new com.itwill.salary.dto.SalaryEmployeeDTO();
	//loginUser.setEmpId("22100003"); // 최아영
	loginUser.setEmpId("10100001"); //이민준
	//loginUser.setEmpId("15100002"); //박지원
	session.setAttribute("loginUser", loginUser);
%>

<html>
<head>
<title>급여 지급 확정</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
<
style
>
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
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
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

		<h2>급여 지급 확정</h2>
		<div style="margin-bottom: 20px;">
			<label>사원 검색 : </label> 
			<input type="text" id="searchInput" placeholder="사번 또는 이름 입력" 
			style="padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		    font-size: 15px;"/>
			<button type="button" onclick="searchEmployee()" style="padding: 7px 16px; font-size: 15px; background: #f0f0f0; color: black; border: none; border-radius: 5px; cursor: pointer">검색</button>

			<ul id="searchResults" style="list-style: none; padding-left: 0; margin-top: 10px;"></ul>
		</div>

		<!-- 연월 선택 -->
		<div class="controls">
			<label>연도:</label> <select id="yearSelect"></select> <label>월:</label> <select id="monthSelect"></select>

			<button id="loadBtn" style="padding: 7px 16px; font-size: 15px; background: #f0f0f0; color: black; border: none; border-radius: 5px; cursor: pointer;">조회하기</button>
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

		<div class="button-group">
			<button onclick="confirmSalary()" style="background-color: #007bff; color: white; border: none; padding: 10px 20px; border-radius: 5px;">지급 확정</button>
		</div>

		<!-- 다운로드 버튼 -->
		<!-- 
  <div class="button-group">
    <button type="button" id="excelBtn" onclick="downloadExcel()" disabled style="padding: 7px 16px;
		  font-size: 15px;
		  background: #f0f0f0;
		  color: black;
		  border: none;
		  border-radius: 5px;
		  cursor: pointer;">
      <img src="/resources/img/excel-icon.png" class="btn-icon" />
      엑셀 다운로드
    </button>
    <button type="button" id="pdfBtn" onclick="downloadPdf()" disabled style="padding: 7px 16px;
		  font-size: 15px;
		  background: #f0f0f0;
		  color: black;
		  border: none;
		  border-radius: 5px;
		  cursor: pointer;">
      <img src="/resources/img/pdf-icon.png" class="btn-icon" />
      PDF 다운로드
    </button>
  </div>
  -->

	</div>

<script>
const loggedInAdminId = '<%=((com.itwill.salary.dto.SalaryEmployeeDTO) session.getAttribute("loginUser")).getEmpId()%>';

let selectedEmpId = "";

// itemName ➜ itemId 변환 함수 추가
function convertItemNameToCode(name) {
  const map = {
    "기본급": "P001",
    "야근수당": "P002",
    "야간수당": "P002",
    "휴일수당": "P003",
    "휴일근무수당": "P003",
    "직책수당": "P004",
    "소득세": "D001",
    "지방소득세": "D002",
    "국민연금": "D003",
    "건강보험": "D004",
    "장기요양보험": "D005",
    "장기요양보험료": "D005",
    "고용보험": "D006"
  };
  return map[name] || null;
}

$(document).ready(function () {
  const currentDate = new Date();
  const currentYear = currentDate.getFullYear();
  const currentMonth = currentDate.getMonth() + 1;

  const $yearSelect = $('#yearSelect');
  const $monthSelect = $('#monthSelect');

  for (let y = currentYear; y >= currentYear - 2; y--) {
    $yearSelect.append('<option value="' + y + '">' + y + '년</option>');
  }

  function populateMonthOptions(limit = 12) {
    $monthSelect.empty();
    for (let m = 1; m <= limit; m++) {
      const mm = m < 10 ? '0' + m : m;
      $monthSelect.append('<option value="' + mm + '">' + m + '월</option>');
    }
  }

  populateMonthOptions(currentMonth - 1);

  $yearSelect.on('change', function () {
    const selectedYear = parseInt($(this).val());
    if (selectedYear === currentYear) {
      populateMonthOptions(currentMonth - 1);
    } else {
      populateMonthOptions(12);
    }
  });

  $('#loadBtn').click(function () {
    const year = $('#yearSelect').val();
    const month = $('#monthSelect').val();
    const salMonth = year + month;

    $.ajax({
      url: '/salary/admin/calculate',
      data: { empId: selectedEmpId, salMonth: salMonth },
      success: function (res) {
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
        alert("해당 연, 월의 급여명세서가 존재하지 않습니다.");
      }
    });
  });
});

function searchEmployee() {
  const keyword = $('#searchInput').val().trim();
  if (!keyword) {
    alert("검색어를 입력하세요.");
    return;
  }

  $.ajax({
    url: '/salary/admin/search-employee',
    method: 'GET',
    data: { keyword: keyword },
    success: function (data) {
      const list = $('#searchResults');
      list.empty();

      if (!data || data.length === 0) {
        list.append('<li>검색 결과 없음</li>');
        return;
      }

      data.forEach(emp => {
        const li = $('<li></li>')
          .text(emp.empName + ' (' + emp.empId + ')')
          .css({ cursor: 'pointer', marginBottom: '5px' })
          .click(() => {
            selectedEmpId = emp.empId;
            $('#searchInput').val(emp.empName + ' (' + emp.empId + ')');
            $('#searchResults').empty();
            alert('선택된 사원: ' + emp.empName + ' / ' + emp.empId);
          });
        list.append(li);
      });
    },
    error: function () {
      alert("검색 중 오류 발생");
    }
  });
}

function confirmSalary() {
  const year = $('#yearSelect').val();
  const month = $('#monthSelect').val();
  const salMonth = year + month;

  if (!selectedEmpId) {
    alert("사원을 먼저 선택하세요.");
    return;
  }

  const dto = {
    empId: selectedEmpId,
    salMonth: salMonth,
    payDate: new Date().toISOString().split("T")[0],
    totalPay: Number($('#totalPay').text().replace(/[^0-9]/g, '')),
    totalDeductions: Number($('#totalDeduct').text().replace(/[^0-9]/g, '')),
    netPay: Number($('#netPay').text().replace(/[^0-9]/g, '')),
    register: loggedInAdminId,
    detailList: []
  };

  $('#tableBody tr').each(function () {
    const tds = $(this).find('td');
    if (tds.length < 4) return;

    const itemNameP = $(tds[0]).text().trim();
    const amountP = $(tds[1]).text().replace(/[^0-9]/g, '');
    const itemNameD = $(tds[2]).text().trim();
    const amountD = $(tds[3]).text().replace(/[^0-9]/g, '');

    if (itemNameP) {
   	dto.detailList.push({
   		  itemId: convertItemNameToCode(itemNameP),
   		  itemName: itemNameP,
   		  itemType: 'P',
   		  amount: Number(amountP)
   		});
    }

    if (itemNameD) {
   	dto.detailList.push({
   		  itemId: convertItemNameToCode(itemNameD),
   		  itemName: itemNameD,
   		  itemType: 'D',
   		  amount: Number(amountD)
   		});
    }
  });

  $.ajax({
    url: '/salary/admin/confirm',
    method: 'POST',
    contentType: 'application/json',
    data: JSON.stringify(dto),
    success: function () {
      alert("지급 확정 완료!");
    },
    error: function (xhr) {
      if (xhr.status === 409) {
        alert(xhr.responseText); // 이미 확정된 급여입니다.
      } else {
        alert("지급 확정 중 오류 발생");
      }
    }
  });
}
</script>

</body>
</html>