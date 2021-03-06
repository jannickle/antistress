let diary_entries = getDiaries();
let labelsArr = getLabels(diary_entries);
let ratingsArr = getRatings(diary_entries);
let notesArr = getNotes(diary_entries);

function checkjQuery(){
    if(typeof jQuery !== undefined){
        console.log("jQuery loaded")
    }else {
        console.log("jQuery er not loaded")
    }
}

function preventSaveFormFromSending(diaryForm){
    diaryForm.submit(function (event){
        event.preventDefault();
        postChartData();
    });
}

function getDiaries(){
    let result=[];
    $.ajax({
        async: false,
        url:"/user/api/getDiaryEntries/" + $("#this-year").val() + "/" + $("#this-week").val(),
        type:"GET",
        success:function (data){
            result = data;
            console.log(result);
        },
        error:function (data){
            console.log("ERROR: couldn't get diary loaded");
        }
    });
    return result;
}

function createGraph() {
    let config = {
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
            // title: {
            //     display: true,
            //     text: "Din ANTISTRESS Dagbog",
            //     fontSize: 18,
            //     fontFamily: "'Helvetica Neue'"
            // },
            tooltips: {
                enabled: false,
            },
            legend: {
                display: false,
            },
            spanGaps: true,
            responsive: true,
            maintainAspectRatio: true,
            aspectRatio: 1.5,
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
                if(e.type === 'touchstart'){
                    $(".popup").css({left: element._model.x + (screen.width / 10)});
                    $(".popup").css({top: element._model.y});
                } else {
                    $(".popup").css({left: e.pageX + 25});
                    $(".popup").css({top: e.pageY + 25});
                }
                $(".popup").show();
                $('#note').val(notesArr[element._index]);
                $('#exampleModalLabel').text("Note for dato: " + formatLabelforNote(labelsArr[element._index]));
                $('#show_modal').click(function (){
                    $('#exampleModal').modal('show');
                })
                $('#close_note').click(function (){
                    $('#exampleModal').modal('hide');
                })
                $('#save_note').click(function (){
                    notesArr[element._index] = $('#note').val();
                    $('#exampleModal').modal('hide');
                })
            },
            onDrag: function (e, datasetIndex, index, value) {

                // change cursor style to grabbing during drag action
                e.target.style.cursor = 'grabbing'
                // where e = event
            },
            onDragEnd: function (e, datasetIndex, index, value) {
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
            }
        }
    };
    let ctx = document.getElementById("chartJSContainer").getContext('2d')
    let chart = new Chart(ctx, config);
}

function formatLabelforNote(label){
    let TOD = label.split(";")[0];
    let full_date = label.split(";")[1];
    let time_of_day;
    if(TOD === "M"){
        time_of_day = "Morgen";
    } else if (TOD === "D"){
        time_of_day = "Dag";
    } else {
        time_of_day = "Aften";
    }
    let year = full_date.split("-")[0];
    let month = full_date.split("-")[1];
    let day = full_date.split("-")[2];
    let date = day + "-" + month + "-" + year;
    return date + " - " + time_of_day;
}

function postChartData(){
    console.log("createUser er kaldet med " + ratingsArr);
    let savedDiary = parseChartData();
    $.ajax({
        url:"/user/api/saveDiary",
        type:"POST",
        contentType:"application/JSON",
        data: JSON.stringify(savedDiary),
        success:function (data){
            console.log("SUCCESS: diary saved");
        },
        error:function (data){
            console.log("ERROR: couldn't save diary")
        }
    });
}

function getRatings(diaryEntries){
    let ratings = [];
    ratings.push(null);
    for(let i = 0; i < diaryEntries.length; i++){
        ratings.push(diaryEntries[i].morning);
        ratings.push(diaryEntries[i].afternoon);
        ratings.push(diaryEntries[i].evening);
        ratings.push(null);
    }
    return ratings;

}

function getLabels(diaryEntries){
    let labels = [];
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
    let notes = [];
    notes.push("");
    for(let i = 0; i < diaryEntries.length; i++){
        notes.push(diaryEntries[i].note1);
        notes.push(diaryEntries[i].note2);
        notes.push(diaryEntries[i].note3);
        notes.push("");
    }
    return notes;
}

function parseChartData(){
    let diaryToSave = diary_entries;
    let j = 1;
    for(let i = 0; i < diaryToSave.length; i++){
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
    return diaryToSave;
}

function addSleepInputs(){
    for(let i = 0; i < diary_entries.length; i++){
        $(function () {
            $('#sleep_day_' + i)
                .attr("value", diary_entries[i].sleep);
        });
    }
}

$('#load').click(function () {
    let element = document.getElementById('chart_container');
    let dimensions = element.getBoundingClientRect();
    let width = dimensions.width;
    let height = dimensions.height;
    let opt = {
        margin:       1,
        filename:     'myfile.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 1 },
        jsPDF:        { unit: 'pt', format: [width, height], orientation: 'landscape' }
    }
    $('#buttons').hide();
    html2pdf().set(opt).from(element).save().then(() => {
        $('#buttons').show();
    });
});

window.addEventListener('DOMContentLoaded', function () {
    createGraph();
    addSleepInputs()
}, false);
