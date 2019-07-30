package com.ruoyi.web.controller.system;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRequest;
import com.dingtalk.api.request.OapiDepartmentListIdsRequest;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserGetDeptMemberRequest;
import com.dingtalk.api.request.OapiUserSimplelistRequest;
import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.dingtalk.api.response.OapiAttendanceListResponse.Recordresult;
import com.dingtalk.api.response.OapiDepartmentListIdsResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse.Department;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserGetDeptMemberResponse;
import com.dingtalk.api.response.OapiUserSimplelistResponse;
import com.dingtalk.api.response.OapiUserSimplelistResponse.Userlist;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.domain.AttendanceQvo;
import com.ruoyi.system.domain.OaDingding;
import com.ruoyi.system.domain.OaDingdingUser;
import com.ruoyi.system.service.IOaDingdingService;
import com.ruoyi.system.service.IOaDingdingUserService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/system/dingding")
public class DingDingController extends BaseController{

	@Autowired
	IOaDingdingService dingdingService;
	
	@Autowired
	IOaDingdingUserService dingdingUserService;

	@GetMapping("/data")
	@ResponseBody
	public void data() throws Exception
	{		
		DingTalkClient client = new DefaultDingTalkClient("");
		
		String accessToken = getAccess_token(client);		

		//获取部门列表
		List<Department> deptList = getDept(client, accessToken);//41
			
		//获取子部门ID列表
		//[120356124, 94058110, 93977150, 94045157]
		Set<Long> allDeptSet = new HashSet<Long>();//所有部门id 36
		for(Department dept : deptList){
			List<Long> subDeptList = getSubDept(client, accessToken, dept.getId().toString());
			allDeptSet.addAll(subDeptList);
		}
		
		//获取部门用户userid列表
		//[042262656224235781, 213121072228921172, 150906420524551796]
		List<String> allUserIdList = new ArrayList<String>();//所有用户id  186
		for(Long deptId : allDeptSet){
			List<String> userIdList = getDeptUser(client, accessToken, deptId.toString());
			allUserIdList.addAll(userIdList);
		}

		//获取部门用户
		//"userlist":[{"name":"牛德源","userid":"213121072228921172"},{"name":"张智伟","userid":"042262656224235781"},{"name":"徐美玲","userid":"150906420524551796"}]}
		List<OaDingdingUser> dingUserList = new ArrayList<OaDingdingUser>();
		for(Long deptId : allDeptSet){
			List<Userlist> userList = getUser(client, accessToken,deptId);
			for(Userlist user : userList){
				OaDingdingUser dingUser = new OaDingdingUser();
				dingUser.setUserId(user.getUserid());
				dingUser.setUserName(user.getName());
				dingUserList.add(dingUser);
			}
		}
		dingdingUserService.insertForeach(dingUserList);//用户总数172
				
		
		//设置查询时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);
       /* String yesterday = getPreDayOrAfterDay(currentDate, -1);//-1是前一天， +1是后一天
        String workDateFrom = yesterday + " " + "00:00:00";
        String workDateTo = yesterday+ " " + "23:59:59";//当前时间
*/
        String fromDate = getPreDayOrAfterDay(currentDate, -3);//-1是前一天， +1是后一天
        String toDate = getPreDayOrAfterDay(currentDate, -1);
        String workDateFrom = fromDate + " " + "00:00:00";
        String workDateTo = toDate+ " " + "23:59:59";//当前时间
        
		//将所有用户id分成50个一组
//		List<List<String>> mEndList = getList(allUserIdList,50);
//	    for (int i = 0; i < mEndList.size(); i++) {
////	        System.out.println(mEndList.get(i).toString()+"");
//			//获取打卡结果
//			List<Recordresult> recordList = getData(client, accessToken, mEndList.get(i), workDateFrom, workDateTo);
//			System.out.println();
//	    }
	    
        List<OaDingding> users = new ArrayList<>();
        List<OaDingding> dataList = getAttendances(allUserIdList,users, workDateFrom,  workDateTo, accessToken);
        dingdingService.insertForeach(dataList);
        System.out.println("获取从昨天0点到今天0点时间的打卡记录"+ Arrays.asList(users));

	}
	
