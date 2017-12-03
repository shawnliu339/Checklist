<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>SEMAT Essence Alpha</title>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="css/checklist-dashboard.css" />
<script type="text/javascript" src="js/checklist-dashboard.js"></script>

</head>

<body>
	<div class="container">
		<h1>SEMAT Essence Alpha</h1>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-success alpha">
					<div class="panel-heading">Opportunity</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${機会}">
								<a href="checklist_goSubChecklist?alphaName=!E6!A9!9F!E4!BC!9A"
									class="list-group-item"> <span name="name"> <c:out
											value="${item.name}" />
								</span> <span name="percentage"> <fmt:formatNumber
											value="${item.percentage}" pattern="#0" />
								</span>
								</a>
							</c:forEach>
						</div>
						<div class="alpha-info">
							<div class="overview">
								<content> The set of circumstances that makes it
appropriate to develop or change a
software system.
								<hr>
								</content>
							</div>
							<div class="detail">
								<ul>
									<li>A good opportunity is identified
addressing the need for a softwarebased
solution</li>
									<li>A good opportunity has established
value</li>
									<li>A good opportunity has a softwarebased
solution that can be
produced quickly and cheaply</li>
									<li>A good opportunity creates a
tangible benefit</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-success alpha">
					<div class="panel-heading">Stakeholders</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${ステークホルダー}">
								<a href="checklist_goSubChecklist?alphaName=!E3!82!B9!E3!83!86!E3!83!BC!E3!82!AF!E3!83!9B!E3!83!AB!E3!83!80!E3!83!BC"
									class="list-group-item"> <span name="name"> <c:out
											value="${item.name}" />
								</span> <span name="percentage"> <fmt:formatNumber
											value="${item.percentage}" pattern="#0" />
								</span>
								</a>
							</c:forEach>
						</div>
						<div class="alpha-info">
							<div class="overview">
								<content> The people, groups, or organizations who affect or are affected by a software system.
								<hr>
								</content>
							</div>
							<div class="detail">
								<ul>
									<li>Healthy stakeholders represent groups or organizations affected by the software system</li>
									<li>Healthy stakeholder representatives carry out their agreed to responsibilities</li>
									<li>Healthy stakeholder representatives cooperate to reach agreement</li>
									<li>Healthy stakeholders are satisfied with the use of the software system</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-warning alpha">
					<div class="panel-heading">Requirements</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${要求}">
								<a href="checklist_goSubChecklist?alphaName=!E8!A6!81!E6!B1!82"
									class="list-group-item"> <span name="name"> <c:out
											value="${item.name}" />
								</span> <span name="percentage"> <fmt:formatNumber
											value="${item.percentage}" pattern="#0" />
								</span>
								</a>
							</c:forEach>
						</div>
						<div class="alpha-info">
							<div class="overview">
								<content>
								What the software system must do to
address the opportunity and satisfy
the stakeholders.
								<hr>
								</content>
							</div>
							<div class="detail">
								<ul>
									<li>Good Requirements meets real
needs</li>
									<li>Good Requirements has clear
scope</li>
									<li>Good Requirements are coherent
and well organized</li>
									<li>Good Requirements help drive
development</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-warning alpha">
					<div class="panel-heading">Software System</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${ソフトウェアシステム}">
								<a href="checklist_goSubChecklist?alphaName=!E3!82!BD!E3!83!95!E3!83!88!E3!82!A6!E3!82!A7!E3!82!A2!E3!82!B7!E3!82!B9!E3!83!86!E3!83!A0"
									class="list-group-item"> <span name="name"> <c:out
											value="${item.name}" />
								</span> <span name="percentage"> <fmt:formatNumber
											value="${item.percentage}" pattern="#0" />
								</span>
								</a>
							</c:forEach>
						</div>
						<div class="alpha-info">
							<div class="overview">
								<content>
								A system made up of software,
hardware, and data that provides its
primary value by the execution of the
software.
								<hr>
								</content>
							</div>
							<div class="detail">
								<ul>
									<li>Good Software System meets
requirements</li>
									<li>Good Software System has
appropriate architecture</li>
									<li>Good Software System is
maintainable, extensible and
testable</li>
									<li>Good Software System has low
support cost</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-info alpha">
					<div class="panel-heading">仕事</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${仕事}">
								<a href="checklist_goSubChecklist?alphaName=!E4!BB!95!E4!BA!8B"
									class="list-group-item"> <span name="name"> <c:out
											value="${item.name}" />
								</span> <span name="percentage"> <fmt:formatNumber
											value="${item.percentage}" pattern="#0" />
								</span>
								</a>
							</c:forEach>
						</div>
						<div class="alpha-info">
							<div class="overview">
								<content> 成果を獲得するために行う精神的、あるいは肉体的な努力をともなう活動
								<hr>
								</content>
							</div>
							<div class="detail">
								健全な仕事は…
								<ul>
									<li>規模が明らかに見積できてトラッキング可能</li>
									<li>作業項目間の依存性を減らすために分解できる</li>
									<li>リスクを常に管理下に置くことができる</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-info alpha">
					<div class="panel-heading">チーム</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${チーム}">
								<a href="checklist_goSubChecklist?alphaName=!E3!83!81!E3!83!BC!E3!83!A0"
									class="list-group-item"> <span name="name"> <c:out
											value="${item.name}" />
								</span> <span name="percentage"> <fmt:formatNumber
											value="${item.percentage}" pattern="#0" />
								</span>
								</a>
							</c:forEach>
						</div>
						<div class="alpha-info">
							<div class="overview">
								<content> 特定のソフトウェアシステムの開発、保守、供給、サポートに積極的に携わる集団
								<hr>
								</content>
							</div>
							<div class="detail">
								健全なチームは…
								<ul>
									<li>効果的にチーム目標を達成している</li>
									<li>効果的に協力できるメンバーで構成されている</li>
									<li>自分たちの仕事に集中している</li>
									<li>継続的な改善に取り組んでいる</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-info alpha">
					<div class="panel-heading">仕事の仕方</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${仕事の仕方}">
								<a href="checklist_goSubChecklist?alphaName=!E4!BB!95!E4!BA!8B!E3!81!AE!E4!BB!95!E6!96!B9"
									class="list-group-item"> <span name="name"> <c:out
											value="${item.name}" />
								</span> <span name="percentage"> <fmt:formatNumber
											value="${item.percentage}" pattern="#0" />
								</span>
								</a>
							</c:forEach>
						</div>
						<div class="alpha-info">
							<div class="overview">
								<content> チームの仕事をガイド、サポートするために適用された最適なプラクティスとツール群
								<hr>
								</content>
							</div>
							<div class="detail">
								良い仕事の仕方は…
								<ul>
									<li>チームに同意されている</li>
									<li>リスクとテクニカルな負債を低減できる</li>
									<li>効果的であり重複した仕事や無駄を取り除ける</li>
									<li>仕事の仕方自身を改善できる</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
