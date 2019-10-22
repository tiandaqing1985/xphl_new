<html>
 <head>
  <style>p{margin-top:0pt;margin-bottom:0pt;}p.X1{text-align:justified;}span.X1{font-family:'等线';font-size:10.0pt;}p.X2{text-align:left;}span.X2{font-size:9.0pt;}p.X3{text-align:center;}span.X3{font-size:9.0pt;}span.X7{font-size:9.0pt;}span.X8{font-size:9.0pt;}</style>
 </head>
 <body>
  <div style="width:841.0pt;margin-bottom:90.0pt;margin-top:90.0pt;margin-left:72.0pt;margin-right:72.0pt;">
   <p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:24.0pt;">费用报销单</span><span id="_GoBack"></span></p>
   <table class="X5 X6 calculateWidehHeight" border="2" style="width:841.0pt;border-collapse:collapse;">
    <tbody>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">报销日期</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.reimburseTime?string("yyyy-MM-dd")}</p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">报销编号</span></p></td>
      <td class="X5 X6" style="width:251.1pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.num!}</p></td>
     </tr>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">部门</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.deptName!}</p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">报销人</span></p></td>
      <td class="X5 X6" style="width:251.1pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.userName!}</p></td>
     </tr>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">报销名称</span></p></td>
	  <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="7"><p>${data.name!}</p></td>
     </tr>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">借款单号</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.JKnum!}</p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">冲抵借款</span></p></td>
      <td class="X5 X6" style="width:251.1pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p><#if data.JKnum??>是<#else>否</#if></p></td>
     </tr>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:169.85pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">报销科目</span></p></td>
      <td class="X5 X6" style="width:326.05pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">摘要</span></p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">金额</span></p></td>
      <td class="X5 X6" style="width:109.35pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">附单据数</span></p></td>
     </tr>
	 <#list huizong! as item>
	 <tr class="X7 X8">
      <td class="X7 X8" style="width:132.25pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.type!}</span></p></td>
      <td class="X7 X8" style="width:349.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.detail!}</span></p></td>
      <td class="X7 X8" style="width:120.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">￥${item.money!}</span></p></td>
      <td class="X7 X8" style="width:103.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.documentNum!}</span></p></td>
     </tr>
	 </#list>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:169.85pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">审批人</span></p></td>
      <td class="X5 X6" style="width:326.05pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">审批意见</span></p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">审批状态</span></p></td>
      <td class="X5 X6" style="width:109.35pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">审批时间</span></p></td>
     </tr>
    <#list shenpiren! as item>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:132.25pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.approverName!}</span></p></td>
      <td class="X7 X8" style="width:349.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.opi!}</span></p></td>
      <td class="X7 X8" style="width:120.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.approvalState!}</span></p></td>
      <td class="X7 X8" style="width:103.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.approvalTime?string("yyyy-MM-dd")}</span></p></td>
     </tr>
	 </#list>
    </tbody>
   </table>
   <p style="text-align:center;"></p>
  </div>
 </body>
</html>