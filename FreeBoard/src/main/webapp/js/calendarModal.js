/**
 * calendarModal.js
 */

let modal = document.querySelector("#exampleModal");
let divEle = document.createElement("div");
let bodyEle = document.querySelector("body");
function modalShow(arg) {
   modalArg = arg; // 여러 함수에서 사용할 용도
   // body 태그
   
   bodyEle.className = "modal-open";
   bodyEle.style.overflow = 'hidden';
   bodyEle.style.paddingRight = '16px'
   
   // modal 태그
   
   modal.classList.add("show");
   modal.setAttribute("aria-modal", true);
   modal.setAttribute("role", "dialog");
   modal.removeAttribute("aria-hidden");
   modal.style.display = "block";

   
   divEle.classList.add("modal-backdrop", "fade", "show");

   bodyEle.appendChild(divEle);

   document.querySelector("#start").value = modalArg.startStr;
   document.querySelector("#end").value = modalArg.endStr;

   let btn =  document.querySelector(".btn-close")
   btn.addEventListener("click", modalHide);
   
}

function modalHide() {

   bodyEle.className = '';
   bodyEle.style.overflow = '';

   divEle.style.display = "none"

   modal.classList.remove("show");
   modal.removeAttribute("aria-modal");
   modal.removeAttribute("role");
   modal.style.display = "none";

}

function modalSave() {
   // title, startStr, endStr
   let title = document.querySelector("#title").value;
   let start = document.querySelector("#start").value;
   let end = document.querySelector("#end").value;
   console.log("title => ", title, " start => ", start, " end => ", end)
   if(title != "" || title != null || title != 'undefined' ) {
      fetch('addEvent.do?title=' + title + '&start=' + start + '&end=' + end)
         .then(resolve => resolve.json())
         .then(result => {
            if(result.retCode == "OK") {
               Swal.fire({
                  title: "Success!",
                  text: "Event added successfully!",
                  icon: "success"
               });
               calendar.addEvent({
                  title : title,
                  start : modalArg.start,
                  end : modalArg.end,
                  allDay : modalArg.allDay
               })
            } else if(result.retCode == "FAIL") {
               Swal.fire({
                  title: "등록 실패!",
                  text: "등록 중 예외 발생!",
                  icon: "error"
               });
            }
            
         })
         .catch(err => console.log(err));
   } else {
      if(title == null || title == 'undefined' || title == "") {
         Swal.fire({
            title: "제목을 입력하세요",
            icon: "error"
         });
         title = document.querySelector("#title").focus()
      }
   }
   
}

function startChange(event) {

   console.log(event);
   modalArg.start = new Date(event.target.value);

}

function endChange(event) {

   console.log(event);
   modalArg.end = new Date(event.target.value);

}