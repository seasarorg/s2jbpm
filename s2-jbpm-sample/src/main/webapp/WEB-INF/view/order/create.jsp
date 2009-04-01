<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${f:url('/css/global.css')}" />
</head>
<body>

<html:errors />
<h4>注文情報登録</h4>
<s:form>

	<table class="tablebg">
		<tr>
			<td>商品</td>
			<td><html:text property="item" /></td>
		</tr>
		<tr>
			<td>金額</td>
			<td><html:text property="price" /></td>
		</tr>
		<tr>
			<td>担当者</td>
			<td><html:text property="contact" /></td>
		</tr>
		<tr>
			<td>状態</td>
			<td><html:select property="status">
				<html:option value="0">仮登録</html:option>
				<html:option value="1">１次承認待ち</html:option>
			</html:select></td>
		</tr>
		<tr>
			<td>備考</td>
			<td><html:text property="note" /></td>
		</tr>

	</table>
	<br />
	<input type="submit" name="insert" value="新規登録" />
</s:form>
<br />
<br />

<s:link href="/order/">注文情報一覧</s:link>

</body>
</html>