<html>
 <head>
  <style>p{margin-top:0pt;margin-bottom:1pt;}p.X1{text-align:justified;}span.X1{font-family:'等线';font-size:10.0pt;}p.X2{margin-top:13.0pt;margin-bottom:13.0pt;}span.X2{font-size:16.0pt;font-weight:bold;}span.X3{font-size:9.0pt;}p.X4{text-align:left;}span.X4{font-size:9.0pt;}p.X5{text-align:center;}span.X5{font-size:9.0pt;}span.X9{font-size:9.0pt;}span.X10{font-size:9.0pt;}span.X11{font-size:9.0pt;}</style>
 </head>
 <body>
  <div style="width:841.0pt;margin-bottom:89.0pt;margin-top:89.0pt;margin-left:72.0pt;margin-right:72.0pt;">
   <p style="text-align:center;"><span style="font-family:'微软雅黑';font-size:18.0pt;font-weight:bold;">借款单</span></p>
   <table class="X7 X8 calculateWidehHeight" style="width:705.75pt;border-collapse:collapse;">
    <tbody>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:96.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款单号</span></p></td>
      <td class="X7 X8" style="width:253.25pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;" colspan="3"><p>${borrowMoney.borrowNumber!}</p></td>
      <td class="X7 X8" style="width:108.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款日期</span></p></td>
      <td class="X7 X8" style="width:247.9pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;" colspan="3"><p>${borrowMoney.borrowTime?string('yyyy-MM-dd')}</p></td>
     </tr>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:96.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">部门</span></p></td>
      <td class="X7 X8" style="width:609.7pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;" colspan="7"><p>${department!}</p></td>
     </tr>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:96.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款人</span></p></td>
      <td class="X7 X8" style="width:253.25pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;" colspan="3"><p>${borrowMoney.borrowUserName!}</p></td>
      <td class="X7 X8" style="width:108.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款金额</span></p></td>
      <td class="X7 X8" style="width:247.9pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;" colspan="3"><p>${borrowMoney.sum!'0'} 元</p></td>
     </tr>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:96.05pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款事由</span></p></td>
      <td class="X7 X8" style="width:609.7pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;" colspan="7"><p>${borrowMoney.cause!}</p></td>
     </tr>
     <tr class="X7 X8">
      <td class="X7 X8" style="width:252.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;" colspan="3"><p style="text-align:center;"><span style="font-family:'微软雅黑';font-weight:bold;">借款总额 人民币（大写）</span><span style="font-family:'微软雅黑';">：</span></p></td>
      <td class="X7 X8" style="width:452.95pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;" colspan="5"><p>${amountInWords}</p></td>
     </tr>
    </tbody>
   </table>
  </div>
 </body>
</html>