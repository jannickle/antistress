// $(document).ready(function(){
//     //var arr = [['2016-10-01',10],['2016-10-02',20],['2016-10-03',12]];
//     //$.jqplot ('test_chart',[arr],
//     //var s3 = [['mandag',1],['tirsdag',3],['onsdag',5],['torsdag',3],['fredag',5]];
//     $.jqplot ('test_chart',[[['1',10],['2',20],['4',12]]],
//         {
//             title: 'Your Antristress Diary - Week 42',
//             axesDefaults: {
//                 labelRenderer: $.jqplot.CanvasAxisLabelRenderer
//             },
//             seriesDefaults: {
//                 showMarker:true,
//                 pointLabels: { show:true } ,
//                 rendererOptions: {
//                     smooth: false,
//                 }
//             },
//             axes: {
//                 xaxis: {
//                     renderer:$.jqplot.DateAxisRenderer,
//                     tickRenderer:$.jqplot.CanvasAxisTickRenderer,
//                     tickOptions:
//                         {
//                             // angle: -90,
//                             formatString:'%A'
//                         },
//                     label: 'Date',
//                     ticks : ['0','1','2','3','4','5','6'],
//                     pad:2
//                 },
//                 yaxis: {
//                     label: 'value',
//                     ticks : ['0','1','2','3','4','5','6','7','8','9','10']
//                 }
//             }
//         });
// });

function displayDiary(diary_entries){

    var plotdata = [];
    var datedata = [];

    var temp = 1;
    for(var i = 0; i < diary_entries.length; i++){
        plotdata.push([temp, diary_entries[i].morning]);
        datedata.push(diary_entries[i].date);
        temp++;
        plotdata.push([temp, diary_entries[i].afternoon]);
        datedata.push(diary_entries[i].date);
        temp++;
        plotdata.push([temp, diary_entries[i].evening]);
        datedata.push(diary_entries[i].date);
        datedata.push(diary_entries[i].date);
        temp++;
        temp++;
    }

    console.log(plotdata);
    console.log(datedata)

    $.jqplot.postDrawHooks.push(function() {
        var overlayCanvas = $($('.jqplot-overlayCanvas-canvas')[0])
        var seriesCanvas = $($('.jqplot-series-canvas')[0])
        seriesCanvas.detach();
        overlayCanvas.after(seriesCanvas);
    });

    $.jqplot ('test_chart', [plotdata],
    {
        title: 'Your Antristress Diary - Week 42',
        height: 500,
        width: 500,
        seriesStyles: {
            color: "#DBBCAF",
            lineWidth: 8,
            markerOptions: {
                show: false
            },
            linePattern: 'dashed'
        },
        axesDefaults: {
            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
            linePattern: 'dashed'
        },
        seriesDefaults: {
            rendererOptions: {
                smooth: false,
            }
        },
        canvasOverlay: {
            show: true,
            objects: [
                {verticalLine: {
                        name: 'barney1',
                        x: 1,
                        lineWidth: 3,
                        dashPattern: [16, 12],
                        lineCap: 'round',
                        linePattern: 'dashed',
                        color: 'rgb(100, 55, 124)',
                        shadow: false
                    }},
                {verticalLine: {
                        name: 'barney2',
                        x: 2,
                        lineWidth: 3,
                        dashPattern: [16, 12],
                        lineCap: 'round',
                        linePattern: 'dashed',
                        color: 'rgb(100, 55, 124)',
                        shadow: false
                    }},
                {verticalLine: {
                        name: 'barney3',
                        x: 3,
                        lineWidth: 3,
                        dashPattern: [16, 12],
                        lineCap: 'round',
                        linePattern: 'dashed',
                        color: 'rgb(100, 55, 124)',
                        shadow: false
                    }},
            ]
        },
        axes: {
            xaxis: {
                tickOptions:
                    {
                        formatter: function (format, value){
                            if (value % 4 === 0 || value === 0){
                                return " ";
                            } else if(value % 4 === 1){
                                return " ";
                            } else if (value % 2 === 1){
                                return " ";
                            } else {
                                return datedata[value];
                            }
                        },
                        showGridline: false
                    },
                label: 'Date',
                tickInterval: 1,
                min: 0,
                max: 28,
                numberTicks: 28,
            },
            x2axis: {
                show: true,
                tickRenderer:$.jqplot.AxisTickRenderer,
                tickOptions:{
                    formatter: function (format, value){
                        if (value % 4 === 0 || value === 0){
                            return " ";
                        } else if(value % 4 === 1){
                            return "M";
                        } else if(value % 2 === 0){
                            return "D";
                        } else if (value % 2 === 1){
                            return "A";
                        } else {
                            return " ";
                        }
                    },
                    showGridline: false,
                },
                ticks : ['0','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15',"16",'17','18','19','20','21','22','23','24','25','26','27','28'],
                tickInterval: 1,
                min: 1,
                max: 28,
                numberTicks: 28,
                // pad:2
            },
            yaxis: {
                tickOptions:{
                    formatter: function (format, value){
                        if(value === '0' || value === '11'){
                            return " ";
                        } else {
                            return value;
                        }
                    },
                    showGridline: false,
                },
                label: 'Stress',
                ticks : ['0','1','2','3','4','5','6','7','8','9','10','11'],
                tickSpacing: 75
            },
        },
    });
}

function getAccount(){
    $.ajax({
        async: false,
        url:"/user/api/getDiaryEntries",
        type:"GET",
        // contentType:"application/JSON",
        success:function (data){
            console.log("modtog data fra server: " + data.id);
            displayDiary(data.diaryEntries);
        },
        error:function (data){
            console.log("ERROR i svar fra server");
        }
    });
}

function checkjQuery(){
    if(typeof jQuery !== undefined){
        console.log("jQuery er loaded")
    }else {
        console.log("jQuery er IKKE loaded")
    }
}