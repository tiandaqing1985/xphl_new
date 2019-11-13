<html>
 <head> 
  <style>
		<style>p{margin-top:0pt;margin-bottom:0pt;}p.X1{text-align:justified;}span.X1{font-family:'等线';font-size:10.0pt;}p.X2{margin-top:13.0pt;margin-bottom:13.0pt;}span.X2{font-size:16.0pt;font-weight:bold;}span.X3{font-size:9.0pt;}p.X4{text-align:left;}span.X4{font-size:9.0pt;}p.X5{text-align:center;}span.X5{font-size:9.0pt;}span.X9{font-size:9.0pt;}span.X10{font-size:9.0pt;}span.X11{font-size:9.0pt;}</style>
 </head> 
 <body> 
  <div style="width:841.0pt;margin-bottom:90.0pt;margin-top:90.0pt;margin-left:72.0pt;margin-right:72.0pt;"> 
   <p style="text-align:center;"> <span style="font-size:16.0pt;font-weight:bold;">团队建设费</span> <span style="font-size:16.0pt;font-weight:bold;">申请单</span> </p> 
   <table class="X5 X6 calculateWidehHeight" border="2" style="width:841.0pt;border-collapse:collapse;"> 
    <tbody>
     <tr class="X5 X6"> 
      <td class="X5 X6" style="width:91.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"> <p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">所属公司</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p>${cost.deptCompany!}</p></td>
      <td class="X5 X6" style="width:106.3pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">申请人</span></p></td>
      <td class="X5 X6" style="width:258.2pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="4"><p>${cost.applicantName!}</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:91.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">所属部门</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p>${cost.deptName!}</p></td>
      <td class="X5 X6" style="width:106.3pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">费用项目</span></p></td>
      <td class="X5 X6" style="width:258.2pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="4"><p>${cost.leagueProject!}</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:91.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">申请时间</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p>${cost.applicationTime?string('yyyy-MM-dd')}</p></td>
      <td class="X5 X6" style="width:106.3pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">起止时间</span></p></td>
      <td class="X5 X6" style="width:258.2pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="4"><p>${cost.startDate?string('yyyy-MM-dd')}--${cost.endDate?string('yyyy-MM-dd')}</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:91.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">活动简介</span></p></td>
      <td class="X5 X6" style="width:605.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="6"><p>活动地点:${cost.activityPlace!};<br />活动人数:${cost.parNumber!};<br />部门名称:${cost.deptName!};<br />形式:${cost.activityForm!};</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:91.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">备注</span></p></td>
      <td class="X5 X6" style="width:605.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="6"><p>${cost.remarks!}</p></td>
     </tr>
	  <tr class="X5 X6">
      <td class="X5 X6" style="width:91.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">序号</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">项目</span></p></td>
      <td class="X5 X6" style="width:106.3pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">费用</span></p></td>
      <td class="X5 X6" style="width:258.2pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="4"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">单据数</span></p></td>
     </tr> 
	  <#list tuanjian as item>
	 <tr class="X5 X6">
      <td class="X5 X6" style="width:91.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.order!}</span></p></td>
      <td class="X5 X6" style="width:241.0pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.name!}</span></p></td>
      <td class="X5 X6" style="width:106.3pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.amount!}</span></p></td>
      <td class="X5 X6" style="width:258.2pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="4"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${item.number!}</span></p></td>
     </tr>
	 </#list> 
     <tr class="X5 X6">
      <td class="X5 X6" style="width:495.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="5"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">费用合计</span></p></td>
      <td class="X5 X6" style="width:201.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p>￥${cost.amount!}</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:495.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="5"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">总计</span><span style="font-family:'微软雅黑';font-weight:bold;">人民币（大写）</span></p></td>
      <td class="X5 X6" style="width:201.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p>${amountInWords!}</p></td>
     </tr>
     <tr class="X5 X6">
      <td class="X5 X6" style="width:91.9pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">审批人</span></p></td>
      <td class="X5 X6" style="width:361.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">审批意见</span></p></td>
      <td class="X5 X6" style="width:113.4pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">审批状态</span></p></td>
      <td class="X5 X6" style="width:130.65pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">审批时间</span></p></td>
     </tr>
      <#list facUserApprovals as checkLog>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:132.25pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" ><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.approverName!}</span></p></td>
      <td class="X7 X8" style="width:349.45pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.opi!}</span></p></td>
      <td class="X7 X8" style="width:120.5pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;" colspan="2"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.approvalState!}</span></p></td>
      <td class="X7 X8" style="width:103.55pt;border-top:1px solid #000000;border-bottom:1px solid #000000;border-left:1px solid #000000;border-right:1px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';">${checkLog.approvalTime?string("yyyy-MM-dd")}</span></p></td>
     </tr>
	 </#list>
    </tbody>
   </table>
   <p></p>
  </div>
 </body>
</html>