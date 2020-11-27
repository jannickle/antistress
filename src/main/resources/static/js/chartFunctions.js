$(document).ready(function(){
    //var arr = [['2016-10-01',10],['2016-10-02',20],['2016-10-03',12]];
    //$.jqplot ('test_chart',[arr],
    //var s3 = [['mandag',1],['tirsdag',3],['onsdag',5],['torsdag',3],['fredag',5]];
    $.jqplot ('test_chart',[[['1',10],['4',20],['3',12]]],
        {
            title: 'Example',
            axesDefaults: {
                labelRenderer: $.jqplot.CanvasAxisLabelRenderer
            },
            seriesDefaults: {
                showMarker:true,
                pointLabels: { show:true } ,
                rendererOptions: {
                    smooth: true
                }
            },
            axes: {
                xaxis: {
                    renderer:$.jqplot.DateAxisRenderer,
                    tickRenderer:$.jqplot.CanvasAxisTickRenderer,
                    tickOptions:
                        {
                            angle: -90,
                            formatString:'%A'
                        },
                    label: 'Date',
                    ticks : ['0','1','2','3','4','5','6'],
                    pad:2
                },
                yaxis: {
                    label: 'value',
                    ticks : ['0','5','10','15','20','25','30','35']
                }
            }
        });
});