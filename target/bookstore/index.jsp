<%--
  Created by IntelliJ IDEA.
  User: LiuSh
  Date: 2020/4/8
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width: 90%;">

    <form style="margin: auto" action="testJson.html" method="post">
        <table>
            <tr>
                <td>账号：</td>
                <td><input type="text" name="userName" /> </td>
                <td></td>
            </tr>

            <tr>
                <td>密码：</td>
                <td colspan="2"><input type="password" name="passWord"/> </td>
            </tr>

            <tr>
                <td colspan="3"><input type="button" id="pass"  value="登录"/> </td>
            </tr>
            <tr>
                <td colspan="3"><a href="register.html">立即注册</a> </td>
            </tr>
        </table>
    </form>

    <ul id="ultest">
        <li></li>
    </ul>

</div>

<script src="static/js/jquery.min.js" type="text/javascript"></script>

<%--<script src="static/js/jquery-1.8.3.min.js" type="text/javascript"></script>--%>
<script>

   $(function () {
       let names = "${name}";
       let namess = '${name}';
       alert(names+"==="+namess);
       <%--let convertibilityPrizeSet = JSON.parse('${lista}')--%>
       <%--alert(convertibilityPrizeSet);--%>
       <%--  let name = $('${name}');--%>
       <%--  alert(name)--%>
       /*if(name != null){
           alert(name);
       }else if("name="+convertibilityPrizeSet != null) {
           alert("convertibilityPrizeSet"+convertibilityPrizeSet)
       }*/

   })
   $("#pass").click(function () {
       $.ajax({
            type:"POST",
           url:"testJsons.html",
           data:{bname:"java",author:"佳"},
           dataType:"JSON",
           success:function (data) {
               let name = "";
               alert(data.model.bookList);
               $.each(JSON.parse(data.model.bookList),function (i,v) {
                   name += "<li>"+v.bname+"</li>"
               })


               $("#ultest").html(name)

           }
       })
   })
</script>
</body>
</html>
