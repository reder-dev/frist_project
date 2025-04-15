<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%-- 테스트용 로그인 사용자 지정 (최아영 emp_id = 22100003) --%>
<%
	com.itwill.approval.dto.ApprovalSearchDTO loginUser = new com.itwill.approval.dto.ApprovalSearchDTO();
	loginUser.setEmpId("22100003");
	session.setAttribute("loginUser", loginUser);
%>

<html>
<head>
<title>전자결재 신청</title>
<!-- 여기에 캐시 방지 메타 태그 삽입 -->
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script>
    // JSP에서 로그인한 사용자 ID를 JavaScript 변수로 저
    const loginUserId = "${sessionScope.loginUser.empId}";
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
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
  font-family: "Noto Sans KR", sans-serif;
  background: #f9f9f9;
  margin: 0;
  padding: 40px;
}

form {
  max-width: 800px;
  margin: auto;
  background: #fff;
  padding: 30px 40px;
  border-radius: 10px;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.08);
}



h2 {
  text-align: center;
  margin-bottom: 30px;
}

label {
  display: block;
  font-weight: 400;
  margin: 20px 0 8px;
}

input[type="text"],
input[type="date"],
select,
textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 15px;
}

input[type="radio"] {
  margin-right: 5px;
}

textarea {
  resize: vertical;
}

input[type="date"] {
  width: 30%;
}

button {
  padding: 7px 16px;
  font-size: 15px;
  background: #f0f0f0;
  color: black;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:disabled {
  background: #ccc;
  cursor: not-allowed;
}

#submitBtn:enabled {
  background-color: #007bff;
  color: white;
  cursor: pointer;
  transition: 0.2s;
}

.fileList {
  margin-top: 10px;
  padding: 10px;
  background: #f3f3f3;
  border: 1px dashed #ccc;
  white-space: pre-wrap;
  font-size: 0.9em;
  color: #333;
}

#leaveSection,
#businessSection {
  background: #fdfdfd;
  padding: 20px;
  border-radius: 8px;
  border: 1px solid #e0e0e0;
  margin-top: 20px;
}

#approverResultTable {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

#approverResultTable th,
#approverResultTable td {
  border: 1px solid #ddd;
  padding: 8px;
}

#selectedApprovers {
  list-style: none;
  padding-left: 0;
}

#selectedApprovers li {
  background: #e9f0ff;
  padding: 5px 10px;
  margin: 5px 0;
  border-radius: 5px;
}

.required {
  color: red;
  margin-right: 4px;
}

#leaveSection, #businessSection {
    background: #fdfdfd;
    padding: 0 20 20 20;
    border-radius: 8px;
    border: 1px solid #e0e0e0;
    margin-top: 20px;
}

.search-wrapper {
  display: flex;       /* 가로 정렬 */
  align-items: center; /* 세로 정렬 */
  gap: 8px;            /* 입력창과 버튼 사이 간격 */
  margin: 15px;
}

.search-input {
  width: 100px;
  padding: 5px;
  font-size: 14px;
}
.radio-group {
  display: flex;
  gap: 10px;  
  margin-top: 3px;
}
.radio-group label {
  display: flex;
  align-items: center;
  font-size: 14px;
}
</style>

