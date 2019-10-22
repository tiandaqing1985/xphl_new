<html>
 <head>
  <style>p{margin-top:0pt;margin-bottom:0pt;}p.X1{text-align:justified;}span.X1{font-family:'等线';font-size:10.0pt;}p.X2{text-align:left;}span.X2{font-size:9.0pt;}p.X3{text-align:center;}span.X3{font-size:9.0pt;}span.X7{font-size:9.0pt;}span.X8{font-size:9.0pt;}</style>
 </head>
 <body>
  <div style="width:841.0pt;margin-bottom:90.0pt;margin-top:90.0pt;margin-left:72.0pt;margin-right:72.0pt;">
   <p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:24.0pt;">对公支付申请</span><span id="_GoBack"></span></p>
   <table class="X5 X6 calculateWidehHeight" border="2" style="width:841.0pt;border-collapse:collapse;">
    <tbody>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">部门名称</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${dept!}</p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">申请人</span></p></td>
      <td class="X5 X6" style="width:251.1pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.userName!}</p></td>
     </tr>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">申请名称</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.name!}</p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">申请日期</span></p></td>
      <td class="X5 X6" style="width:251.1pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p><#if data.createTime??>${data.createTime?string('yyyy-MM-dd')}<#else></#if></p></td>
     </tr>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">发票状态</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="7"><p>
	  ${data.weatherInvoice!}</p></td>
     </tr>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">申请总金额</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${sum!}</p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">收款人户名</span></p></td>
      <td class="X5 X6" style="width:251.1pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.payee!}</p></td>
     </tr>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">收款人账号</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.payeeAccount!}</p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">收款人开户行</span></p></td>
      <td class="X5 X6" style="width:251.1pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${data.payeeBank!}</p></td>
     </tr>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:113.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">备注</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="7"><p>${data.remarks!}</p></td>
     </tr>
	  <tr class="X5 X6">
      <td class="X5 X6" style="width:169.85pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">审批人</span></p></td>
      <td class="X5 X6" style="width:326.05pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">审批意见</span></p></td>
      <td class="X5 X6" style="width:92.15pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">审批状态</span></p></td>
      <td class="X5 X6" style="width:109.35pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:14.0pt;">审批时间</span></p></td>
     </tr>
    <#list facUserApprovals! as checkLog>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:132.25pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.approverName!}</span></p></td>
      <td class="X7 X8" style="width:349.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.opi!}</span></p></td>
      <td class="X7 X8" style="width:120.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.approvalState!}</span></p></td>
      <td class="X7 X8" style="width:103.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.approvalTime?string('yyyy-MM-dd')!}</span></p></td>
     </tr>
	 </#list>
    </tbody>
   </table>
   <p style="text-align:center;"></p>
  </div>
 </body>
</html>