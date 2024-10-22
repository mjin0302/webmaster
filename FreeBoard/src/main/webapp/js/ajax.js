/**
 * ajax.js
 * Asynchronous Javascript And Xml
 * 자바스크립트는 비동기 방식으로 처리한ek
 * 사람들은 기다려주지 않는다
 */

setTimeout(function() {
   console.log("1")

}, 1000);

console.log("1");
console.log("2");
console.log("3");

let xhtp = new XMLHttpRequest();
xhtp.open('get', 'memberJson.do');
xhtp.send();

let data = [];
xhtp.onload = () => {
   let obj = JSON.parse(xhtp.responseText);
   console.log(obj);
   data = obj;
   console.log("1", obj);

   for(let i = 0; i < data.length; i++) {
      console.log(data[i]);
   }
}

console.log("2", data);
