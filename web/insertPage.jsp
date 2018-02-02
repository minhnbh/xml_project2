<%-- 
    Document   : insertPage
    Created on : Feb 2, 2018, 7:44:51 AM
    Author     : MinhNBHSE61805
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form action="InsertStudentServlet" method="POST">
    <table>
        <tr>
            <td>Last name</td>
            <td><input type="text" name="lastname" ></td>
        </tr>
        <tr>
            <td>Middle name</td>
            <td><input type="text" name="middlename" ></td>
        </tr>
        <tr>
            <td>First name</td>
            <td><input type="text" name="firstname" ></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" ></td>
        </tr>
        <tr>
            <td>Sex</td>
            <td><input type="text" name="sex" ></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><input type="text" name="address" ></td>
        </tr>
        <tr>
            <td>Status</td>
            <td>
                <select name="status">
                    <option value="studying">Studying</option>
                    <option value="dropout">Drop out</option>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td style="text-align: right">
                <button type="submit" value="Insert" name="btAction">Add</button>
            </td>
        </tr>
    </table>
</form>
