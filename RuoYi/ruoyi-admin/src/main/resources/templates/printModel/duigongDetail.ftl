<html>
 <head>
  <style>p{margin-top:0pt;margin-bottom:0pt;}p.X1{text-align:justified;}span.X1{font-family:'等线';font-size:10.0pt;}p.X2{text-align:left;}span.X2{font-size:9.0pt;}p.X3{text-align:center;}span.X3{font-size:9.0pt;}span.X7{font-size:9.0pt;}span.X8{font-size:9.0pt;}</style>
 </head>
 <body>
  <div style="width:841.0pt;margin-bottom:90.0pt;margin-top:90.0pt;margin-left:72.0pt;margin-right:72.0pt;">
   <p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:24.0pt;">费用</span><span style="font-family:'微软雅黑';font-size:24.0pt;">对公支付明细</span><span id="_GoBack"></span></p>
   <table class="X5 X6 calculateWidehHeight" border="2" style="width:841.0pt;border-collapse:collapse;">
    <tbody>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:130.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">申请人</span></p></td>
      <td class="X5 X6" style="width:577.6pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="9"><p>${payPublicApply.userName!}</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:130.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">部门名称</span></p></td>
      <td class="X5 X6" style="width:577.6pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="9"><p>${user.dept.deptName!}</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:65.2pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">项目名称</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">付款事由</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">付款金额</span></p></td>
      <td class="X5 X6" style="width:79.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">详情</span></p></td>
     </tr>
	 <#list facPayPublicDetailed as item>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:65.2pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.name!}</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.reson!}</span></p></td>
      <td class="X5 X6" style="width:65.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.money!}</span></p></td>
      <td class="X5 X6" style="width:79.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.detail!}</span></p></td>
     </tr>
	 </#list>
    </tbody>
   </table>
   <p style="text-align:justified;"></p>
  </div>
 </body>
</html>