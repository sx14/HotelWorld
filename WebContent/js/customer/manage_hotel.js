/**
 * Created by xu on 2017/2/10.
 */

function pass(hid) {
    var state = document.getElementById('form'+hid).firstElementChild;
    state.value='1';
}

function unpass(hid) {
    var state = document.getElementById('form'+hid).firstElementChild;
    state.value='0';
}


window.chartColors = {
    red: 'rgb(255, 99, 132)',
    orange: 'rgb(255, 159, 64)',
    yellow: 'rgb(255, 205, 86)',
    green: 'rgb(75, 192, 192)',
    blue: 'rgb(54, 162, 235)',
    purple: 'rgb(153, 102, 255)',
    grey: 'rgb(231,233,237)'
};


function loadGraph(data) {
    var config = {
        type: 'line',
        data: {
//            labels: ["January", "February", "March", "April", "May", "June", "July"],
             labels:data.date,
            datasets: [{
                label: "营业额(￥)",
                backgroundColor: window.chartColors.red,
                borderColor: window.chartColors.red,
                data : data.money,
                fill: false,
            }
            ]
        },
        options: {
            responsive: true,
            title:{
                display:true,
                text:'最近30日酒店营业额统计图'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: '日期(yyyy-MM-dd)'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: '营业额(￥)'
                    }
                }]
            }
        }
    };

    window.onload = function() {
        var ctx = document.getElementById("canvas").getContext("2d");
        window.myLine = new Chart(ctx, config);
    };
}