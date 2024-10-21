/**
 * basic.js
 */

console.log("출력!!!!!!!!")

let name = "김민진";
let age = 20;
let obj = { name : "킴옹심",
            age : 3,
            showInfo: function() {
               return this.name + " - " + this.age;
            }
         }
console.log("이름 : ", obj.name, " 나이 : ", obj.age)
console.log(obj['age']);
console.log(obj.showInfo());

let data = [obj]
data.push({name : "박민수", age: 22});
data.push({name : "송민혁", age: 25});

console.log("data : " + data)
// DOM
let tr;
let th;
let td;

let tableEle = document.querySelector("#tableBox");
let tbodyEle = document.querySelector("#tableBox tbody")

function createElementFunc() {
   for(let i = 0; i < data.length; i++ ) {
      tr = document.createElement("tr");
      
      td = document.createElement("td");
      td.innerHTML = data[i]['age'];
      td = document.createElement("td");
      td.innerHTML = data[i]['name'];
      tr.appendChild(td);

      document.querySelector("#tableBox").appendChild(tr);
   }
}
let ul = document.querySelector("#show ul");
let li = document.createElement("li");

console.log(ul);

li.innerText = "Cherry";
ul.append(li)

let lis = document.querySelectorAll("#show ul li");

lis.forEach((fruit) => {
   fruit.addEventListener('click', (e) => {
      fruit.remove();
   });
   console.log(fruit.innerHTML)
});


tr = document.createElement("tr");
th = document.createElement("th");
td = document.createElement("td");

createElementFunc();

th.innerHTML = "이름"
td.innerHTML = "김민진"
tr.appendChild(th);
tr.appendChild(td);

let table = document.querySelector("tbody")
table.appendChild(tr)
