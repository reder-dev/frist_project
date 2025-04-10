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
#templateSelect {
	font-size: 16px;
	padding: 5px;
	width: 300px;
}

#templateSelect option {
	color: black; /* 혹시 회색이나 흰색이면 안보일 수 있어서 강제 설정 */
	background-color: white;
}
</style>
<body>

	<h2>전자결재 신청</h2>
	<form id="approvalForm" method="post" action="/approval/apply" enctype="multipart/form-data">
		<!-- 문서 제목 -->
		<label>문서 제목: <span style="color: red;">(필수)</span></label> 
		<input type="text" name="documentTitle" id="documentTitle"><br> <br>

		<!-- 결재 분류 -->
		<label>결재 분류: <span style="color: red;">(필수)</span></label> 
		<select name="referenceTableName" id="categorySelect" required>
			<option value="">-- 선택하세요 --</option>
			<option value="LEAVE">휴가(반차/연차/병가)</option>
			<option value="BUSINESS">출장</option>
		</select><br> <br>

		<!-- 로그인 사용자 ID -->
		<input type="hidden" name="requester" value="${sessionScope.loginUser.empId}" /> <input type="hidden" name="register" value="${sessionScope.loginUser.empId}" /> <input type="hidden" name="attachmentCount" id="attachmentCount" value="0" />

		<!-- 휴가 신청 영역 -->
		<div id="leaveSection" style="display: none;">
			<h4>휴가 신청</h4>
			<label>휴가 구분: <span style="color: red;">(필수)</span></label> 
			<input type="radio" name="leaveStatus" value="반차">반차 
			<input type="radio" name="leaveStatus" value="연차">연차 
			<input type="radio" name="leaveStatus" value="병가">병가<br> <br> 
			<label>휴가 기간: <span style="color: red;">(필수)</span></label> 
			<input type="date" name="leaveStartDate"> ~ <input type="date" name="leaveEndDate"><br> <br> 
			<label>코멘트:</label><br>
			<textarea name="comment" rows="3" cols="50"></textarea>
			<br> <br> <label>첨부파일:</label> <input type="file" id="leaveFiles" multiple><br> <br>
			<pre id="leaveFileList" class="fileList"></pre>
		</div>

		<!-- 출장 신청 영역 -->
		<div id="businessSection" style="display: none;">
			<h4>출장 신청</h4>
			<label>출장지: <span style="color: red;">(필수)</span></label> 
			<input type="text" name="businessLocation"><br> <br> 
			<label>출장목적: <span style="color: red;">(필수)</span>
			</label> <input type="text" name="businessPurpose"><br> <br> 
			<label>출장 기간: <span style="color: red;">(필수)</span></label> 
			<input type="date" name="businessStartDate"> ~ <input type="date" name="businessEndDate"><br> <br> 
			<label>코멘트:</label><br>
			<textarea name="comment" rows="3" cols="50"></textarea>
			<br> <br> <label>첨부파일:</label> <input type="file" id="bizFiles" multiple><br> <br>
			<pre id="bizFileList" class="fileList"></pre>
		</div>

		<!-- 결재선 지정 -->
		<h3>
			결재선 지정 <span style="color: red; font-weight: 400; font-size: 0.8em;"> (직접 결재선 지정 또는 저장된 결재선 선택) </span>
		</h3>

		<h4>직접 결재선 지정</h4>
		<label>이름 검색:</label> <input type="text" id="approverKeyword" placeholder="이름 입력">
		<button type="button" id="searchApproverBtn">검색</button>
		<br> <br>

		<table border="1" id="approverResultTable">
			<thead>
				<tr>
					<th>이름</th>
					<th>부서</th>
					<th>직책</th>
					<th>추가</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>

		<h4>선택된 결재자</h4>
		<ul id="selectedApprovers"></ul>
		<input type="hidden" name="approverList" id="approverList"><br> <br>

		<!-- 템플릿 저장 -->
		<label>결재선 이름:</label> <input type="text" id="templateName" disabled>
		<button type="button" id="saveTemplateBtn" disabled>결재선 저장</button>
		<br> <br>

		<!-- 결재선 불러오기 -->
		<h4>저장된 결재선 선택</h4>
		<select id="templateSelect" style="width: 1000px;">
			<option value="">-- 결재선 선택 --</option>
		</select><br> <br>

		<button type="button" onclick="resetApprovalLine()">결재선 초기화</button>
		<br> <br>

		<button type="submit" id="submitBtn" disabled>결재 신청</button>
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
        $('#approverResultTable button').prop('disabled', false);
        selectedApprovers.length = 0;
        renderApprovers();
        toggleSubmitButton();
        $('#approverKeyword').val(''); // 검색 입력창 비우기
        $('#approverResultTable tbody').empty(); // 검색 결과 테이블도 초기화
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
                    alert("해당하는 사원의 이름이 없습니다. 다시 입력해주세요.");
                    return;
                }

                data.forEach((item, index) => {
                	const row = "<tr>"
                        + "<td>" + item.empName + "</td>"
                        + "<td>" + item.depName + "</td>"
                        + "<td>" + item.posId + "</td>"
                        + "<td><button class='addBtn' "
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
        // 1. 이름 입력 확인
        if (!name) {
            alert('결재선 저장을 위해 결재선 이름을 입력하세요.');
            return;
        }

        // 2. 결재자 지정 여부 확인
        if (selectedApprovers.length === 0) {
            alert('결재자를 추가하세요.');
            return;
        }

        // 3. 템플릿 이름 중복 여부 체크
        $.ajax({
            url: '/approval/template/check-name',
            type: 'GET',
            data: { name: name },
            success: function (isDuplicate) {
                if (isDuplicate) {
                    alert("이미 존재하는 결재선 이름입니다. 다른 이름을 입력해주세요.");
                    return;
                }

                // 4. 중복이 아니면 결재선 저장 요청
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

</body>
</html>