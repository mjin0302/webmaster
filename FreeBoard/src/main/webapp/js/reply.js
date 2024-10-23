/**
 * reply.js
 * 
 * replyService에서 생성했던 메소드를 활용
 */
// ------------------------------------------------------------------------------------------
// 댓글 등록 버튼
let addBtn = document.querySelector("#replyAddBtn");	// 버튼 찾기
addBtn.addEventListener('click', addReplyHandlerFunc);

// 댓글 등록 함수
function addReplyHandlerFunc(e) {
   let reply = document.querySelector('#reply').value;	// 댓글 내용
	
	if(!reply || !logId) {	// 댓글 내용이나 아이디 값이 없을 때
		Swal.fire({
			text: "필수값이 없습니다.",
			icon: "error"
		});
		return;
	}

   service.addReply( // 등록성공시
      {bno, reply, memberId: logId},
      result => {
         if(result.retCode == "OK") {
            Swal.fire({
               title: "Good job!",
               text: "등록성공!!",
               icon: "success"
            });
				page = 1;
				showList();	// 목록 다시 보여줌
				service.getReplyCount(bno, createPageList, err => console.log(err));

            reply.value = "";
         } else if(result.retCode == "FAIL") {
            Swal.fire({
               text: "등록중 오류 발생",
               icon: "error"
            });
         } else {
            Swal.fire({
               text: "알수 없는 코드",
               icon: "error"
            });
         }
         
      },
      err => console.log(err)	// 등록 실패시
   )	// end service.addReply
} // End addReplyHandlerFunc

// ------------------------------------------------------------------------------------------
let page = 1;
// pagination a태그 클릭이벤트
function linkMove() {
	document.querySelectorAll("nav ul.pagination a").forEach((ele) => {
		ele.addEventListener("click", (e) => {

			e.preventDefault();	// 페이지 이동 막기

			// 페이지 번호를 업데이트
			page = ele.dataset.page;
			showList();	// 댓글 목록 출력
			service.getReplyCount(bno, createPageList, err => console.log(err));
		})
	})
}

// 댓글 총 개수 불러옴
service.getReplyCount(bno, createPageList, err => console.log(err));

// 댓글 페이징 태그 만든 후 출력
function createPageList(result) { //page = 2
	let totalCnt = result.totalCnt;
	let startPage, endPage, realEnd;
	let prev, next;

	endPage = Math.ceil(page / 5) * 5; // 5page
	startPage = endPage -4;	// 1page
	realEnd = Math.ceil(totalCnt / 5);	// 7page
	endPage = endPage > realEnd ? realEnd : endPage;

	prev = startPage > 1;	// false
	next = endPage < realEnd;	// true

	let list = '';
	list += '<li class="page-item">';
	if(prev)	// 이전페이지 출력
		list += '	<a class="page-link" href="#" aria-label="Previous" data-page="' + (startPage - 1) + '">'
	else 
		list += '	<span class="page-link disabled" aria-label="Previous">'
	list += '		<span aria-hidden="true">&laquo;</span>'
	list += '	</span>'
	list += '</li>'
	for (let p = startPage; p <= endPage; p++) {
		list += '<li class="page-item"><a class="page-link" data-page="' + p + '">' + p + '</a></li>'
				
		document.querySelector("nav ul.pagination").innerHTML = list;
	}
	list += '<li class="page-item">';
	if(next)	// 다음 페이지면
		list += '	<a class="page-link" href="#" aria-label="Next" data-page="'+(endPage+1)+'">'
	else 
		list += '	<span class="page-link disabled" aria-label="Next">'
	list += '		<span aria-hidden="true">&raquo;</span>'
	list += '	</span>'
	list += '</li>'
	
	document.querySelector("nav ul.pagination").innerHTML = list;

	linkMove();
}

// 댓글 목록을 출력 함수
showList();
function showList() {
	// 출력목록을 화면에서 지우고 ..
	document.querySelectorAll("div.reply div.content li").forEach((tag, idx) => {
		if(idx > 0) 
			tag.remove();
	})

	// 목록 출력
	service.replylist({ bno, page }, // bno
		function(result) { // successFunc
			
			for(let i = 0; i < result.length; i++) {
				let template = replyListCreate(result[i]);
				document.querySelector(".reply ul").appendChild(template);
			}
		},
		function(error) { // errorFunc
			console.log(error);
		}
	) // end service.replylist
}	// end showList();

// 댓글 삭제 함수
function deleteReplyFunc(e, page) {
	// let btn = document.querySelector("#delBtn").parentElement.parentElement
	let rno = e.target.parentElement.parentElement.firstElementChild.innerText; // e.target 기준
	
	// 댓글 삭제 기능 호출
	service.removeReply(rno, // 삭제할 댓글번호
		result => {	// 정상처리 실행함수
			if(result.retCode == "OK") {
				e.target.parentElement.parentElement.remove();
				Swal.fire({
               title: "Good job!",
               text: "삭제 성공!!",
               icon: "success"
            });
				showList();	// 목록 다시 보여줌

				service.getReplyCount(bno, createPageList, err => console.log(err));

			} else if(result.retCode == "FAIL"){
				Swal.fire({
               text: "처리중 예외 발생",
               icon: "error"
            });
			} else {
				Swal.fire({
               text: "알수 없는 코드",
               icon: "error"
            });
			}
		},	
		err => console.log(err)	// 예외발생 실행함수

	) // End service.removeReply()

} // End deleteReplyFunc()

// ------------------------------------------------------------------------------------------

// li만들어서 return 해주는 함수
function replyListCreate(reply = {replyNo, reply, memberId}) {
	
	// 1. li 생성함
	let template = document.querySelector(".reply ul li").cloneNode(true); // cloneNode(true) => 복사
	// 2. 생성된 li형식에 댓글번호, 내용, 작성자, 삭제버튼 추가
	template.querySelector("span").innerText = reply.replyNo;
	template.querySelector("span:nth-of-type(2)").innerText = reply.reply;
	template.querySelector("span:nth-of-type(3)").innerText = reply.memberId;
	template.querySelector("span:nth-of-type(4)").innerHTML = "<button onclick='deleteReplyFunc(event)' id='delBtn' class='btn btn-outline-danger btn-sm'>삭제</button>";
	
	return template;
}





























// function makeList(data = []) {
// 	for(let i = 0; i < data.length; i++) {
// 		let tr = makeRow(obj[i]);
		
// 		document.querySelector("#replyList tbody").appendChild(tr);
// 	}
// } // End makeList()

// function makeRow(data = {}) {
// 	let fields = [ 'boardNo', 'reply', 'memberId' ];
// 	let tr = document.createElement("tr");
	
// 	for(let i = 0; i < data.length; i++) {
// 		let td = document.createElement("td");
// 		td.innerText = data[fields[i]];
// 		tr.appendChild(td);
// 	} // End makeRow()
	
// 	let td = document.createElement("td");
// 	let btn = document.createElement("button");
// 	btn.innerHTML = "삭제";
	
// 	btn.addEventListener("click", deleteRowFunc);
	
// 	td.appendChild(btn);
// 	tr.appendChild(td);
	
// 	return tr;
// };