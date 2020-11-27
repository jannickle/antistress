$(document).ready(function(){
    //var arr = [['2016-10-01',10],['2016-10-02',20],['2016-10-03',12]];
    //$.jqplot ('test_chart',[arr],
    //var s3 = [['mandag',1],['tirsdag',3],['onsdag',5],['torsdag',3],['fredag',5]];
    var s3 = [['1',1],['2',3],['3',5],['4',3],['5',5]];
    $.jqplot.config.enablePlugins = true;
    $.jqplot ('test_chart',[s3],
        {
            title: 'Example',
            axesDefaults: {
                labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                //renderer: $.jqplot.DateAxisRenderer,

                dragable: {
                    color: undefined,
                    constrainTo: 'y',
                    fixed: 'y',
                },
            },
            seriesDefaults: {


                    constrainTo: 'y',
                    fixed: 'y',

                showMarker:true,
                pointLabels: { show:true } ,
                rendererOptions: {
                    smooth: true
                },

            },
            grid:{
                background:"#0047cb",
                shadow:"false",
                opacity:"90"},
            // },cursor: {
            //     show: true,
            //
            // },
            // highlighter: {
            //     sizeAdjust: 10,
            //     tooltipLocation: 'n',
            //     tooltipAxes: 'x',
            //     tooltipFormatString: '<b><i><span style="color:#ff0000;">nice</span></i></b>',
            //     useAxesFormatters: false
            // },
            axes: {
                xaxis: {
                    //renderer:$.jqplot.DateAxisRenderer,
                   tickRenderer:$.jqplot.CanvasAxisTickRenderer,
                    tickOptions:
                        {
                            angle: 0,


                        },
                    label: 'Date',
                    tickInterval: 1,
                    min:0, max: 8,
                    numberTicks: 8,

                },

                yaxis: {
                    renderer: $.jqplot.CanvasAxisLabelRenderer,
                    tickOptions:
                        {
                            formatString:'%d'
                        },
                    min: 0,
                    max:10,
                    interval:1,
                    numberTicks: 10,
                    label: 'value',

                    //ticks : ['0','1','2','3','4','5','6','7','8','9','10'],
                    pad: 0
                }
            }
        });
});