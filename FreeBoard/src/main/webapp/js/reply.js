/**
 * reply.js
 * 
 * replyService에서 생성했던 메소드를 활용
 */
service.rlist(595, // bno
   function(result) { // successFunc
      console.log(result);

      result.forEach((ele) => {
            let tr = document.createElement("tr");
            
            Object.keys(ele).forEach((value) => {
               let td = document.createElement("td");
               td.innerText = ele[value];
               tr.appendChild(td);
            });
   
            let td = document.createElement("td")
            let button = document.createElement("button");
            button.innerText = "삭제"
   
            td.appendChild(button);
            tr.appendChild(td);
            
            document.querySelector("#replyList").appendChild(tr);
         });
      // let fields = [];
      // let btn = document.createElement("button");

      // for(let i = 0; i < result.length; i++) {
      //    console.log(result)
      //    for(let prop in result) {
      //       console.log(result[prop[i]]);
      //    }
      // }
      
      // for(let i = 0; i < result.length; i++) {
      //    let tr = document.createElement("tr");
      //    let td = document.createElement("td");
      // }
   }, 
   function(error) { // errorFunc
      console.log(error);
   }

)