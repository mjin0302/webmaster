<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="js/dist/index.global.js"></script>

<script>
    let modalArg = null; // arg데이터를 담아놓고 공유하기 위해서
    let calendar = null;
    document.addEventListener('DOMContentLoaded', async function() {
        var calendarEl = document.getElementById('calendar');
        
        // Ajax 호출
        // <!-- ^ new Promise(function(){}, function(){}) : 첫 번째엔 성공시, 두번째는 실패시 --> 
        // <!-- ^프라미스 객체가 반환될 때 await 수행코드가 있으면 그 다음 코드 실행 -->
        let eventData = [];
        let resolve = await fetch('fullCalendarData.do') // 
        let result = await resolve.json();  //.then(resolve => resolve.json())
        eventData = result;         // .then(result => {
                                    //     eventData = result;
                                    //     console.log("result =>", result);    
                                    //     fullCalendarFunc(eventData);
                                    // })
                                    // .catch(err => console.log(err));
        calendar = new FullCalendar.Calendar(calendarEl, {
            headerToolbar : {
                left : 'prev,next today',
                center : 'title',
                right : 'dayGridMonth,timeGridWeek,timeGridDay'
            },
            initialDate : todayDate(),
            navLinks : true, // can click day/week names to navigate views
            selectable : true,
            selectMirror : true,
            select : function(arg) {
                modalShow(arg);
                // var title = prompt('Event Title:');
                //if (title) {
                    
                    // title, start, end 값
                    // 화면에 출력 해줌
               // }
                calendar.unselect()
            },
            eventClick : function(arg) {
                console.log("arg => ",arg)
                Swal.fire({
                    title: "Are you sure?",
                    text: "Are you sure you want to delete this event?",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#3085d6",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Yes, delete it!"
                }).then((result) => {

                    
                    fetch('removeEvent.do?title=' + arg.event._def.title + '&start=' + today(arg.event.startStr, arg.event._def.allDay) + '&end=' + today(arg.event.endStr, arg.event._def.allDay))
                    .then(resolve => resolve.json())
                    .then(result => {
                        if(result.retCode == "OK") {
                            Swal.fire({
                                text: "일정이 삭제되었습니다.",
                                icon: "success"
                            });
                            arg.event.remove();
                        } else if(result.retCode == "FAIL") {
                            Swal.fire({
                                title: "삭제 실패!",
                                text: "삭제 중 예외 발생!",
                                icon: "error"
                            });
                        }
                    })
                    .catch(err => console.log(err));
                    //
                });
            },
            editable : true,
            dayMaxEvents : true, // allow "more" link when too many events
            events : eventData
        });

        calendar.render();
        
        const today = (date, allDay) => {
            let event = new Date(date);

            let today;
            if(allDay) {
                let year = event.getFullYear();
                let month = event.getMonth() + 1;
                let day = event.getDate();

                if (month < 10) month = '0' + month;
                if (day < 10) day = '0' + day;

                return (year + '-' + month + '-' + day);
            } else {
                let year = event.getFullYear();
                let month = event.getMonth() + 1;
                let day = event.getDate();
                let hours = event.getHours();
                let minutes = event.getMinutes();
                let seconds = event.getSeconds();

                if (month < 10) month = '0' + month;
                if (day < 10) day = '0' + day;
                if (hours < 10) hours = '0' + hours;
                if (minutes < 10) minutes = '0' + minutes;
                if (seconds < 10) seconds = '0' + seconds;
                console.log(year + '-' + month + '-' + day + 'T' + hours + ':' + minutes + ':' + seconds);
                return (year + '-' + month + '-' + day + 'T' + hours + ':' + minutes + ':' + seconds);
            }
            
        }

    });

    const deleteEvent = (eventId) => {
        fetch('deleteEvent.do?eventId=' + eventId)
            .then(resolve => resolve.json())
            .then(result => {
                    Swal.fire({
                        title: "Success!",
                        text: "Event deleted successfully!",
                        icon: "success"
                    });
                    location.reload();
                })
            .catch(err => console.log(err));
    }

    const todayDate = () => {
        let date = new Date();
        let year = date.getFullYear(); 
        let month = date.getMonth() + 1;
        let day = date.getDate();

        let today = year + '-' + month + '-' + day;
        if (month < 10) month = '0' + month;
        return today;
    }
	
</script>

<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<div id="calendar"></div>

    <!-- 모달창 열기 -->
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Launch demo modal
    </button>
    
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- title, startStr, endStr -->
                <label for="title">제목</label>
                <input type="text" name="title" id="title"><br>
                <label for="start">시작일시 </label>
                <input type="date" name="start" onchange="startChange(event)" id="start"><br>
                <label for="end">종료일시 </label>
                <input type="date" name="end" onchange="endChange(event)" id="end"><br>
            </div>
            <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" onclick="modalSave()">Save changes</button>
            </div>
        </div>
        </div>
    </div>

    <script src="js/calendarModal.js"></script>
</body>
