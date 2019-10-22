<html>
 <head>
  <style>p{margin-top:0pt;margin-bottom:0pt;}p.X1{text-align:justified;}span.X1{font-family:'等线';font-size:10.0pt;}p.X2{margin-top:13.0pt;margin-bottom:13.0pt;}span.X2{font-size:16.0pt;font-weight:bold;}span.X3{font-size:9.0pt;}p.X4{text-align:left;}span.X4{font-size:9.0pt;}p.X5{text-align:center;}span.X5{font-size:9.0pt;}span.X9{font-size:9.0pt;}span.X10{font-size:9.0pt;}span.X11{font-size:9.0pt;}</style>
 </head>
 <body>
  <div style="width:841.0pt;margin-bottom:89.0pt;margin-top:89.0pt;margin-left:72.0pt;margin-right:72.0pt;">
   <p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:18.0pt;font-weight:bold;">借款单</span></p>
   <table class="X7 X8 calculateWidehHeight" border="2" style="width:841.0pt;border-collapse:collapse;">
    <tbody>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:96.05pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款单号</span></p></td>
      <td class="X7 X8" style="width:253.25pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${borrowMoney.num!}</p></td>
      <td class="X7 X8" style="width:108.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款日期</span></p></td>
      <td class="X7 X8" style="width:247.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${borrowMoney.loanTime?string('yyyy-MM-dd')}</p></td>
     </tr>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:96.05pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">部门</span></p></td>
      <td class="X7 X8" style="width:609.7pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="7"><p>${dept!}</p></td>
     </tr>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:96.05pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款人</span></p></td>
      <td class="X7 X8" style="width:253.25pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${borrowMoney.userName!}</p></td>
      <td class="X7 X8" style="width:108.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款金额</span></p></td>
      <td class="X7 X8" style="width:247.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p>${borrowMoney.amount!'0'} 元</p></td>
     </tr>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:96.05pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款事由</span></p></td>
      <td class="X7 X8" style="width:609.7pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="7"><p>${borrowMoney.reason!}</p></td>
     </tr>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:252.8pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款总额 人民币（大写）</span><span style="font-family:'微软雅黑';">：</span></p></td>
      <td class="X7 X8" style="width:452.95pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="5"><p>${amountStr}</p></td>
     </tr>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:132.25pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">审批人</span></p></td>
      <td class="X7 X8" style="width:349.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="4"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">审批意见</span></p></td>
      <td class="X7 X8" style="width:120.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">审批状态</span></p></td>
      <td class="X7 X8" style="width:103.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">审批时间</span></p></td>
     </tr>
	 <#list facUserApprovals as checkLog>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:132.25pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.approverName!}</span></p></td>
      <td class="X7 X8" style="width:349.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="4"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.opi!}</span></p></td>
      <td class="X7 X8" style="width:120.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.approvalState!}</span></p></td>
      <td class="X7 X8" style="width:103.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.approvalTime?string("yyyy-MM-dd")}</span></p></td>
     </tr>
	 </#list>
    </tbody>
   </table>
   <p class="X1 X2" style="text-align:center;"><span class="X1 X2"> </span><span id="_GoBack"><span class="X1 X2">借款人手签:</span></span></p>
   <p class="X1 X2" style="text-align:right;"><span class="X1 X2"></span></p>
  </div>
 </body>
</html>