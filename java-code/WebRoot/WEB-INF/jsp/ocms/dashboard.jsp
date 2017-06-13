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
					<div class="panel-heading">機会</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${機会}">
								<a href="checklist_goSubChecklist?alphaName=%E6%A9%9F%E4%BC%9A"
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
								<content> ソフトウェアシステムを開発、あるいは更新するための理由になり得る十分な状況
								<hr>
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
			</div>
			<div class="col-md-6">
				<div class="panel panel-success alpha">
					<div class="panel-heading">ステークホルダ</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${ステークホルダー}">
								<a href="checklist_goSubChecklist?alphaName=%E3%82%B9%E3%83%86%E3%83%BC%E3%82%AF%E3%83%9B%E3%83%AB%E3%83%80%E3%83%BC"
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
								<content> ソフトウェアシステムに影響を与えたり、影響を受けたりする人、グループ、組織
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
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-warning alpha">
					<div class="panel-heading">要求</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${要求}">
								<a href="checklist_goSubChecklist?alphaName=%E8%A6%81%E6%B1%82"
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
								ソフトウェアシステムが機会に対応するためにすべきこととステークホルダを満足させるためにすべきこと
								<hr>
								</content>
							</div>
							<div class="detail">
								良い要求は…
								<ul>
									<li>現実的なニーズにマッチしている</li>
									<li>スコープが明確になっている</li>
									<li>一貫性があって体系化されている</li>
									<li>開発推進を手助けしてくれる</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-warning alpha">
					<div class="panel-heading">ソフトウェアシステム</div>
					<div class="panel-body">
						<div class="list-group">
							<c:forEach var="item" items="${ソフトウェアシステム}">
								<a href="checklist_goSubChecklist?alphaName=%E3%82%BD%E3%83%95%E3%83%88%E3%82%A6%E3%82%A7%E3%82%A2%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0"
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
								システムはソフトウェアとハードウェアおよびソフトウェアが作り出す価値あるデータで構成される
								<hr>
								</content>
							</div>
							<div class="detail">
								良いソフトウェアシステムは…
								<ul>
									<li>要求を満たしている</li>
									<li>相応しいアーキテクチャを採用している</li>
									<li>メンテナンス性、拡張性、テスト可能性を有す</li>
									<li>低コストでサポートできる</li>
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
								<a href="checklist_goSubChecklist?alphaName=%E4%BB%95%E4%BA%8B"
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
								<a href="checklist_goSubChecklist?alphaName=%E3%83%81%E3%83%BC%E3%83%A0"
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
								<a href="checklist_goSubChecklist?alphaName=%E4%BB%95%E4%BA%8B%E3%81%AE%E4%BB%95%E6%96%B9"
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
