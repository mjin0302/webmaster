/**
 * replyservice.js
 * 메소드: 목록, 등록, 삭제
 */

const service = {
   rlist(bno, successFunc, errorFunc) {
      // Ajax 호출.
      fetch('replyList.do?bno=' + bno)
         .then(resolve => resolve.json())
         .then(successFunc)
         .catch(errorFunc)
   }
}