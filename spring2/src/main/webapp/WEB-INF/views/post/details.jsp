<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>Spring 2</title>
        
        <!-- Bootstrap CSS 링크 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
                rel="stylesheet" 
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
                crossorigin="anonymous">
	</head>
        
    <body>
        <div class="container-fluid">
            <c:set var="pageTitle" value="포스트 상세보기" />
            <%@ include file="../fragments/header.jspf" %>
            
            <main>
                <div class="mt-2 card">
                    <div class="card-body">
                        <form>
                            <div class="mt-2">
                                <label for="id" class="form-label">번호</label>
                                <input id="id" class="form-control" type="text"
                                    value="${post.id}" readonly />
                            </div>
                            <div class="mt-2">
                                <label for="title" class="form-label">제목</label>
                                <input id="title" class="form-control" type="text"
                                    value="${post.title}" readonly />
                            </div>
                            <div class="mt-2">
                                <label for="content" class="form-label">내용</label>
                                <textarea id="content" class="form-control" rows="5" 
                                    readonly>${post.content}</textarea>
                            </div>
                            <div class="mt-2">
                                <label for="author" class="form-label">작성자</label>
                                <input id="author" class="form-control" type="text"
                                    value="${post.author}" readonly />
                            </div>
                            <div class="mt-2">
                                <label for="createdTime" class="form-label">작성시간</label>
                                <input id="createdTime" class="form-control" type="text"
                                    value="${post.createdTime}" readonly />
                            </div>
                            <div class="mt-2">
                                <label for="modifiedTime" class="form-label">최종수정시간</label>
                                <input id="modifiedTime" class="form-control" type="text"
                                    value="${post.modifiedTime}" readonly />
                            </div>
                        </form>
                    </div>
                    <c:if test="${post.author eq signedInUser}">
                        <div class="card-footer d-flex justify-content-center">
                            <c:url var="postModifyPage" value="/post/modify">
                                <c:param name="id" value="${post.id}" />
                            </c:url>
                            <a class="btn btn-outline-primary" 
                                href="${postModifyPage}">수정하기</a>
                        </div>
                    </c:if>
                </div>
            </main>
            
            <section>
                <div class="d-inline-flex gap-1 mt-2">
                    <button class="btn btn-outline-secondary"
                        id="btnToggleComment">댓글 보기</button>
                </div>
                
                <!-- 댓글 보기/감추기 토글 버튼에 의해서 접기/펼치기를 할 영역 -->
                <div class="mt-2 collapse" id="collapseComments">
                    <!-- 댓글 등록 UI -->
                    <div class="mt-2 card card-body">
                        <div class="row">
                            <div class="col-10">
                                <textarea class="form-control" rows="3" id="ctext" placeholder="댓글 입력"></textarea>
                                <input class="d-none" id="username" value="${signedInUser}" readonly>
                            </div>
                            <div class="col-2">
                                <button class="btn btn-outline-success" id="btnRegisterComment">등록</button>
                            </div>
                        </div>
                    </div>
                    
                    <!-- 댓글 목록 UI -->
                    <div class="my-2" id="divComments">댓글목록</div>
                </div>
            </section>

            <!-- 댓글을 업데이트할 수 있는 모달(다이얼로그) -->
            <div class="modal" id="commentModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">댓글 업데이트</h5>
                            <button class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- 수정할 댓글 아이디 (번호) -->
                            <input class="d-none" id="modalCommentId" readonly />
                            <!-- 수정할 댓글 내용 -->
                            <textarea class="form-control" id="modalCommentText"></textarea>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-outline-secondary" data-bs-dismiss="modal">취소</button>
                            <button class="btn btn-outline-success" id="btnUpdateComment">저장</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
        <!-- Axios Http JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        
        <script>
        // 세션에 저장된 로그인 사용자 아이디를 자바 스크립트에 변수로 저장/
        // -> comments.js 파일의 코드들에서 그 변수를 사용할 수 있도록 하기 위해서.
        // JSP 파일의 <script> 태그 안에서는 EL을 사용할 수 있음.
        // (주의) JS 파일에서는 EL을 사용할 수 없음
        const signedInUser = '${signedInUser}';
        </script>
        
        <c:url var="commentsJS" value="/js/comments.js" />
        <script src="${commentsJS}"></script>
	</body>
</html>