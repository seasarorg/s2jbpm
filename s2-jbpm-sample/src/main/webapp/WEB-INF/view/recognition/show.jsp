<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${f:url('/css/global.css')}" />
</head>
<body>

<html:errors />
<h4>商品情報詳細</h4>
<table class="tablebg">
	<tr>
		<td>商品</td>
		<td>${f:h(item)}</td>
	</tr>
	<tr>
		<td>金額</td>
		<td>${f:h(price)}</td>
	</tr>
	<tr>
		<td>担当者</td>
		<td>${f:h(contact)}</td>
	</tr>
	<tr>
		<td>備考</td>
		<td>${f:h(note)}</td>
	</tr>

</table>

<br />
<s:link href="recognize/${id}">１次承認実行</s:link>

<br />
<br />
<s:link href="/recognition/">一覧表示</s:link>
</body>
</html>