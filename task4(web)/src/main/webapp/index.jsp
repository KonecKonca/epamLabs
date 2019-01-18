<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Xml parsers</title>
</head>
<body>

    <center>
        <div>
            <h1>Choose xml parser</h1>
            <h3>dom</h3>
            <h3>sax</h3>
            <h3>stax</h3>
            <br><br><br>

            <form action="/controller" method="post">
                <input type="hidden" name="chooseCommand" value="CHOOSE_PARSER_COMMAND">

                <input type="text" name="parser" required minlength="3" maxlength="4">

                <input type="submit" value="Parse">

            </form>
        </div>
    </center>

</body>
</html>
