/**
 * Javascript file to display BerlinClock
 */


/**
 * Establish Stomp connection and subscribe the event /topic for the status and clock timing.
 */
function connect() {
    var stompClient = null;
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/clockstatus', function(clockStatus){
            showBerlinClockStatus(clockStatus);
        });
        stompClient.subscribe('/topic/clocktime', function(clockTime){
            alert(clockTime)
            showBerlinClock(JSON.parse(clockTime.body));
        });
    });
}

/**
 * Update the clock status
 * @param message
 */
function showBerlinClockStatus(message){
    if(message) {
        var clockStatusDiv = document.getElementById('circle');
        if ("Y" == message) {
            clockStatusDiv.style.backgroundColor = 'Yellow';
        }
    }
}

/**
 * Build and displays the clock timing
 * @param lampState
 */
function showBerlinClock(lampState){
    var response = document.getElementById('berlinRecTable');
    response.innerHTML = '';
    for(var i=0;i < lampState.length; i++){
        var objLamp = lampState[i];
        var tr = document.createElement('tr');
        response.appendChild(tr);
        for(var j=0; j< objLamp.length;j++){
            if(objLamp[j]){
                var td = document.createElement('td');
                var color = document.createAttribute('id');
                color.value = 'circle'
                if("O" == objLamp[j].lampState){
                    td.style.backgroundColor='White';
                }else {
                    if("Y" == objLamp[j].lampColor){
                        td.style.backgroundColor='Yellow';
                    }else{
                        td.style.backgroundColor='Red';
                    }
                }
                td.setAttributeNode(color);
                response.appendChild(td);
            }
        }
    }
}
