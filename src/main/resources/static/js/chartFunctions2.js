function preventSaveFormFromSending(diaryForm){
    diaryForm.submit(function (event){
        event.preventDefault();
        thisProbablyDoesntWork();
    });
}

var diary_entries = getDiaries();
var labelsArr = getLabels(diary_entries);
var ratingsArr = getRatings(diary_entries);

function showDiary (){
    console.log(diary_entries)
    console.log(labelsArr)
    console.log(ratingsArr)

    var options = {
        type: 'line',
        data: {
            labels: labelsArr,
            datasets: [
                {
                    label: 'Belastnings niveau',
                    lineTension: 0.1,
                    fill: false,
                    backgroundColor: 'rgba(75, 192, 192, 0.4)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderJoinStyle: 'miter',
                    pointBorderColor: 'rgba(200, 192, 192, 1)',
                    pointBackgroundColor: '#fff',
                    pointBorderWidth: 2,
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: 'rgba(75, 192, 192, 1)',
                    pointHoverBorderColor: 'rgba(75, 192, 192, 1)',
                    pointHoverBorderWidth: 2,
                    pointRadius: 1,
                    pointHitRadius: 10,
                    xAxisID:'xAxis1',
                    data: ratingsArr,
                }]
        },
        options: {
            spanGaps: true,
            scales: {
                xAxes: [
                    {
                        id:'xAxis1',
                        type:"category",
                        position: 'top',
                        ticks:{
                            callback:function(value, index, values){
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
                                var time = label.split(";")[0];
                                var day = label.split(";")[1];
                                if(time === "D"){
                                    return day.substring(5, day.length);
                                }else{
                                    return null;
                                }
                            }
                        }
                    }
                ],
                yAxes: [
                    {
                        ticks: {
                            reverse: false,
                            min: 0,
                            max: 10,
                            // beginAtZero: true
                        },
                        gridLines: {
                            display: false
                        }
                    }]
            },
            dragData: true,
            dragDataRound: 0, // round to full integers (0 decimals)
            dragOptions: {
                showTooltip: true // Recommended. This will show the tooltip while the user
                // drags the datapoint
            },
            onDragStart: function (e, element) {
                console.log(e)
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
                // where e = event
            },
            hover: {
                onHover: function(e) {
                    // indicate that a datapoint is draggable by showing the 'grab' cursor when hovered
                    const point = this.getElementAtEvent(e)
                    if (point.length) e.target.style.cursor = 'grab'
                    else e.target.style.cursor = 'default'
                }
            }
        }
    };
    var ctx = document.getElementById('chartJSContainer').getContext('2d');
    window.test = new Chart(ctx, options);
}

function thisProbablyDoesntWork(){
    console.log("createUser er kaldet med " + ratingsArr);
    var savedDiary = parseChartData();
    $.ajax({
        url:"/user/api/saveDiary",
        type:"POST",
        contentType:"application/JSON",
        data: JSON.stringify(savedDiary),
        success:function (data){
            console.log("SUCCESS svar fra server")
            $("#status").html("Server: Dagbog er gemt!");
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

function getDiaries(){
    var result=[];
    $.ajax({
        async: false,
        url:"/user/api/getDiaryEntries",
        type:"GET",
        // contentType:"application/JSON",
        success:function (data){
            console.log("modtog data fra server: " + data.id);
            result = data.diaryEntries;
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
        j++;
        diaryToSave[i].afternoon = ratingsArr[j];
        j++;
        diaryToSave[i].evening = ratingsArr[j];
        j+=2;
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