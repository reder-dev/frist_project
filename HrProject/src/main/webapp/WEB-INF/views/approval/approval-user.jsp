<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.itwill.approval.dto.ApprovalSearchDTO" %>
<%
    ApprovalSearchDTO loginUser = new ApprovalSearchDTO();
    loginUser.setEmpId("22100003"); // 테스트용 사번
    session.setAttribute("loginUser", loginUser);
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>내 결재 진행 상황</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <style>
    body {
      font-family: 'Pretendard', sans-serif;
      margin: 30px;
      background: #f5f5f5;
    }
    p {
      font-weight: bold;
    }
    h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      background: #fff;
      box-shadow: 0 0 4px rgba(0,0,0,0.1);
    }
    th, td {
      border: 1px solid #ccc;
      padding: 10px;
      text-align: center;
      font-size: 0.95em;
    }
    .popup, .popup-overlay {
      display: none;
      position: fixed;
      z-index: 1000;
    }
    .popup-overlay {
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: rgba(0, 0, 0, 0.4);
    }
    .popup {
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      background: #fff;
      width: 600px;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 5px 20px rgba(0,0,0,0.2);
    }
    .popup .close-btn {
      position: absolute;
      top: 10px;
      right: 15px;
      cursor: pointer;
    }
    .comment-box {
      margin-top: 20px;
      background: #f9f9f9;
      padding: 10px;
      border-radius: 6px;
    }
    #pagination {
	  margin-top: 25px;
	  text-align: center;
	}
	
	#pagination button {
	  margin: 0 5px;
	  padding: 5px 12px;
	  border: 1px solid #007bff;
	  background-color: white;
	  color: #007bff;
	  border-radius: 5px;
	  cursor: pointer;
	  font-size: 0.95em;
	}
	
	#pagination button.active {
	  background-color: #007bff;
	  color: white;
	  font-weight: bold;
	}
	
	#pagination button:hover:not(.active) {
	  background-color: #f0f8ff;
	}
	button{
      padding: 7px 16px;
	  font-size: 15px;
	  background: #f0f0f0;
	  color: black;
	  border: none;
	  border-radius: 5px;
	  cursor: pointer;
    }
  </style>
</head>
<body>
  <h2>내 결재 진행 상황</h2>
  <table id="userApprovalTable">
    <thead>
      <tr>
        <th>순번</th>
        <th>요청일</th>
        <th>제목</th>
        <th>상세내용</th>
        <th>처리상태</th>
      </tr>
    </thead>
    <tbody id="userApprovalBody"></tbody>
  </table>
  <div id="pagination"></div>

  <!-- 팝업 -->
  <div class="popup-overlay" id="popupOverlay"></div>
  <div class="popup" id="popupWindow">
    <span class="close-btn" onclick="closePopup()">✖</span>
    <div id="popupContent">상세 정보 로딩 중...</div>
  </div>

  <script>
    const loginUserId = '<%= loginUser.getEmpId() %>';
    let approvalList = [];
    let itemsPerPage = 10;

    function loadUserApprovals() {
      $.ajax({
        url: '/approval/user-documents',
        method: 'GET',
        data: { requesterId: loginUserId },
        success: function(data) {
          approvalList = data;
          renderUserPage(1);
        }
      });
    }

    function renderUserPage(page) {
      const tbody = $('#userApprovalBody');
      tbody.empty();

      const start = (page - 1) * itemsPerPage;
      const end = start + itemsPerPage;
      const sliced = approvalList.slice(start, end);

      sliced.forEach((item, idx) => {
        const status = item.approvalStatus || '대기';
        const statusColor = status === '승인' ? 'blue' : status === '반려' ? 'orange' : 'gray';
        const row = '<tr>' +
          '<td>' + (start + idx + 1) + '</td>' +
          '<td>' + item.requestDate + '</td>' +
          '<td>' + item.documentTitle + '</td>' +
          '<td><button onclick="openPopup(\'' + item.approvalDocumentId + '\')">상세확인</button></td>' +
          '<td style="color:' + statusColor + '">' + status + '</td>' +
          '</tr>';
        tbody.append(row);
      });

      renderPagination(page);
    }

    function renderPagination(current) {
      const totalPages = Math.ceil(approvalList.length / itemsPerPage);
      const container = $('#pagination');
      container.empty();

      for (let i = 1; i <= totalPages; i++) {
        const btn = $('<button>').text(i).click(() => renderUserPage(i));
        if (i === current) btn.css({ background: '#007bff', color: 'white' });
        container.append(btn);
      }
    }

    function openPopup(documentId) {
      $.ajax({
        url: '/approval/user-detail?documentId=' + documentId,
        method: 'GET',
        success: function(detail) {
          let html = '';
          html += '<h3>[' + detail.documentTitle + ']</h3>';
          html += '<p>요청일: ' + detail.formattedRequestDate + '</p>';
          html += '<p>요청 코멘트: ' + (detail.comment || '-') + '</p>';

          if (detail.referenceTableName === 'LEAVE') {
            html += '<p>휴가 구분: ' + detail.leaveStatus + '</p>';
            html += '<p>기간: ' + detail.formattedLeaveStartDate + ' ~ ' + detail.formattedLeaveEndDate + ' (' + detail.leaveDays + '일)</p>';
          } else if (detail.referenceTableName === 'BUSINESS') {
            html += '<p>출장지: ' + detail.tripLocation + '</p>';
            html += '<p>출장 목적: ' + detail.businessTripPurpose + '</p>';
            html += '<p>기간: ' + detail.formattedBusinessTripStart + ' ~ ' + detail.formattedBusinessTripEnd + ' (' + detail.tripDays + '일)</p>';
          }

          if (detail.files && detail.files.length > 0) {
            html += '<p>첨부파일:</p><ul>';
            detail.files.forEach(file => {
            	html += '<li><a href="/approval/download?fileId=' + file.fileId + '">' + file.fileName + '</a></li>';
            });
            html += '</ul>';
          }
		
          if (detail.approvers && detail.approvers.length > 0) {
        	  html += '<p><strong>결재선:</strong><br>';
        	  const approverLine = detail.approvers.map((a, idx) => {
        	    return (idx + 1) + '. ' + a.name + ' (' + a.dept + ' / ' + a.position + ') (' + a.status + ')';
        	  }).join(' → ');
        	  html += approverLine + '</p>';
        	}
          
          if (detail.approverComments && detail.approverComments.length > 0) {
            html += '<div class="comment-box">';
            html += '<h4>&lt;결재 코멘트&gt;</h4>';
            html += '<ul>';
            detail.approverComments.forEach(c => {
              if (c.comment) {
                html += '<li><b> ' + c.empName + '</b> : ' + c.comment + '</li>';
              }
            });
            html += '</ul></div>';
          }

          $('#popupContent').html(html);
          $('#popupOverlay').show();
          $('#popupWindow').show();
        }
      });
    }

    function closePopup() {
      $('#popupOverlay').hide();
      $('#popupWindow').hide();
    }

    $(document).ready(function() {
      loadUserApprovals();
    });
  </script>
</body>
</html>