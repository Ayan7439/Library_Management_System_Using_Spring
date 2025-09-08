const ctx = document.getElementById('booksChart').getContext('2d');

new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ['Books Issued till Date', 'Books Not Issued till Date'],
    datasets: [{
      data: [30, 70],
      backgroundColor: ['#2196f3', '#ccc'],
      borderWidth: 1
    }]
  },
  options: {
    cutout: '70%',
    plugins: {
      legend: {
        display: false
      },
      tooltip: {
        enabled: true
      }
    },
    maintainAspectRatio: false   // ✅ Let CSS control the size
  }
});




