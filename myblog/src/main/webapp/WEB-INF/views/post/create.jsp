<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<!-- Bootstrap을 사용하기 위한 meta name="viewport" 설정. -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>My Blog</title>
        
        <!-- Bootstrap CSS 링크 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
                rel="stylesheet" 
                integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
                crossorigin="anonymous">
	</head>
	<body class="bg-dark">
        <div class="container-lg">
            <c:set value="New Post" var="pageTitle"></c:set>
            <%@ include file="../fragments/header.jspf" %>
        </div>
        
        <main class="container-lg mt-2">
            <div class="card bg-secondary bg-opacity-75 p-2">
                <c:url var="postCreatePage" value="/post/create" />
                <form method="post" action="${postCreatePage}" id="create" enctype="multipart/form-data"><!--  -->
                    <div class="card-body">
                        <div>
                            <input class="form-control form-control-lg" type="text" name="title" placeholder="제목을 입력하세요." required autofocus>
                        </div>
                        <div class="mt-2">
                            <textarea class="form-control" rows="10" name="content" placeholder="내용을 입력하세요." wrap="hard" required></textarea>
                        </div>
                        <div class="mt-2 d-none">
                            <input type="text" name="author" value="${signedInMember}" readonly>
                        </div>
                        <div class="mt-2">
                            <div class="d-inline-block">
                                <label for="inputImage" class="form-label text-white">이미지 첨부</label>
                                <input class="form-control form-control-sm" type="file" name="images" id="inputImage" accept="image/jpg, image/jpeg, image/png" multiple>
                            </div>
                            <div class="d-inline-block">
                                <label for="inputImage" class="form-label text-white">파일 첨부</label>
                                <input class="form-control form-control-sm" type="file" name="files" id="inputFile">
                            </div>
                        </div>
                        
                    </div>
                </form>
                <div class="card-footer">
                    <div class="d-flex justify-content-end">
                        <input class="btn btn-dark" type="submit" form="create" value="작성">
                    </div>
                </div>
            </div>
        </main>
		
        
        <!-- Bootstrap JS 링크 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous"></script>
	</body>
</html>