	public static List<OaDingding> getAttendances(List<String> list, List<OaDingding> users, String workDateFrom, String workDateTo,String access_Token) {
      int listSize=list.size();
      int toIndex = 50;
      for(int i = 0;i < list.size();i += 50){
          if(i+50 > listSize){        //作用为toIndex最后没有50条数据则剩余几条newList中就装几条
              toIndex = listSize-i;
          }
          List<String> newList = list.subList(i,i + toIndex);
          Boolean hasMore = true;
          int offset = 0;//为了分页
          do{
              Map<String,Object> mapParam = new HashMap<>();
              mapParam.put("workDateFrom", workDateFrom);
              mapParam.put("workDateTo", workDateTo);
              mapParam.put("userIdList", newList);
              mapParam.put("offset", offset * 50);
              mapParam.put("limit", 50);
              String attendanceStr = getAttendance(mapParam, access_Token);
              JSONObject firstJson = JSONObject.parseObject(attendanceStr);

              hasMore = firstJson.getBoolean("hasMore");
              JSONArray recordFirst = firstJson.getJSONArray("recordresult");//当前部门下的userList
              for(int j = 0;j < recordFirst.size(); j++) {
                  JSONObject record = recordFirst.getJSONObject(j);
                  OaDingding dingding = new OaDingding();
                  dingding.setCheckType(record.getString("checkType"));
                  dingding.setUserId(record.getString("userId"));
                  
                  Date date = new Date();
                  date.setTime(record.getLong("workDate"));
                  dingding.setWorkDate(date);
                  
                  date = new Date();
                  date.setTime(record.getLong("userCheckTime"));
                  dingding.setUserCheckTime(date);
                  
                  dingding.setTimeResult(record.getString("timeResult"));
                  users.add(dingding);
              }
              if(hasMore) {//有下一页偏移量加一
                  offset++;
              }
          } while (hasMore);
      }
      return users;
	}
	 public static String doPost(String requestUrl,JSONObject json){
	        HttpClient client = new DefaultHttpClient();
	        HttpPost post = new HttpPost(requestUrl);
	        post.setHeader("Content-Type", "application/json");
	        post.addHeader("Authorization", "Basic YWRtaW46");
	        String result = "";
	        try {
	            StringEntity s = new StringEntity(json.toString(), "utf-8");
	            s.setContentEncoding(new BasicHeader("contentType",
	                    "application/json"));
	            post.setEntity(s);
	            // 发送请求
	            HttpResponse httpResponse = client.execute(post);
	            // 获取响应输入流
	            InputStream inStream = httpResponse.getEntity().getContent();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
	            StringBuilder strber = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null)
	                strber.append(line + "\n");
	            inStream.close();
	            result = strber.toString();
	            System.out.println(result);
	            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	 
	                System.out.println("请求服务器成功，做相应处理");
	 
	            } else {
	                System.out.println("请求服务端失败");
	            }
	        } catch (Exception e) {
	            System.out.println("请求异常");
	            throw new RuntimeException(e);
	        }
	        return result;
	    }
	   private static String accessTokenUrl = "https://oapi.dingtalk.com/gettoken";//通用
	   private static String corpId = "ding306f151d37f14d4035c2f4657eb6378f";
	   private static String corpSecret = "3wJRsyR6kyWF23I4SuesPZfuwmDpsqXmiAovTREP_7sf1vf9RdjobpHd0fPeZdTk";
	 
	    public static String getAccessToken() {
	        Map<String,Object> map = new HashMap<>();
	        map.put("corpid",corpId);
	        map.put("corpsecret",corpSecret);
	        return httpGetStringResult(accessTokenUrl, map);//获取access_token
	    }
	    public static String httpGetStringResult(String url,Map<String,Object> param){
	        String content = null;
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        if(param != null && !param.isEmpty()){
	            StringBuffer strparams = new StringBuffer();
	            for (Map.Entry<String, Object> map : param.entrySet()) {
	                strparams.append(map.getKey()).append("=").append(map.getValue().toString()).append("&");
	            }
	            strparams = strparams.deleteCharAt(strparams.length()-1);
	            url = url + "?" + strparams;
	        }
	        HttpGet httpGet = new HttpGet(url);
	        CloseableHttpResponse response = null;
	 
	        try {
	            response = httpClient.execute(httpGet);
	            HttpEntity entity = response.getEntity();
	            content = EntityUtils.toString(entity,"UTF-8");
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	 
	        }finally {
	            try {
	                if(null!=response){
	                    response.close();
	                }
	            } catch (IOException e) {
	 
	                e.printStackTrace();
	            }
	        }
	 
	        return content;
	    }
	 private static List<List<String>> getList(List<String> mList, int targ) {
	        List<List<String>>mEndList = new ArrayList<>();
	        if( mList.size()%targ!=0) {
	            for (int j = 0; j < mList.size() / targ + 1; j++) {
	                if ((j * targ + targ) < mList.size()) {
	                    mEndList.add(mList.subList(j * targ, j * targ + targ));//0-3,4-7,8-11    j=0,j+3=3   j=j*3+1
	                } else if ((j * targ + targ) > mList.size()) {
	                    mEndList.add(mList.subList(j * targ, mList.size()));
	                } else if (mList.size() < targ) {
	                    mEndList.add(mList.subList(0, mList.size()));
	                }
	            }
	        }else if(mList.size()%targ==0){
	            for (int j = 0; j < mList.size() / targ; j++) {
	                if ((j * targ + targ) <= mList.size()) {
	                    mEndList.add(mList.subList(j * targ, j * targ + targ));//0-3,4-7,8-11    j=0,j+3=3   j=j*3+1
	                } else if ((j * targ+ targ) > mList.size()) {
	                    mEndList.add(mList.subList(j * targ, mList.size()));
	                } else if (mList.size() < targ) {
	                    mEndList.add(mList.subList(0, mList.size()));
	                }
	            }
	        }
			return mEndList;
	    }

     private static String getPreDayOrAfterDay(String current, int flag) {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Calendar calendar = Calendar.getInstance();//获取日历实例
         try {
             calendar.setTime(sdf.parse(current));
         } catch (Exception e) {
             e.printStackTrace();
         }
         calendar.add(Calendar.DAY_OF_MONTH, flag);  //设置为前一天
         String yesterday = sdf.format(calendar.getTime());//获得前一天
         return yesterday;
     }

	//获取打卡结果
	private static List<Recordresult> getData(DingTalkClient client, String accessToken,List<String> userIdList,String workDateFrom, String workDateTo) throws Exception{
		client = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
		OapiAttendanceListRequest request = new OapiAttendanceListRequest();
		request.setWorkDateFrom(workDateFrom);
		request.setWorkDateTo(workDateTo);
		request.setUserIdList(userIdList);
		request.setOffset(0L);
		request.setLimit(50L);
		OapiAttendanceListResponse response = client.execute(request,accessToken);
		List<Recordresult> recordList = response.getRecordresult();
		return recordList;
	}
	
	 public static String getAttendance(Map<String, Object> map ,String access_token_str) {
	        String dingDingAttendance = "https://oapi.dingtalk.com/attendance/list?access_token="+access_token_str;
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("workDateFrom",map.get("workDateFrom"));
	        jsonObject.put("workDateTo",map.get("workDateTo"));
	        jsonObject.put("limit",map.get("limit"));
	        jsonObject.put("offset",map.get("offset"));
	        jsonObject.put("userIdList",map.get("userIdList"));
	        return doPost(dingDingAttendance,jsonObject);//获取考勤记录
	 
	    }


	//获取部门用户
	//"userlist":[{"name":"牛德源","userid":"213121072228921172"},{"name":"张智伟","userid":"042262656224235781"},{"name":"徐美玲","userid":"150906420524551796"}]}
	private static List<Userlist> getUser(DingTalkClient client, String accessToken, long deptId) throws Exception{
		client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
		OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
		request.setDepartmentId(deptId);//获取的部门id
		request.setOffset(0L);//支持分页查询，与size参数同时设置时才生效，此参数代表偏移量
		request.setSize(10L);//支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100
		request.setOrder("entry_asc");
		/*支持分页查询，部门成员的排序规则，默认不传是按自定义排序；
		entry_asc：代表按照进入部门的时间升序，
		entry_desc：代表按照进入部门的时间降序，
		modify_asc：代表按照部门信息修改时间升序，
		modify_desc：代表按照部门信息修改时间降序，
		custom：代表用户定义(未定义时按照拼音)排序*/
		request.setHttpMethod("GET");
		OapiUserSimplelistResponse response = client.execute(request, accessToken);
		List<Userlist> userList =  response.getUserlist();
		return userList;
	}
	
	//获取部门用户userid列表
	//[042262656224235781, 213121072228921172, 150906420524551796]
	private static List<String> getDeptUser(DingTalkClient client, String accessToken, String deptId) throws Exception{
		client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getDeptMember");
		OapiUserGetDeptMemberRequest req = new OapiUserGetDeptMemberRequest();
		req.setDeptId(deptId);//部门id
		req.setHttpMethod("GET");
		OapiUserGetDeptMemberResponse rsp = client.execute(req, accessToken);
		List<String> userIdList = rsp.getUserIds();
		return userIdList;
	}
	
	//获取子部门ID列表
	//[120356124, 94058110, 93977150, 94045157]
	private static List<Long> getSubDept(DingTalkClient client, String accessToken, String deptId) throws Exception{
		client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list_ids");
		OapiDepartmentListIdsRequest request = new OapiDepartmentListIdsRequest();
		request.setId(deptId);//父部门id。根部门的话传1
		request.setHttpMethod("GET");
		OapiDepartmentListIdsResponse response3 = client.execute(request, accessToken);
		List<Long> subDeptList = response3.getSubDeptIdList();
		return subDeptList;
	}
	
	//获取部门列表
	//{"createDeptGroup":true,"name":"数字营销服务部","id":94020168,"autoAddUser":true,"parentid":87676139}
	private static List<Department> getDept(DingTalkClient client, String accessToken) throws Exception{
		client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
		OapiDepartmentListRequest request = new OapiDepartmentListRequest();
		request.setId("1");//父部门id（如果不传，默认部门为根部门，根部门ID为1
		request.setHttpMethod("GET");
		OapiDepartmentListResponse response = client.execute(request, accessToken);
		List<Department> departList = response.getDepartment();
		return departList;
	}
	
	private static String getAccess_token(DingTalkClient client) throws Exception{
		client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
		OapiGettokenRequest request = new OapiGettokenRequest();
		request.setAppkey("dingdgiqxnj8qyayn7za");
		request.setAppsecret("bg0yMqmoQTDfHSB7K1HTYJ4HYB727Ua9t4YLJxRsuGaBB926M6MH3oNW8_2YwJqz");
		request.setHttpMethod("GET");
		OapiGettokenResponse response = client.execute(request);
		return response.getAccessToken();
	}
}