<body>
<h2>전자결재 신청</h2>
<form class="form-box" id="approvalForm" method="post" action="/approval/apply" enctype="multipart/form-data">

  <!-- 문서 정보 -->
  <div class="form-section">
    <label><span class="required">* </span><span style="font-weight: bold;">문서 제목</span></label>
    <input type="text" class="form-control" name="documentTitle" id="documentTitle">

    <label><span class="required">* </span><span style="font-weight: bold;">결재 분류</span></label>
    <select class="form-control" name="referenceTableName" id="categorySelect" required>
      <option value="">-- 선택하세요 --</option>
      <option value="LEAVE">휴가(반차/연차/병가)</option>
      <option value="BUSINESS">출장</option>
    </select>

    <input type="hidden" name="requester" value="${sessionScope.loginUser.empId}" />
    <input type="hidden" name="register" value="${sessionScope.loginUser.empId}" />
    <input type="hidden" name="attachmentCount" id="attachmentCount" value="0" />
  </div>

  <!-- 휴가 영역 -->
  <div id="leaveSection" class="form-section" style="display:none;">
    <div class="radio-group">
    <label style="font-size: 16px;"><span class="required">* </span>휴가 구분 : </label>
    <label style="font-size: 16px;"><input type="radio" name="leaveStatus" value="반차" style="margin-right: 10;"> 반차</label>
    <label style="font-size: 16px;"><input type="radio" name="leaveStatus" value="연차" style="margin-right: 10;"> 연차</label>
    <label style="font-size: 16px;"><input type="radio" name="leaveStatus" value="병가" style="margin-right: 10;"> 병가</label>
	</div>
	
    <label><span class="required">* </span>휴가 기간</label>
    <input type="date" class="form-control half-width" name="leaveStartDate" id="leaveStartDate"> ~ 
    <input type="date" class="form-control half-width" name="leaveEndDate" id="leaveEndDate">

    <label>코멘트</label>
    <textarea name="comment" class="form-control"></textarea>

    <label>첨부파일 (최대 3개)</label>
    <input type="file" id="leaveFiles" class="form-control" multiple>
    <div id="leaveFileList" class="fileList"></div>
  </div>

  <!-- 출장 영역 -->
  <div id="businessSection" class="form-section" style="display:none;">
    <label><span class="required">* </span>출장지</label>
    <input type="text" name="businessLocation" class="form-control">

    <label><span class="required">* </span>출장 목적</label>
    <input type="text" name="businessPurpose" class="form-control">

    <label><span class="required">* </span>출장 기간</label>
    <input type="date" class="form-control half-width" name="businessStartDate"> ~
    <input type="date" class="form-control half-width" name="businessEndDate">

    <label>코멘트</label>
    <textarea name="comment" class="form-control"></textarea>

    <label>첨부파일</label>
    <input type="file" id="bizFiles" class="form-control" multiple>
    <div id="bizFileList" class="fileList"></div>
  </div>

  <!-- 결재선 지정 -->
  <div class="form-section" style="background: #fdfdfd; padding: 0 20 20 20; border-radius: 8px; border: 1px solid #e0e0e0; margin: 20px 0;">
    <h3>결재선 지정 <span style="color: blue; font-weight: 400; font-size: 80%; margin-left: 10">(저장된 결재선 선택 또는 직접 지정)</span></h3>
	
    <label style="font-weight: bold;"><span class="required">* </span>저장된 결재선 선택</label>
    <select id="templateSelect" class="form-control" style="width: 300px; margin-left: 15;">
      <option value="">-- 결재선 선택 --</option>
    </select>
	
    <h4 style="margin-bottom: 0"><span class="required">* </span>직접 결재선 지정</h4>
    <div class="search-wrapper">
	  <input type="text" id="approverKeyword" class="search-input" placeholder="이름 입력" style="width: 100;">
	  <button type="button" id="searchApproverBtn">검색</button>
	</div>

	<div id="approverResultSection" style="display: none;">
    <table style="width: 400; margin-left: 15;" id="approverResultTable">
      <thead>
        <tr>
          <th style="font-weight: 400;">이름</th>
          <th style="font-weight: 400;">부서</th>
          <th style="font-weight: 400;">직책</th>
          <th style="font-weight: 400;">추가</th>
        </tr>
      </thead>
      <tbody></tbody>
    </table>
	</div>
	
	<div id="selectedApproversSection" style="display: none;">
	<div style="display: flex; align-items: center;">
		<h4 style="margin-bottom: 0; margin-top: 30;">선택된 결재자</h4>
        <button type="button" onclick="resetApprovalLine()" class="btn" style="margin-left: 25; margin-top:25; display:none;" id="resetBtn">결재선 초기화</button>
    </div>
    <ul id="selectedApprovers"></ul>
    <input type="hidden" name="approverList" id="approverList">
	</div>
	
	<div id="saveTemplateSection" style="display: none;">
    <label style="font-weight: bold; margin-left: 15;">결재선 이름</label>
    <input style="width: 100; margin-left: 15;" type="text" id="templateName" class="form-control" disabled>
    <button type="button" id="saveTemplateBtn" class="btn" disabled>결재선 저장</button>
	</div>
	
  </div>

  <!-- 제출 버튼 -->
  <div style="text-align: right;">
    <button type="submit" class="btn" id="submitBtn" disabled>결재 신청</button>
  </div>
