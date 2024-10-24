<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>chart</title>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		
		var chartData = [
			["Writer", "Count per Writer"],
		];

		fetch('countByWriter.do')
			.then(resolve => resolve.json())
			.then(result => {
				console.log(result);
				result.forEach((row) => {
					console.log("row",row)
					chartData.push([row.name, row.count])
					google.charts.load(
						'current', 
						{ 'packages' : [ 'corechart' ] }
					);
					google.charts.setOnLoadCallback(drawChart);
				});
				console.log(chartData);
			})
			.catch(err => console.log(err));
		
		

		function drawChart() {
			// 2차원 배열 형태로 데이터를 받아와야한다 [[], [], [], []]
			var data = google.visualization.arrayToDataTable(chartData
				// [
				// 	[ 'Task', 'Hours per Day' ], 
				// 	[ 'Work', 	  11 ], 
				// 	[ 'Eat', 	  2 ],
				// 	[ 'Commute',  2 ], 
				// 	[ 'Watch TV', 2 ], 
				// 	[ 'Sleep',	  7 ] 
				// ]
			); 

			var options = {
				title : '사용자별 게시글 현황',
			};

			// piechart 
			var chart = new google.visualization.PieChart(document.getElementById('piechart'));

			chart.draw(data, options);
		}
	</script>											
</head>
<body>
	<div id="piechart" style="width: 900px; height: 500px;"></div>
</body>
</html>