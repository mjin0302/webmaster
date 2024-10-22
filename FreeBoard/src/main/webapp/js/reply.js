/**
 * reply.js
 * 
 * replyService에서 생성했던 메소드를 활용
 */
service.rlist(595, // bno
   function(result) { // successFunc
      makeList(result);
   },
   function(error) { // errorFunc
      console.log(error);
   }

) // end service.rlist

function makeList(data = []) {
	for(let i = 0; i < data.length; i++) {
		let tr = makeRow(obj[i]);
		
		document.querySelector("#replyList tbody").appendChild(tr);
	}
}

function makeRow(data = {}) {
	let fields = [ 'boardNo', 'reply', 'memberId' ];
	let tr = document.createElement("tr");
	
	for(let i = 0; i < data.length; i++) {
		let td = document.createElement("td");
		td.innerText = data[fields[i]];
		tr.appendChild(td);
	}
	
	let td = document.createElement("td");
	let btn = document.createElement("button");
	btn.innerHTML = "삭제";
	
	btn.addEventListener("click", deleteRowFunc);
	
	td.appendChild(btn);
	tr.appendChild(td);
	
	return tr;
};