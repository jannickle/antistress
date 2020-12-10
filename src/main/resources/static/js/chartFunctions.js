// let ctx;
let chart;

var diary_entries = getDiaries();
var labelsArr = getLabels(diary_entries);
var ratingsArr = getRatings(diary_entries);
var notesArr = getNotes(diary_entries);

function createGraph() {

    var config = {
        type: 'line',
        data: {
            labels: labelsArr,
            datasets: [
                {
                    label: 'Belastnings niveau',
                    lineTension: 0.2,
                    fill: false,
                    backgroundColor: 'rgba(158, 35, 22, 0.7)',
                    borderColor: 'rgba(158, 35, 22, 1)',
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderJoinStyle: 'miter',
                    pointBorderColor: 'rgba(58, 58, 58, 1)',
                    pointBackgroundColor: 'rgba(158, 35, 22, 0.7)',
                    pointBorderWidth: 4,
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: 'rgba(158, 35, 22, 0.7)',
                    pointHoverBorderColor: 'rgba(158, 35, 22, 1)',
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 10,
                    xAxisID:'xAxis1',
                    data: ratingsArr,
                }]
        },
        options: {
            title: {
                display: true,
                text: "Din ANTISTRESS Dagbog",
                fontSize: 18,
                fontFamily: "'Helvetica Neue'"
            },
            tooltips: {
                enabled: false,
            },
            legend: {
                display: false,
            },
            spanGaps: true,
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                xAxes: [
                    {
                        id:'xAxis1',
                        type:"category",
                        position: 'top',
                        ticks:{
                            callback: function(value, index, values){
                                if((index % 4)) {
                                    var time = value.split(";")[0];
                                    var day = value.split(";")[1];
                                    return time;
                                } else {
                                    return null;
                                }
                            }
                        }
                    },
                    {
                        id:'xAxis2',
                        type:"category",
                        gridLines: {
                            drawOnChartArea: false, // only want the grid lines for one axis to show up
                        },
                        ticks:{
                            callback:function(label){
                                var time_of_day = label.split(";")[0];
                                var full_date = label.split(";")[1];
                                if(time_of_day === "D"){
                                    var month_day = full_date.substring(5, full_date.length);
                                    var month = month_day.split("-")[0];
                                    var day = month_day.split("-")[1];
                                    return day + "/" + month;
                                }else{
                                    return null;
                                }
                            }
                        }
                    }
                ],
                yAxes: [
                    {
                        id: 'yAxis1',

                        ticks: {
                            // reverse: false,
                            display: false,
                            min: 0,
                            max: 10,
                            beginAtZero: true,
                            // userCallback: function(label, index, labels) {
                            //     // when the floored value is the same as the value we have a whole number
                            //     if (Math.floor(label) === label) {
                            //         return label;
                            //     }
                            //
                            // },
                        },
                        gridLines: {
                            display: false
                        }
                    }]
            },
            dragData: true,
            dragDataRound: 0, // round to full integers (0 decimals)
            onDragStart: function (e, element) {
                console.log(e);
                console.log(element);
                console.log(e.type);
                if(e.type === 'touchstart'){
                    console.log(e.touches[0])
                    $(".popup").css({left: element._model.x + (screen.width / 10)});
                    $(".popup").css({top: element._model.y});
                } else {
                    $(".popup").css({left: element._model.x + (screen.width / 30)});
                    $(".popup").css({top: element._model.y});
                }
                $(".popup").show();
                $('#note').val(notesArr[element._index]);
                $('#exampleModalLabel').text("Note for dato: " + formatLabelforNote(labelsArr[element._index]));
                $('#show_modal').click(function (){
                    console.log('show modal button pressed')
                    $('#exampleModal').modal('show');
                })
                $('#save_note').click(function (){
                    console.log('show modal button pressed')
                    notesArr[element._index] = $('#note').val();
                    $('#exampleModal').modal('hide');
                })
            },
            onDrag: function (e, datasetIndex, index, value) {

                // console.log(datasetIndex, index, value)
                // change cursor style to grabbing during drag action
                e.target.style.cursor = 'grabbing'
                // where e = event
            },
            onDragEnd: function (e, datasetIndex, index, value) {
                // console.log(datasetIndex, index, value)
                // restore default cursor style upon drag release
                e.target.style.cursor = 'default'
                // // where e = event
                // setTimeout(function (){
                //     $('#exampleModal').modal('hide');
                // }, 2000);
            },
            hover: {
                onHover: function(e) {
                    // indicate that a datapoint is draggable by showing the 'grab' cursor when hovered
                    const point = this.getElementAtEvent(e)
                    if (point.length) e.target.style.cursor = 'grab'
                    else e.target.style.cursor = 'default'
                }
            },
            onClick: function (element, dataAtClick) {
                let scaleRef,
                    valueX,
                    valueY;
                for (var scaleKey in this.scales) {
                    scaleRef = this.scales[scaleKey];
                    if (scaleRef.isHorizontal() && scaleKey === 'xAxis1') {
                        valueX = scaleRef.getValueForPixel(element.offsetX);
                    } else if (scaleKey === 'yAxis1') {
                        valueY = Math.round(scaleRef.getValueForPixel(element.offsetY));
                    }
                }
                if(valueY <= 10 && ratingsArr[valueX] == null && labelsArr[valueX] !== ""){
                    ratingsArr[valueX] = valueY;
                    this.update();
                }
                else if (valueY <= 10 && (ratingsArr[valueX] >= 0 || ratingsArr[valueX] <= 10) && labelsArr[valueX] !== ""){

                }
            }
        }
    };

    var ctx = document.getElementById("chartJSContainer").getContext('2d')
    ctx.fillText("Dato", 0, 0);

    chart = new Chart(ctx, config);
}

