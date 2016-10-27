<#if currentPage == "search">
  <#assign search = true>
</#if>
<#if currentPage == "update">
  <#assign update = true>
</#if>

<navigation class="o_main-nav">
	<ul class="o_main-nav__list">
		<li class="o_header__item <#if update??>current</#if>"><a class="o_header__link" href="/stock/update">Add/Update a Stock</a></li>
		<li class="o_header__item <#if search??>current</#if>"><a class="o_header__link" href="/stock">Search an Existing Stock</a></li>
	</ul>
</navigation>