/**
 * json.js
 * {name: "홍길동", age: 20} => object
 * {"name": "홍길동", "age": 20}=> json object
 * json 문자열 => 자바스크립트 객체 => json 문자열.
 * <div id="show">
	<table class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>first_name</th>
				<th>last_name</th>
				<th>email</th>
				<th>gender</th>
				<th>salary</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
</div>
 * 
 */

// JSP => 페이지 출력.
// JSON 데이터 => 서버에서 보내준 데이터를 화면에서 보이게 만들어줌

let obj = { name: "홍길동", age: 20 };
let json = JSON.stringify(obj); // stringify()함수 : object를 string으로
obj = JSON.parse(json);   // parse()함수 : string을 object로

fetch('js/MOCK_DATA.json') // 해당 파일을 읽어와서 .then에 전달함
   // then메소드는 함수를 가짐
   .then((resolve) => { // resolve에는 MOCK_DATA.json이 담겨있음
      console.log("resolve => ", resolve);
      return resolve.json(); // 원래는 문자열이였는데 .json()을 통해서 js object로 변환해서 반환해줌

   })
   .then((result) => {  // result에는 위쪽 then에서 리턴해준 resolve값이 담겨있음
      console.log("result => ", result);
      makeList(result);
   });

// obj = JSON.parse(data);
// console.log(obj);

// obj배열에 있는 건수만큼 tr을 생성하고 tbody에 하위요소르 등록하기
function makeList(obj = []) {
	let fields = ['id', 'first_name', 'last_name', 'email', 'gender', 'salary'];
   for (let i = 0; i < obj.length; i++) {
      let tr = document.createElement("tr");
      
      // 마우스 올렸을 때 이벤트
      tr.addEventListener("mouseover", (event) => {
         tr.style.backgroundColor = '#98aec3';
      });

      // 마우스 땟을 때 이벤트
      tr.addEventListener("mouseout", (event) => {
         tr.style.backgroundColor = '#fff';
      });
      // td 생성
      for(let j = 0; j < fields.length; j++) {
         let td = document.createElement("td");
         td.innerText = obj[i][fields[j]];
         tr.appendChild(td);
      }

      let td = document.createElement("td")
      let button = document.createElement("button");
      button.innerText = "삭제"

      button.addEventListener("click", (e) => {
         console.dir(e);
         //e.target.parentElement.parentElement.remove();
      })

      td.appendChild(button);
      tr.appendChild(td);
      
      document.querySelector("#show tbody").appendChild(tr);
	}
}