function formatLabelforNote(label){
    var TOD = label.split(";")[0];
    var full_date = label.split(";")[1];

    let time_of_day;

    if(TOD === "M"){
        time_of_day = "Morgen";
    } else if (TOD === "D"){
        time_of_day = "Dag";
    } else {
        time_of_day = "Aften";
    }

    var year = full_date.split("-")[0];
    var month = full_date.split("-")[1];
    var day = full_date.split("-")[2];

    var date = day + "-" + month + "-" + year;

    return date + " - " + time_of_day;
}


function postChartData(){
    console.log("createUser er kaldet med " + ratingsArr);
    var savedDiary = parseChartData();
    $.ajax({
        url:"/user/api/saveDiary",
        type:"POST",
        contentType:"application/JSON",
        data: JSON.stringify(savedDiary),
        success:function (data){
            console.log("SUCCESS svar fra server");
            console.log(savedDiary);
            $("#status").html("Dagbog er gemt!");
        },
        error:function (data){
            console.log("ERROR i svar fra server")
        }
    });
}

function getRatings(diaryEntries){
    var ratings = [];
    ratings.push(null);
    for(var i = 0; i < diaryEntries.length; i++){
        ratings.push(diaryEntries[i].morning);
        ratings.push(diaryEntries[i].afternoon);
        ratings.push(diaryEntries[i].evening);
        ratings.push(null);
    }
    return ratings;

}

function getLabels(diaryEntries){
    var labels = [];
    labels.push("");
    for(var i = 0; i < diaryEntries.length; i++){
        labels.push('M' + ';' + diaryEntries[i].date);
        labels.push('D' + ';' + diaryEntries[i].date);
        labels.push('A' + ';' + diaryEntries[i].date);
        labels.push("");
    }
    return labels;
}

function getNotes(diaryEntries){
    var notes = [];
    notes.push("");
    for(var i = 0; i < diaryEntries.length; i++){
        notes.push(diaryEntries[i].note1);
        notes.push(diaryEntries[i].note2);
        notes.push(diaryEntries[i].note3);
        notes.push("");
    }
    console.log(notes);
    return notes;
}

function getDiaries(){
    var result=[];
    $.ajax({
        async: false,
        url:"/user/api/getDiaryEntries",
        type:"GET",
        success:function (data){
            result = data;
            console.log(data);
        },
        error:function (data){
            console.log("ERROR i svar fra server");
        }
    });
    return result;
}

function parseChartData(){
    var diaryToSave = diary_entries;
    var j = 1;
    for(var i = 0; i < diaryToSave.length; i++){
        diaryToSave[i].morning = ratingsArr[j];
        diaryToSave[i].note1 = notesArr[j];
        j++;
        diaryToSave[i].afternoon = ratingsArr[j];
        diaryToSave[i].note2 = notesArr[j];
        j++;
        diaryToSave[i].evening = ratingsArr[j];
        diaryToSave[i].note3 = notesArr[j];
        j+=2;
        diaryToSave[i].sleep = parseInt($("#sleep_day_" + i).val());
    }
    console.log(diaryToSave);
    return diaryToSave;
}

function checkjQuery(){
    if(typeof jQuery !== undefined){
        console.log("jQuery er loaded")
    }else {
        console.log("jQuery er IKKE loaded")
    }
}

function preventSaveFormFromSending(diaryForm){
    diaryForm.submit(function (event){
        event.preventDefault();
        postChartData();
    });
}

function addSleepInputs(){
    for(let i = 0; i < diary_entries.length; i++){
        $(function () {
            $("<input type='number' min='1' max='10'/>")
                .attr("value", diary_entries[i].sleep)
                .attr("id", "sleep_day_" + i)
                .appendTo("#sleepInput" + i);
        });
    }
}

// $(window).click(function(e) {
//     $(".popup").css({left: e.pageX});
//     $(".popup").css({top: e.pageY});
//     $(".popup").show();
// });

window.addEventListener('DOMContentLoaded', function () {
    addSleepInputs()
    createGraph();
}, false);