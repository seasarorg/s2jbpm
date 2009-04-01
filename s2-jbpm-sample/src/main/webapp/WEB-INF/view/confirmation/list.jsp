<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${f:url('/css/global.css')}" />
</head>
<body>

<html:errors />
<h4>注文確定待ち商品一覧</h4>
<table border="1">
	<tr style="background-color: pink">
		<th>ID</th>
		<th>商品</th>
		<th>値段</th>
		<th>担当者</th>
		<th>状態</th>
		<th>備考</th>
		<th></th>
	</tr>

	<c:forEach var="e" varStatus="s" items="${orderItems}">
		<tr style="background-color:${s.index %2 == 0 ? 'white' : 'aqua'}">
			<td>${f:h(e.id)}</td>
			<td>${f:h(e.item)}</td>
			<td>${f:h(e.price)}</td>
			<td>${f:h(e.contact)}</td>
			<td>${f:h(smp:sn(e.status))}</td>
			<td>${f:h(e.note)}</td>
			<td><s:link href="show/${e.id}"> 詳細 </s:link></td>
		</tr>
	</c:forEach>

</table>
<br />
<hr />
<a href="/s2-jbpm-sample/">メニューへ</a>
</body>
</html>