/**
 * jreply.js
 */

console.log("start")

function createLiFunc (item) {
   $("<li/>").append(
      $("<span/>").addClass("col-sm-2").text(item.replyNo), // 글 번호
      $("<span/>").addClass("col-sm-5").text(item.reply),   // 댓글 내용
      $("<span/>").addClass("col-sm-2").text(item.memberId),
      $("<span/>").addClass("col-sm-2").append($("<button>삭제</button>"))  // 삭제 
   ) .appendTo($("div.content ul"));
   //
}

$.ajax('replyList.do?bno='+bno+"&page=1")
.done((result) => {
   result.forEach(item => {
      createLiFunc(item);
   });
   // 삭제 이벤트
   
})
.fail((err) => {
   console.log(err);
})

$("div.content ul").on("click", "button", (e) => {
   let rno = $(e.target).parent().parent().find("span:eq(0)").text();
   $.ajax({
      url : 'removeReply.do',
      data : { rno : rno },
      method : "get",
      dataType: "json" // ? 문자열 => 자바스크립트 객체
   })
   .done((result) => {
      if(result.retCode == "OK") {
         $(e.target).closest("li").remove();
      } 
   })
   .fail((err) => {
      console.log(err);
   })
});

$("#replyAddBtn").on("click", (e) => {
   let bno = $(e.target).closest("div.reply").prev().find("thead td:eq(0)").text();
   let reply = $("#reply").val();
   console.log(bno, reply)

   $.ajax({
      url : 'addReply.do',
      data : { reply: reply, bno : bno, memberId: "ongsim" },
      method : "get",
      dataType: "json" // ? 문자열 => 자바스크립트 객체
   })
   .done((result) => {
      console.log(result);
      createLiFunc(result.retVal);
   })
   .fail((err) => {
      console.log(err);
   })
});
