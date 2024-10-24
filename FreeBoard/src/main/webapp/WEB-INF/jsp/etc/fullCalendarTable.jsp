<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="js/dist/index.global.js"></script>

<script>
    
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

        console.log(eventData); 
        var calendar = new FullCalendar.Calendar(calendarEl, {
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
                var title = prompt('Event Title:');
                if (title) {
                    // title, start, end 값 
                    console.log(arg);

                    // 화면에 출력 해줌
                    calendar.addEvent({
                        title : title,
                        start : arg.start,
                        end : arg.end,
                        allDay : arg.allDay
                    })
                    let start = today(arg.start, arg.allDay);
                    let end = today(arg.end, arg.allDay);
                    
                    fetch('addEvent.do?title=' + title + '&start=' + start + '&end=' + end)
                        .then(resolve => resolve.json())
                        .then(result => {
                            console.log("add result =>", result);
                            Swal.fire({
                                title: "Success!",
                                text: "Event added successfully!",
                                icon: "success"
                            });
                            location.reload();
                        })
                        .catch(err => console.log(err));
                }
                calendar.unselect()
            },
            eventClick : function(arg) {
                Swal.fire({
                        title: "Are you sure?",
                        text: "Are you sure you want to delete this event?",
                        icon: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#3085d6",
                        cancelButtonColor: "#d33",
                        confirmButtonText: "Yes, delete it!"
                    }).then((result) => {
                        console.log("arg => ", arg);
                        console.log("result => ", result);
                        //arg.event.remove()
                    });
            },
            editable : true,
            dayMaxEvents : true, // allow "more" link when too many events
            events : eventData
        });

        calendar.render();
        
        const today = (date, allDay) => {
            let today;
            if(allDay) {
                console.log(date)
                let year = date.getFullYear();
                let month = date.getMonth() + 1;
                let day = date.getDate();

                if (month < 10) month = '0' + month;
                if (day < 10) day = '0' + day;

                return (year + '-' + month + '-' + day);
            } else {
                let year = date.getFullYear();
                let month = date.getMonth() + 1;
                let day = date.getDate();
                let hours = date.getHours();
                let minutes = date.getMinutes();
                let seconds = date.getSeconds();

                if (month < 10) month = '0' + month;
                if (day < 10) day = '0' + day;
                if (hours < 10) hours = '0' + hours;
                if (minutes < 10) minutes = '0' + minutes;
                if (seconds < 10) seconds = '0' + seconds;

                return (year + '-' + month + '-' + day + 'T' + hours + ':' + minutes + ':' + seconds);
            }
            
        }

    });

    const deleteEvent = (eventId) => {
        fetch('deleteEvent.do?eventId=' + eventId)
            .then(resolve => resolve.json())
            .then(result => {
                    console.log("delete result =>", result);
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
</body>