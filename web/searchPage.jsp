<%-- 
    Document   : searchPage
    Created on : Jan 29, 2018, 9:50:29 AM
    Author     : MinhNBHSE61805
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<form action="SearchAddressServlet">
    Search: <input type="text" name="txtSearchValue" />
    <input type="submit" value="Search" name="btAction" />
</form>
<a href="insertPage.jsp">Add new student</a>
<c:set var="searchValue" value="${param.txtSearchValue}" />

<%--<c:if test="${not empty searchValue}">--%>
    <c:set var="result" value="${sessionScope.SEARCHRESULT}" />
    <c:if test="${not empty result}">
        <table>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Id</th>
                    <th>Full name</th>
                    <th>Sex</th>
                    <th>Address</th>
                    <th>Class</th>
                    <th>Status</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${result}" varStatus="counter">
                    <tr>
                        <td>
                            ${counter.count}
                        </td>
                        <td>
                            ${dto.id}
                        </td>
                        <td>
                            ${dto.lastname} ${dto.middlename} ${dto.firstname}
                        </td>
                        <td>
                            ${dto.sex}
                        </td>
                        <td>
                            ${dto.address}
                        </td>
                        <td>
                            ${dto.sClass}
                        </td>
                        <td>
                            ${dto.status}
                        </td>
                        <td>
                            <c:url value="ProcessServlet?btAction=Delete" var="deleteFrom">
                                <input type="submit" value="Delete" name="btAction" />
                                <c:param name="id" value="${dto.id}" />
                                <c:param name="searchValue" value="${searchValue}" />
                            </c:url>
                            <a href="${deleteFrom}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
<%--</c:if>--%>