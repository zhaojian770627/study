<#-- assign 指令 定义变量-->
<#assign name="zj">

hello ${name}

<#-- if 指令-->
<#if flag=1>
	1
	<#elseif flag=2>
	2
	<#else>
	3
</#if>

<#-- list 指令 循环迭代-->
<#list weeks as week>
	${week_index}: ${week}
</#list>