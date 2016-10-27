<#assign currentPage = "update">
<html>
<head>
<title>Add or update a stock</title>
<#include "head.ftl">
</head>
<body>  
	<div class="c-page-wrapper">
		<div class="c-main-container">
			<#include "header.ftl">
			<h1>Add or update a stock</h1>
			<div class="c-form-container search">
				<form class="p-form" method="post" action="/stock/update">
					<label class="p-form__label" for="code">Stock code: </label>
					<input class="p-form__input--text-field" type="text" placeholder="    i.e. A2M.AX" name="code" id="code">
					<input class="p-form__input--submit js-submit" type="submit" value="Add/Update">
				</form>
			</div>
			<article class="c-main-content">
				<#if errorMsg??>
					<p class="p-error-msg js-error-msg">${errorMsg}</p>
				</#if>
				<#if stockInfo??>
					<div class="p-stock-info">
						<h2 class="p-stock-info__heading">${stockInfo.symbol}</h2>
						<ul class="p-stock-info__list">
							<li class="p-stock-info__item">Name： <span>${stockInfo.name}</span></li>
							<li class="p-stock-info__item">Time: <span>${stockInfo.utctime?datetime}</span></li>
							<li class="p-stock-info__item">Price: <span>$ ${stockInfo.price}</span></li>
							<li class="p-stock-info__item">Volume: <span>${stockInfo.volume}</span></li>
							<li class="p-stock-info__item">Type: <span>${stockInfo.type}</span></li>
							<li class="p-stock-info__item">Ts: <span>${stockInfo.ts}</span></li>
						</ul>
					</div>
				</#if>
			</article>
		</div>
	</div>
  
</body>
</html>