</form>
	
	<script>
	// 결재선 저장 버튼 활성화 제어 함수
	function toggleTemplateInput(enabled) {
	    $('#templateName').prop('disabled', !enabled);
	    $('#saveTemplateBtn').prop('disabled', !enabled);
	}
	
    const selectedApprovers = [];
    let isTemplateSelected = false;
	
    // 날짜 유효성 검사
    function isDateRangeValid(start, end) {
        return new Date(start) <= new Date(end);
    }

    // 휴가 날짜 검사
    $('input[name="leaveStartDate"], input[name="leaveEndDate"]').on('change blur input', function () {
	    const start = $('input[name="leaveStartDate"]').val();
	    const end = $('input[name="leaveEndDate"]').val();
	    if (start && end && !isDateRangeValid(start, end)) {
	        alert('올바르지 않은 기간입니다. 날짜를 다시 확인해주세요.');
	        $('input[name="leaveEndDate"]').val('');
	    }
	});

    // 출장 날짜 검사
    $('input[name="businessStartDate"], input[name="businessEndDate"]').on('change blur input', function () {
	    const start = $('input[name="businessStartDate"]').val();
	    const end = $('input[name="businessEndDate"]').val();
	    if (start && end && !isDateRangeValid(start, end)) {
	        alert('올바르지 않은 기간입니다. 날짜를 다시 확인해주세요.');
	        $('input[name="businessEndDate"]').val('');
	    }
	});

    // 직접/템플릿 선택 분리
    function disableOtherApprovalMethod(method) {
        if (method === 'manual') {
            $('#templateSelect').prop('disabled', true);
        } else if (method === 'template') {
            $('#approverKeyword, #searchApproverBtn').prop('disabled', true);
            $('#approverResultTable button').prop('disabled', true);
        }
    }

    function resetApprovalLine() {
        $('#templateSelect').val('').prop('disabled', false);
        $('#approverKeyword, #searchApproverBtn').prop('disabled', false);
        
     	// 직접 결재선 관련 초기화 및 감춤
        $('#approverKeyword, #searchApproverBtn').prop('disabled', false).val('');
        $('#approverResultTable tbody').empty();
        $('#manualApprovalSection').hide();
        $('#approverResultSection').hide();
        $('#selectedApproversSection').hide();
        $('#saveTemplateSection').hide();
        
        $('#approverResultTable button').prop('disabled', false);
        selectedApprovers.length = 0;
        renderApprovers();
        toggleSubmitButton();
        $('#approverKeyword').val(''); // 검색 입력창 비우기
        $('#approverResultTable tbody').empty(); // 검색 결과 테이블도 초기화
        $('#resetBtn').hide();
    }

    // 카테고리 선택
    $('#categorySelect').change(function () {
        $('#leaveSection, #businessSection').hide();
        const selected = $(this).val();
        if (selected === 'LEAVE') $('#leaveSection').show();
        else if (selected === 'BUSINESS') $('#businessSection').show();
        toggleSubmitButton();
    });

    // 결재자 검색
    $('#searchApproverBtn').click(function () {
        const keyword = $('#approverKeyword').val();
        if (!keyword.trim()) return;
        $.ajax({
            url: '/approval/search-approvers',
            type: 'GET',
            data: { keyword },
            success: function (data) {
                const tbody = $('#approverResultTable tbody');
                tbody.empty();

                if (!data || data.length === 0) {
                	$('#approverResultSection').hide(); // 검색결과 숨김
                    alert("해당하는 사원의 이름이 없습니다. 다시 입력해주세요.");
                    return;
                }
                $('#approverResultSection').show(); // 검색결과 보임

                data.forEach((item, index) => {
                	const row = "<tr>"
                        + "<td style='text-align:center;'>" + item.empName + "</td>"
                        + "<td style='text-align:center;'>" + item.depName + "</td>"
                        + "<td style='text-align:center;'>" + item.posId + "</td>"
                        + "<td style='text-align:center;'><button class='addBtn' "
                        + "data-empid='" + item.empId + "' "
                        + "data-name='" + item.empName + "' "
                        + "data-dep='" + item.depName + "' "
                        + "data-pos='" + item.posId + "'>"
                        + "추가</button></td>"
                        + "</tr>";

                    $('#approverResultTable tbody').append(row);
                });
            },
            error: () => alert("검색 요청 중 오류가 발생했습니다.")
        });
    });

    function addApprover(empId, empName, depName, posId) {
        // 중복 추가 방지
        if (selectedApprovers.find(a => a.empId === empId)) {
            alert("이미 추가된 결재자입니다.");
            return;
        }

        // 안전하게 값 설정 (null이나 undefined일 수 있으므로)
        empName = empName || empId;
        depName = depName || '-';
        posId = posId || '-';

        // 결재자 목록에 추가
        disableOtherApprovalMethod('manual');
        isTemplateSelected = false;
        selectedApprovers.push({ empId, empName, depName, posId });

        $('#approverResultTable tbody').empty();  // 🔥 검색결과 표 초기화
        
        $('#manualApprovalSection').show();
        $('#approverResultSection').show();
        $('#selectedApproversSection').show();
        $('#saveTemplateSection').show();
        $('#resetBtn').show();
        
        // UI 반영
        renderApprovers();
        toggleSubmitButton();
        
        // 결재선 저장 버튼 활성화
        toggleTemplateInput(true);
    }

    function renderApprovers() {
        const ul = $('#selectedApprovers');
        ul.empty(); // 기존 내용 비우기

        for (let i = 0; i < selectedApprovers.length; i++) {
            const a = selectedApprovers[i];

            const empName = a.empName ? a.empName : a.empId;
            const dep = a.depName ? a.depName : "-";
            const pos = a.posId ? a.posId : "-";

         	// 기본 텍스트 구성
            let li = "<li>" + (i + 1) + ". " + empName + " (" + dep + " / " + pos + ")";

            // 직접 추가한 경우에만 X버튼 추가
            if (!isTemplateSelected) {
                li += " <button type='button' onclick='removeApprover(" + i + ")'>X</button>";
            }

            li += "</li>";

            ul.append(li);
        }

        // 숨겨진 input에도 반영
        $('#approverList').val(selectedApprovers.map(a => a.empId).join(','));
    }

    function removeApprover(index) {
        selectedApprovers.splice(index, 1);
        renderApprovers();
        toggleSubmitButton();
    }

 	// 결재선 저장 버튼 클릭 시 실행
    $('#saveTemplateBtn').click(function () {
    let name = $('#templateName').val().trim(); // 👈 이 줄이 없어서 오류가 난 거예요!

    if (!name) {
        alert('결재선 저장을 위해 결재선 이름을 입력하세요.');
        return;
    }

    if (selectedApprovers.length === 0) {
        alert('결재자를 추가하세요.');
        return;
    }

    // 중복 확인
    $.ajax({
        url: '/approval/template/check-name',
        type: 'GET',
        data: { name: name },
        success: function (isDuplicate) {
            if (isDuplicate) {
                alert("이미 존재하는 결재선 이름입니다. 다른 이름을 입력해주세요.");
                return;
            }

            // 저장 요청
            const data = {
                templateId: 'TMP' + Date.now(),
                templateName: name,
                ownerId: loginUserId,
                detailList: selectedApprovers.map((a, i) => ({
                    approvalOrder: i + 1,
                    approverId: a.empId,
                    approverRole: a.posId
                }))
            };

            $.ajax({
                url: '/approval/template/save',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: res => alert(res === 'success' ? '저장 완료' : '실패'),
                error: () => alert('서버 오류')
            });
        },
        error: () => alert("중복 확인 실패")
    });
});

    $(document).ready(function () {
    	// 오늘 날짜 기준 min 설정
        const today = new Date().toISOString().split('T')[0];
        // 기본 감춤
        $('#manualApprovalSection').hide();
        $('#approverResultSection').hide();
        $('#selectedApproversSection').hide();
        $('#saveTemplateSection').hide();

        // 템플릿 + 상세 결재자 목록을 드롭다운에 표시 (미리보기 포함)
       $.ajax({
	    url: '/approval/template/list-full',
	    type: 'GET',
	    data: { ownerId: loginUserId },
	    success: function (data) {
	        const select = $('#templateSelect');
	        select.empty(); // 기존 옵션 초기화
	        select.append('<option value="">-- 결재선 선택 --</option>');

			//let myDetailText="";
	        data.forEach(function(t, i) {
	            // 여기에서 선언해야 함
	            let myDetailText = "";

	            // 내부 반복문 (결재자 목록)
	            const approvers = Array.isArray(t.approvers) ? t.approvers : Object.values(t.approvers);

	            for (let j = 0; j < approvers.length; j++) {
	                const a = approvers[j];
	                const empName = a.empName ? a.empName : a.approverId;
	                const dep = a.depName ? a.depName : "-";
	                const pos = a.posId ? a.posId : a.approverRole;

	                // 문자열 연결은 여기서 계속 진행
	                myDetailText += (j + 1) + ". " + empName + " (" + dep + " / " + pos + ") → ";
	            }

	            // 반복문 끝난 후 출력
	            if (myDetailText.endsWith(" → ")) {
	                myDetailText = myDetailText.slice(0, -3);
	            }

	            const optionText = t.templateName + " | " + myDetailText;

	            const option = $('<option>')
	                .val(t.templateId)
	                .text(optionText)
	                .data('approvers', approvers);

	            $('#templateSelect').append(option);
	        });
	        
	        $('#categorySelect').change(function () {
	            $('#leaveSection, #businessSection').hide();
	            $('#leaveFiles, #bizFiles').removeAttr("name");

	            const selected = $(this).val();
	            if (selected === 'LEAVE') {
	                $('#leaveSection').show();
	                $('#leaveFiles').attr("name", "attachmentFiles");
	            } else if (selected === 'BUSINESS') {
	                $('#businessSection').show();
	                $('#bizFiles').attr("name", "attachmentFiles");
	            }
	        });

	     	// 이벤트 위임 추가
	        $('#approverResultTable').on('click', '.addBtn', function () {
	            const empId = $(this).data('empid');
	            const empName = $(this).data('name');
	            const depName = $(this).data('dep');
	            const posId = $(this).data('pos');
	            addApprover(empId, empName, depName, posId);
	            $('#approverResultSection').hide();
	        });
	     	
	    	 // 오늘 날짜 기준으로 이전 날짜 비활성화
	        const today = new Date().toISOString().split('T')[0];

	        // 휴가 날짜
	        $('input[name="leaveStartDate"]').attr('min', today);
	        $('input[name="leaveEndDate"]').attr('min', today);

	        // 출장 날짜
	        $('input[name="businessStartDate"]').attr('min', today);
	        $('input[name="businessEndDate"]').attr('min', today);
	    }
	});

    // 휴가 첨부파일 선택 시
       $('#leaveFiles').on('change', function () {
           const files = this.files;
           $('#attachmentCount').val(files.length);

           if (files.length > 3) {
               alert("최대 3개까지 파일을 선택할 수 있습니다.");
               $(this).val('');
               $('#attachmentCount').val(0);
               $('#leaveFileList').text('');
               return;
           }

           let fileNames = '';
           for (let i = 0; i < files.length; i++) {
               fileNames += files[i].name + '\n';
           }
           $('#leaveFileList').text(fileNames.trim());
       });

       // 출장 첨부파일 선택 시
       $('#bizFiles').on('change', function () {
           const files = this.files;
           $('#attachmentCount').val(files.length);

           if (files.length > 3) {
               alert("최대 3개까지 파일을 선택할 수 있습니다.");
               $(this).val('');
               $('#attachmentCount').val(0);
               $('#bizFileList').text('');
               return;
           }

           let fileNames = '';
           for (let i = 0; i < files.length; i++) {
               fileNames += files[i].name + '\n';
           }
           $('#bizFileList').text(fileNames.trim());
       });

        
        // 페이지 로드 시 결재선 저장 버튼 기본 비활성화
        toggleTemplateInput(false);
    });

    $('#templateSelect').change(function () {
    	const selected = $(this).find('option:selected');
    	const approverData = selected.data('approvers');

    	if (!approverData) return;
    	
    	 // 직접 결재선 관련은 감춤
    	  $('#manualApprovalSection').hide();
    	  $('#approverResultSection').hide();
    	  $('#saveTemplateSection').hide();

    	  // 선택된 결재자는 보여줌
    	  $('#selectedApproversSection').show();
    	
    	isTemplateSelected = true;

        // 템플릿으로 선택되었으므로 직접입력 영역 비활성화
        disableOtherApprovalMethod('template');
		
        // 저장된 결재선 선택 시 비활성화
        toggleTemplateInput(false);
        
        // 기존 목록 초기화
        selectedApprovers.length = 0;

        // 새로운 결재자 목록 채우기
        approverData.forEach(a => {
            selectedApprovers.push({
                empId: a.approverId,
                empName: a.empName,
                depName: a.depName,
                posId: a.posId
            });
        });
		
        $('#selectedApproversSection').show();
        $('#resetBtn').show();
        
        renderApprovers();
        toggleSubmitButton();
    });

    function toggleSubmitButton() {
        const title = $('input[name="documentTitle"]').val().trim();
        const category = $('#categorySelect').val();
        const hasApprover = selectedApprovers.length > 0 || $('#templateSelect').val();
        let categoryValid = false;

        if (category === 'LEAVE') {
            const leaveType = $('input[name="leaveStatus"]:checked').val();
            const start = $('input[name="leaveStartDate"]').val();
            const end = $('input[name="leaveEndDate"]').val();
            categoryValid = leaveType && start && end && isDateRangeValid(start, end);
        } else if (category === 'BUSINESS') {
            const location = $('input[name="businessLocation"]').val().trim();
            const purpose = $('input[name="businessPurpose"]').val().trim();
            const start = $('input[name="businessStartDate"]').val();
            const end = $('input[name="businessEndDate"]').val();
            categoryValid = location && purpose && start && end && isDateRangeValid(start, end);
        }

        $('#submitBtn').prop('disabled', !(title && category && hasApprover && categoryValid));
    }

    $('input, select').on('change keyup', toggleSubmitButton);
    
    $('#approvalForm').on('submit', function (e) {
        e.preventDefault(); // 기본 제출 막기

        const form = this;
        const formData = new FormData(form);

        $.ajax({
            url: '/approval/apply',
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            success: function () {
                alert('결재 신청이 완료되었습니다.');
                location.reload(); // 다시 현재 페이지로 리로드
            },
            error: function () {
                alert('오류가 발생했습니다. 다시 시도해주세요.');
            }
        });
    });
   
</script>

<script>
document.addEventListener('DOMContentLoaded', function () {
  const start = document.getElementById('leaveStartDate');
  const end = document.getElementById('leaveEndDate');

  // 시작일이 바뀔 때
  start.addEventListener('change', function () {
    const selected = document.querySelector('input[name="leaveStatus"]:checked');
    if (selected && selected.value === '반차') {
      end.value = start.value;
    }
  });

  // 라디오 버튼 변경 시
  document.querySelectorAll('input[name="leaveStatus"]').forEach(function (radio) {
    radio.addEventListener('change', function () {
      if (radio.value === '반차') {
        end.value = start.value;
      }
    });
  });
});
</script>

</body>
</html>