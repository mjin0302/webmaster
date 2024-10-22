/**
 * members.js
 */
// jsp => req.getAttribute()

// "등록" 버튼에 이벤트 추가
document.querySelector("#addBtn").addEventListener("click", (e) => {
   let id = document.querySelector("#mid").value;
   let pass = document.querySelector("#mpass").value;
   let name = document.querySelector("#mname").value;
   let phone = document.querySelector("#mphone").value;
   
   // 등록
   fetch('addMemberJson.do?id='+id+'&pass='+pass+'&name='+name+'&phone='+phone)
      .then((resolve) => resolve.json())
      .then((result) => { 
         if(result.retCode === 'OK') {
            Swal.fire({
               title: "Good job!",
               text: "등록성공!!",
               icon: "success"
            });
            let tr = makeRow({memberId:id, memberName:name, phone:phone});
            document.querySelector("#show tbody").appendChild(tr);
         } else {
            Swal.fire({
               title: "Good job!",
               text: "",
               icon: "error"
            });
         }
	  	})
      .catch((err) => { 
         Swal.fire({
            title: "Good job!",
            text: err,
            icon: "error"
         });
      })
});



// 행을 만드는 함수
function makeRow(obj = {}) {
   let fields = ['memberId', 'memberName', 'phone'];
   let tr = document.createElement('tr');

   tr.setAttribute("data-id", obj.memberId);

   for(let i = 0; i < fields.length; i++) {
      let td = document.createElement('td');
      td.innerText = obj[fields[i]];
      tr.appendChild(td);
   }

   let td = document.createElement("td")
   let button = document.createElement("button");
   button.innerText = "삭제"

   button.addEventListener("click", deleteRowFunc);

   td.appendChild(button);
   tr.appendChild(td);

   return tr;
}

// 삭제 하는 함수
function deleteRowFunc(e) {
   console.log(e.target.parentElement.parentElement.firstElementChild.innerText);
   console.dir(e.target.parentElement.parentElement.dataset.id) //dataset => data속성 읽어오는 컬렉션임

   let id = e.target.parentElement.parentElement.dataset.id;
   
   fetch('removeMemberJson.do?id=' + id)
      .then(resolve => resolve.json())
      .then(result => {
         if(result.retCode == 'OK') {
            Swal.fire({
               title: "Success",
               text: id + "지우기 성공!!",
               icon: "warning"
            });
            e.target.parentElement.parentElement.remove();
         } else {
            Swal.fire({
               text: "처리중 에러 발생",
               icon: "error"
            });
         }
      })
}

// JSON형태의 회원목록을 출력하는 데이터가 필요함
fetch('memberJson.do')
   .then((resolve) => {
      return resolve.json();
   })
   .then((result) => makeList(result) )
   .catch((err) => {
      Swal.fire({
         title: "Good job!",
         text: err,
         icon: "error"
      });
   });

// 전체 목록 출력
function makeList(obj = []) {
   for(let i = 0; i < obj.length; i++) {
      let tr = makeRow(obj[i]);
      document.querySelector("#show tbody").appendChild(tr);
   }
}
   // const makeList = (obj = []) => {
   //    obj.forEach((ele) => {
   //       let tr = document.createElement("tr");

   //       Object.keys(ele).forEach((value) => {
   //          let td = document.createElement("td");
   //          td.innerText = ele[value];
   //          tr.appendChild(td);
   //       });

   //       let td = document.createElement("td")
   //       let button = document.createElement("button");
   //       button.innerText = "삭제"

   //       td.appendChild(button);
   //       tr.appendChild(td);
         
   //       document.querySelector("#show tbody").appendChild(tr);
   //    });
   // }

   