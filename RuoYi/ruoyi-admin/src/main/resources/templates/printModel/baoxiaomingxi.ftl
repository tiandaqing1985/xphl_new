<html>
 <head>
  <style>p{margin-top:0pt;margin-bottom:0pt;}p.X1{text-align:justified;}span.X1{font-family:'等线';font-size:10.0pt;}p.X2{text-align:left;}span.X2{font-size:9.0pt;}p.X3{text-align:center;}span.X3{font-size:9.0pt;}span.X7{font-size:9.0pt;}span.X8{font-size:9.0pt;}</style>
 </head>
 <body>
  <div style="width:841.0pt;margin-bottom:90.0pt;margin-top:90.0pt;margin-left:72.0pt;margin-right:72.0pt;">
   <p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:24.0pt;">费用</span><span style="font-family:'微软雅黑';font-size:24.0pt;">报销明细</span><span id="_GoBack"></span></p>
   <table class="X5 X6 calculateWidehHeight" border="2" style="width:841.0pt;border-collapse:collapse;">
    <tbody>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:130.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">报销人</span></p></td>
      <td class="X5 X6" style="width:577.6pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="8"><p>${data.userName!}</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:130.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">部门名称</span></p></td>
      <td class="X5 X6" style="width:577.6pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="8"><p>${data.deptName!}</p></td>
     </tr>
	  <tr class="X5 X6">
      <td class="X5 X6" style="width:130.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">报销编号</span></p></td>
      <td class="X5 X6" style="width:577.6pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="8"><p>${data.num}</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">日期</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">报销科目</span></p></td>
      <td class="X5 X6" style="width:79.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">事由</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">目标单位简称</span></p></td>
      <td class="X5 X6" style="width:116.3pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">参与人</span></p></td>
      <td class="X5 X6" style="width:58.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">吃饭地点</span></p></td>
      <td class="X5 X6" style="width:58.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">出发地点</span></p></td>
      <td class="X5 X6" style="width:49.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">到达地点</span></p></td>
      <td class="X5 X6" style="width:47.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">金额</span></p></td>
      <td class="X5 X6" style="width:36.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">附单据数</span></p></td>
     </tr>
	 <#list jiaotong as item>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.ddDate?string('yyyy-MM-dd')}</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.type!}</span></p></td>
      <td class="X5 X6" style="width:79.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.reason!}</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.targetUnit!}</span></p></td>
      <td class="X5 X6" style="width:116.3pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.user!}</span></p></td>
      <td class="X5 X6" style="width:58.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"></p></td>
      <td class="X5 X6" style="width:58.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.departure!}</span></p></td>
      <td class="X5 X6" style="width:49.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.target!}</span></p></td>
      <td class="X5 X6" style="width:47.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.amount!}</span></p></td>
      <td class="X5 X6" style="width:36.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.documentNum!}张</span></p></td>
     </tr>
	 </#list>
	 <#list zhaodaifei as item>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';"><#if item.ddDate??>${item.ddDate?string('yyyy-MM-dd')}<#else></#if></span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">招待费报销</span></p></td>
      <td class="X5 X6" style="width:79.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.reason!}</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.targetUnit!}</span></p></td>
      <td class="X5 X6" style="width:116.3pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.addUser!}</span></p></td>
      <td class="X5 X6" style="width:58.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;">${item.location!}</p></td>
      <td class="X5 X6" style="width:58.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';"></span></p></td>
      <td class="X5 X6" style="width:49.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';"></span></p></td>
      <td class="X5 X6" style="width:47.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.amount!}</span></p></td>
      <td class="X5 X6" style="width:36.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.documentNum!}张</span></p></td>
     </tr>
	 </#list>
	 <#list qita as item>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.ddDate?string('yyyy-MM-dd')}</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.project!}</span></p></td>
      <td class="X5 X6" style="width:79.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.reason!}</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';"></span></p></td>
      <td class="X5 X6" style="width:116.3pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';"></span></p></td>
      <td class="X5 X6" style="width:58.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"></p></td>
      <td class="X5 X6" style="width:58.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';"></span></p></td>
      <td class="X5 X6" style="width:49.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';"></span></p></td>
      <td class="X5 X6" style="width:47.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.amount!}</span></p></td>
      <td class="X5 X6" style="width:36.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.documentNum!}张</span></p></td>
     </tr>
	 </#list>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:624.05pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="8"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">合计</span></p></td>
      <td class="X5 X6" style="width:47.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p>￥${total}</p></td>
      <td class="X5 X6" style="width:36.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p>${invoices}张</p></td>
     </tr>
    </tbody>
   </table>
   <p style="text-align:justified;"></p>
  </div>
 </body>
</html>