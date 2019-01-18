<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Current Tariffs</title>
</head>
<body>

    <div>

        <h2>Tariffs</h2>
        <br>

        <h3>Unlimit Tarifs</h3>
        <table>
            <tr>
                <td>Name</td>
                <td>Operator</td>
                <td>SMS</td>
                <td>Tariffication</td>
                <td>Connection</td>
                <td>Favourite number</td>
                <td>Internal call</td>
                <td>External call</td>
                <td>Internet</td>
                <td>Payroll</td>
            </tr>

            <c:forEach var="element" items="${unlimitList}">
                <tr>
                    <td>########</td>
                    <td>########</td>
                    <td>########</td>
                    <td>########</td>
                    <td>########</td>

                    <td>########</td>
                    <td>########</td>
                    <td>########</td>
                    <td>########</td>
                    <td>########</td>
                </tr>
                <tr>
                    <td><c:out value="${element.name}"/></td>
                    <td><c:out value="${element.operatorName}"/></td>
                    <td><c:out value="${element.smsPrice}"/></td>

                    <td><c:out value="${element.tariffParameters.tarifficationType}"/></td>
                    <td><c:out value="${element.tariffParameters.payForConnection}"/></td>
                    <td><c:out value="${element.tariffParameters.favouriteNumber}"/></td>

                    <td><c:out value="${element.hasInternalCall }"/></td>
                    <td><c:out value="${element.hasExternalCall}"/></td>
                    <td><c:out value="${element.hasInternet}"/></td>
                    <td><c:out value="${element.payroll}"/></td>
                </tr>
            </c:forEach>

        </table>
        <br><br><br>

        <h3>Limit Tarifs</h3>
        <table>
            <tr>
                <td>Name</td>
                <td>Operator</td>
                <td>SMS</td>
                <td>Tariffication</td>
                <td>Connection</td>
                <td>Favourite number</td>
                <td>Internal call</td>
                <td>External call</td>
                <td>Internet</td>
            </tr>

            <c:forEach var="element" items="${limitList}">
                <tr>
                    <td>########</td>
                    <td>########</td>
                    <td>########</td>
                    <td>########</td>
                    <td>########</td>

                    <td>########</td>
                    <td>########</td>
                    <td>########</td>
                    <td>########</td>
                </tr>
                <tr>
                    <td><c:out value="${element.name}"/></td>
                    <td><c:out value="${element.operatorName}"/></td>
                    <td><c:out value="${element.smsPrice}"/></td>

                    <td><c:out value="${element.tariffParameters.tarifficationType}"/></td>
                    <td><c:out value="${element.tariffParameters.payForConnection}"/></td>
                    <td><c:out value="${element.tariffParameters.favouriteNumber}"/></td>

                    <td><c:out value="${element.internalCallPrice }"/></td>
                    <td><c:out value="${element.externalCallPrice}"/></td>
                    <td><c:out value="${element.mbPrice}"/></td>
                </tr>
            </c:forEach>

        </table>


        <br><br><br>

        <a href="${pageContext.request.contextPath}/index.jsp">
            <h3>Choose another parser</h3>
        </a>

        <a href="${pageContext.request.contextPath}/jsp/loadFile.jsp">
            <h3>Choose another file</h3>
        </a>

    </div>

</body>
</html>
