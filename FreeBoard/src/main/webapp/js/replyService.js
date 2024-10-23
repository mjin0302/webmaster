/**
 * replyservice.js
 * 메소드: 목록, 등록, 삭제
 */
const service = {
   // 1. 댓글 목록 조회
   replylist(param = { bno, page }, successFunc, errorFunc) {
      // Ajax 호출.
      fetch('replyList.do?bno=' + param.bno + '&page=' + param.page)
         .then(resolve => resolve.json())
         .then(successFunc)
         .catch(errorFunc)
   },
   // 2. 댓글 삭제
   removeReply(rno, successFunc, errorFunc) {
      fetch('removeReply.do?rno=' + rno)
         .then(resolve => resolve.json())
         .then(successFunc)
         .catch(errorFunc)
   },
   // 3. 추가
   addReply(param = { bno, reply, memberId }, successFunc, errorFunc) {
      fetch('addReply.do?bno='+param.bno+'&reply='+param.reply+'&memberId='+param.memberId)
         .then(resolve => resolve.json())
         .then(successFunc)
         .catch(errorFunc)
   },

   // 4. 댓글 카운트
   getReplyCount(bno, successFunc, errorFunc) {
      fetch('replyCount.do?bno=' + bno)
         .then(resolve => resolve.json())
         .then(successFunc)
         .catch(errorFunc)
   }
}