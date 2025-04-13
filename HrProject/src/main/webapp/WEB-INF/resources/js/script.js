/**
 * 
 */
// 대시보드 차트 초기화
document.addEventListener('DOMContentLoaded', function() {
  // 직원 현황 차트
  const employeeChartEl = document.getElementById('employeeChart');
  if (employeeChartEl) {
    const employeeChart = new Chart(employeeChartEl, {
      type: 'bar',
      data: {
        labels: ['개발팀', '영업팀', '인사팀', '재무팀', '마케팅팀'],
        datasets: [{
          label: '직원 수',
          data: [12, 19, 8, 5, 10],
          backgroundColor: [
            'rgba(54, 162, 235, 0.5)',
            'rgba(255, 99, 132, 0.5)',
            'rgba(255, 206, 86, 0.5)',
            'rgba(75, 192, 192, 0.5)',
            'rgba(153, 102, 255, 0.5)'
          ],
          borderColor: [
            'rgba(54, 162, 235, 1)',
            'rgba(255, 99, 132, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  // 근태 현황 차트
  const attendanceChartEl = document.getElementById('attendanceChart');
  if (attendanceChartEl) {
    const attendanceChart = new Chart(attendanceChartEl, {
      type: 'doughnut',
      data: {
        labels: ['정상 출근', '지각', '결근', '휴가'],
        datasets: [{
          label: '근태 현황',
          data: [42, 5, 2, 8],
          backgroundColor: [
            'rgba(75, 192, 192, 0.5)',
            'rgba(255, 206, 86, 0.5)',
            'rgba(255, 99, 132, 0.5)',
            'rgba(54, 162, 235, 0.5)'
          ],
          borderColor: [
            'rgba(75, 192, 192, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)'
          ],
          borderWidth: 1
        }]
      }
    });
  }
});

// 사진 미리보기 기능
document.addEventListener('DOMContentLoaded', function() {
  const photoFileInput = document.getElementById('photoFile');
  if (photoFileInput) {
    photoFileInput.addEventListener('change', function(e) {
      const file = e.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
          const previewImg = document.querySelector('.card-body img');
          if (previewImg) {
            previewImg.src = e.target.result;
          }
        };
        reader.readAsDataURL(file);
      }
    });
  }
});
