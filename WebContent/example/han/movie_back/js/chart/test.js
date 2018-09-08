            // y 軸的顯示
            var yAxis = [];
            // 資料集合，之後只要更新這個就好了。
            var datas = [];
            var ctx = document.getElementById('canvasLine').getContext('2d');
            var lineChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: yAxis,
                    datasets: [{
                        label: '測試資料',
                        data: datas,
                        backgroundColor: "rgba(0,148,255,0.4)"
                    }]
                }
            });

            //時間格式
            var timeFormat = 'HH:mm:ss';

            function appendData() {
                //超過10 個，就把最早進來的刪掉
                if (yAxis.length > 10) {
                    yAxis.shift();
                    datas.shift();
                }

                //推入y 軸新的資料 
                yAxis.push(new moment().format(timeFormat));

                //推入一筆亂數進資料
                datas.push(Math.floor(Math.random() * 100) + 1);

                //更新線圖
                lineChart.update();
            }

            //每秒做一次
            setInterval(appendData, 1000);