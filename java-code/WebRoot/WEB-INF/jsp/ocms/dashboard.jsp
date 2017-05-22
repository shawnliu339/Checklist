<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>SEMAT Essence Alpha</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <style type="text/css">
    .list-group {
        display: inline-block;
        width: 45%;
    }

    .alpha {
    	display: inline-block;
    	width: 40%;
    	margin-right: 50px;
    }

    .alpha a {
    	position: relative;
    	width: 100%;
    }

    .alpha a span[name=percentage] {
    	position: absolute;
    	right: 20px;
    }
    
    .alpha-info {
    	display: inline-block;
    	width: 54%;
    	vertical-align: top;
    }

    .overview {
        height: 80px;
        margin: 5px;
    }
    
    .detail {
    	margin: 5px;
    }
    
    .hr {
    	margin-bottom: 5px;
    }
    </style>
</head>

<body>
    <div class="container">
        <h1>SEMAT Essence Alpha</h1>
        <div class="panel panel-success alpha">
            <div class="panel-heading">機会</div>
            <div class="panel-body">
                <div class="list-group">
                	<c:forEach var="item" items="${機会}">
                		<a href="#" class="list-group-item">
                    		<span name="name">
                    			<c:out value="${item.name}"/>
                    		</span>
                    		<span name="percentage">
                    			<fmt:formatNumber value="${item.percentage}" pattern="#0"/>
                    		</span>
                    	</a>
                	</c:forEach>
                </div>
                <div class="alpha-info">
                    <div class="overview">
                        <content>
                            ソフトウェアシステムを開発、あるいは更新するための理由になり得る十分な状況<hr>
                        </content>
                    </div>
                    <div class="detail">
                       	良い機会は…
                        <ul>
                        	<li>ソフトウェアシステムで解決すべき機会が特定されている</li>
                        	<li>ソフトウェアシステム導入の価値が定義されている</li>
                        	<li>QCDに見合ったソフトウェアが導入されている</li>
                        	<li>明確な利益を生み出すことができる</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-success alpha">
            <div class="panel-heading">ステークホルダ</div>
            <div class="panel-body">
                <div class="list-group">
                    <c:forEach var="item" items="${ステークホルダー}">
                		<a href="#" class="list-group-item">
                    		<span name="name">
                    			<c:out value="${item.name}"/>
                    		</span>
                    		<span name="percentage">
                    			<fmt:formatNumber value="${item.percentage}" pattern="#0"/>
                    		</span>
                    	</a>
                	</c:forEach>
                </div>
                <div class="alpha-info">
                    <div class="overview">
                        <content>
                            ソフトウェアシステムに影響を与えたり、影響を受けたりする人、グループ、組織
                            <hr>
                        </content>
                    </div>
                    <div class="detail">
                       健全なステークホルダは…
                       	<ul>
                        	<li>ソフトウェアシステムに関与するグループや組織の代表者</li>
                        	<li>ステークホルダ間で約束した役割を果たす</li>
                        	<li>要求の合意形成のために協力する</li>
                        	<li>ソフトウェアシステムの恩恵を受ける</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
