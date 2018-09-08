var lineChartData = {
    //x座標
    labels: ["Mon", "Tue", "Wed", "Thr", "Fri", "Sat", "Sun"],
    datasets: [{
            //統計表的背景顏色
            fillColor: "rgba(0,0,255,0.4)",
            //統計表畫筆顏色
            strokeColor: "rgba(0,0,0,1)",
            //點的顏色
            pointColor: "black",
            //點邊框的顏色
            pointStrokeColor: "black",
            //滑鼠觸發時點的顏色
            pointHighlightFill: "red",
            //滑鼠觸發時點邊框的顏色
            pointHighlightStroke: "rgba(220,220,220,0.3)",
            //Y座標
            data: [17, 26, 35, 46, 56, 57, 68, 78, 85, 84, 95, 100]
        },
        {
            fillColor: "rgba(255,255,255,0)",
            strokeColor: "rgba(92,184,92,1)",
            pointColor: "rgba(23,126,23,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(200,187,205,1)",
            data: [22, 12, 11, 43, 2, 42, 51, 17, 18, 18, 44, 63]
        }
        // , {
        //     fillColor: "rgba(255,0,0,0.3)",
        //     strokeColor: "rgba(80,200,92,1)",
        //     pointColor: "rgba(50,126,23,1)",
        //     pointStrokeColor: "#fff",
        //     pointHighlightFill: "#fff",
        //     pointHighlightStroke: "rgba(151,23,205,1)",
        //     data: [50, 22, 70, 43, 32, 42, 51, 17, 18, 18, 44, 63]
        // }
    ]
}

window.onload = function () {
    var ctx = document.getElementById("canvas").getContext("2d");
    window.myLine = new Chart(ctx).Line(lineChartData, {
        responsive: true
    });
}