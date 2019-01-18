<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Load File</title>
</head>
<body>

    <center>

        <p> You choise <h2>${parser}</h2> parser</p>
        <p> Download Image </p>

        <FORM action="/upoad" enctype="multipart/form-data" method="POST">
            <input type="hidden" name="chooseCommand" value="CHOOSE_XML_COMMAND">

            Upload File:
            <INPUT type="file" name="content">
            <INPUT type="submit" value="Upload File">
        </FORM>

        <br><br><br>

        <a href="${pageContext.request.contextPath}/index.jsp">
            <h3>Choose another parser</h3>
        </a>

    </center>


</body>
</html>
