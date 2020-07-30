package com.ruoyi.web.controller.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.dingtalk.api.response.OapiDepartmentListResponse.Department;
import com.dingtalk.api.response.OapiUserSimplelistResponse.Userlist;
import com.ruoyi.system.domain.Dingding;
import com.ruoyi.system.domain.OaDingding;
import com.ruoyi.system.domain.OaDingdingUser;
import com.ruoyi.system.mapper.OaDingdingUserMapper;
import com.ruoyi.system.service.IOaDingdingService;
import com.ruoyi.system.service.IOaDingdingUserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("deprecation")
@Component("dingding")
public class DingDingTask {

    private static String accessTokenUrl = "https://oapi.dingtalk.com/gettoken";//通用
    private static String corpId = "ding306f151d37f14d4035c2f4657eb6378f";
    private static String corpSecret = "3wJRsyR6kyWF23I4SuesPZfuwmDpsqXmiAovTREP_7sf1vf9RdjobpHd0fPeZdTk";
    @Autowired
    IOaDingdingService dingdingService;
    @Autowired
    IOaDingdingUserService dingdingUserService;
    @Autowired
    OaDingdingUserMapper oaDingdingUserMapper;

    /**
     * num = -1 生成昨天的考勤
     */
    public void dingDingTask(String num) throws Exception {

        DingTalkClient client = new DefaultDingTalkClient("");

        String accessToken = getAccess_token(client);

        //获取部门列表
        List<Department> deptList = getDept(client, accessToken);//41

        //获取子部门ID列表
        //[120356124, 94058110, 93977150, 94045157]
        Set<Long> allDeptSet = new HashSet<Long>();//所有部门id 36
        for (Department dept : deptList) {
            List<Long> subDeptList = getSubDept(client, accessToken, dept.getId().toString());
            allDeptSet.addAll(subDeptList);
        }

        //获取部门用户userid列表
        //[042262656224235781, 213121072228921172, 150906420524551796]
        List<String> allUserIdList = new ArrayList<String>();//所有用户id  186
        for (Long deptId : allDeptSet) {
            List<String> userIdList = getDeptUser(client, accessToken, deptId.toString());
            allUserIdList.addAll(userIdList);
        }

        //获取部门用户
        //"userlist":[{"name":"牛德源","userid":"213121072228921172"},{"name":"张智伟","userid":"042262656224235781"},{"name":"徐美玲","userid":"150906420524551796"}]}
        List<OaDingdingUser> dingUserList = new ArrayList<OaDingdingUser>();
        for (Long deptId : allDeptSet) {
            List<Userlist> userList = getUser(client, accessToken, deptId);
            for (Userlist user : userList) {
                OaDingdingUser dingUser = new OaDingdingUser();
                if ("12485610011037053255".equals(user.getUserid())) continue;
                if (!"manager8676".equals(user.getUserid())) {
                    if ("3144041311-1058488227".equals(user.getUserid())) {
                        dingUser.setUserId(Long.parseLong("314404131110584882"));
                    } else {
                        if (user.getUserid().length() > 19) {
                            dingUser.setUserId(Long.parseLong(user.getUserid().substring(0, 19)));
                        } else {
                            dingUser.setUserId(Long.parseLong(user.getUserid()));
                        }
                    }
                } else {
                    dingUser.setUserId(8676L);
                }
                dingUser.setUserName(user.getName());
                dingUserList.add(dingUser);
            }
        }
        dingdingUserService.insertForeach(dingUserList);//用户总数172


        //设置查询时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);

        String yesterday = getPreDayOrAfterDay(currentDate, Integer.parseInt(num));//-1是前一天， +1是后一天
        String workDateFrom = yesterday + " " + "00:00:00";
        String workDateTo = yesterday + " " + "23:59:59";//当前时间

//        String fromDate = getPreDayOrAfterDay(currentDate, -3);//-1是前一天， +1是后一天
//        String toDate = getPreDayOrAfterDay(currentDate, -1);
//        String workDateFrom = fromDate + " " + "00:00:00";
//        String workDateTo = toDate+ " " + "23:59:59";//当前时间
//


        List<OaDingding> users = new ArrayList<>();
        List<OaDingding> dataList = getAttendances(allUserIdList, users, workDateFrom, workDateTo, accessToken);
        if (dataList.size() != 0) {
            dingdingService.insertForeach(dataList);
        }
//        System.out.println("获取从昨天0点到今天0点时间的打卡记录"+ Arrays.asList(users));


        //根据请假、外出报备、补卡修改钉钉打卡考勤结果
//        dingdingService.updateOaDingDingByOutAndApply();
        dingdingService.updateDingdingByApply();
    }

    /**
     * 计算当月三次迟到累计迟到时长是否满足小于等于60min
     */
    public void updateOaDingDingByCountLate() {
        dingdingService.updateOaDingDingByCountLate();
    }


    public void dingDingUpdateName() throws Exception {
        Long wangzhenzhen = 12333319631237216L;//王震震
        Long weiyuanhao = 1248012138879414L;//隗元昊
        Long quyilin1 = 135416124323359665L;//屈伊琳1
        Long liyang01 = 1765181417846298L;//李扬01
        Long chenchao01 = 4162903061228861L;//陈超01
        Long chenyong02 =110320021501213823L;
        OaDingding dingding = new OaDingding();
        dingding.setUserId(wangzhenzhen);
        dingding.setUserName("王震震");
        dingdingService.updateOaDingding(dingding);
        OaDingding dingding2 = new OaDingding();
        dingding2.setUserId(weiyuanhao);
        dingding2.setUserName("隗元昊");
        dingdingService.updateOaDingding(dingding2);
        OaDingding dingding3 = new OaDingding();
        dingding3.setUserId(quyilin1);
        dingding3.setUserName("屈伊琳1");
        dingdingService.updateOaDingding(dingding3);
        OaDingding dingding4 = new OaDingding();
        dingding4.setUserId(liyang01);
        dingding4.setUserName("李扬01");
        dingdingService.updateOaDingding(dingding4);
        OaDingding dingding5 = new OaDingding();
        dingding5.setUserId(chenchao01);
        dingding5.setUserName("陈超01");
        dingdingService.updateOaDingding(dingding5);
        OaDingding dingding6 = new OaDingding();
        dingding6.setUserId(chenyong02);
        dingding6.setUserName("陈勇02");
        dingdingService.updateOaDingding(dingding6);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);
        String yesterday = getPreDayOrAfterDay(currentDate, Integer.parseInt("-1"));//-1是前一天， +1是后一天
        Date work = sdf.parse(yesterday);
        //   Date work = sdf.parse("2020-07-15");
        Long yanghuiting = 184061524626231551L;//杨惠婷
        xiugaishijian(yanghuiting, work);
        Long tanpeiyu = 672951194935145472L;//  谭佩瑜
        xiugaishijian(tanpeiyu, work);
    }

    /**
     * 修改上下班时间 9:00   18:00
     *
     * @return 修改上下班时间1
     */
    public String xiugaishijian(Long id, Date work) throws Exception {
        Dingding dingding = new Dingding();
        dingding.setUserId(id);
        dingding.setWorkDate(work);
        List<Dingding> a = dingdingService.selectOaDingList(dingding);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
        String workOn = sdfs.format(work) + " " + "09:00:00";
        String workoff = sdfs.format(work) + " " + "18:00:00";//下班时间
        Date dateTime1 = sdf.parse(workOn);
        Date dateTime2 = sdf.parse(workoff);
        for (Dingding v : a) {
            if (v.getCheckType().equals("OnDuty")) {  //上班时间
                int i = dateTime1.compareTo(v.getUserCheckTime());
                if (i < 0) {
                    //如果小于证明时间大于9点上班正常
                    v.setTimeResult("Late");
                    v.setStatus("1");
                    v.setCheckType("OnDuty");
                    dingdingService.updateDingdingOnDuty(v);

                }

            } else {//下班时间
                int i = v.getUserCheckTime().compareTo(dateTime2);
                if (i > 0) {
                    //如果大于证明时间大于18点上班正常
                    v.setTimeResult("Normal");
                    v.setStatus(null);
                    v.setCheckType("OffDuty");
                    dingdingService.updateDingdingOnDuty(v);

                }
            }

        }
        return "";
    }


    //弹性工作制
    public void dingElasticTime(String num) {
        //弹性工作制
        //设置查询时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);

        String yesterday = getPreDayOrAfterDay(currentDate, Integer.parseInt(num));//-1是前一天， +1是后一天
        dingdingService.updateOaDingDingByElasticTime(yesterday);
    }

    //特殊下班（16:00）
    public void dingSpecialTime(String time) {
        dingdingService.updateOaDingDingBySpecialTime(time);
    }

    public List<OaDingding> getAttendances(List<String> list, List<OaDingding> users, String workDateFrom, String workDateTo, String access_Token) {
        int listSize = list.size();
        int toIndex = 50;
        for (int i = 0, length = list.size(); i < length; i += 50) {
            if (i + 50 > listSize) {        //作用为toIndex最后没有50条数据则剩余几条newList中就装几条
                toIndex = listSize - i;
            }
            List<String> newList = list.subList(i, i + toIndex);
            Boolean hasMore = true;
            int offset = 0;//为了分页
            do {
                Map<String, Object> mapParam = new HashMap<>();
                mapParam.put("workDateFrom", workDateFrom);
                mapParam.put("workDateTo", workDateTo);
                mapParam.put("userIdList", newList);
                mapParam.put("offset", offset * 50);
                mapParam.put("limit", 50);
                String attendanceStr = getAttendance(mapParam, access_Token);
                JSONObject firstJson = JSONObject.parseObject(attendanceStr);

                hasMore = firstJson.getBoolean("hasMore");
                JSONArray recordFirst = firstJson.getJSONArray("recordresult");//当前部门下的userList
                for (int j = 0; j < recordFirst.size(); j++) {
                    JSONObject record = recordFirst.getJSONObject(j);

                    OaDingding dingding = new OaDingding();
                    dingding.setCheckType(record.getString("checkType"));
                    if ("12485610011037053255".equals(record.getString("userId"))) continue;
                    if (!"manager8676".equals(record.getString("userId"))) {
                        if ("3144041311-1058488227".equals(record.getString("userId"))) {
                            dingding.setUserId(Long.parseLong("314404131110584882"));
                        } else {
                            if (record.getString("userId").length() > 19) {
                                dingding.setUserId(Long.parseLong(record.getString("userId").substring(0, 19)));
                            } else {
                                dingding.setUserId(Long.parseLong(record.getString("userId")));
                            }
                        }

                    } else {
                        dingding.setUserId(8676L);
                    }

                    dingding.setUserName(dingdingUserService.selectOaDingdingUserById(dingding.getUserId()).getUserName());

                    Date date = new Date();
                    date.setTime(record.getLong("workDate"));
                    dingding.setWorkDate(date);

                    //当前日期是星期几
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    dingding.setWeekDay(getWeekOfDate(date));

                    date = new Date();
                    date.setTime(record.getLong("userCheckTime"));
                    dingding.setUserCheckTime(date);

                    String timeResult = record.getString("timeResult");
                    if (!"Normal".equals(timeResult)) {
                        dingding.setStatus("1");
                    }
                    dingding.setTimeResult(timeResult);
                    users.add(dingding);
                }
                if (hasMore) {//有下一页偏移量加一
                    offset++;
                }
            } while (hasMore);
        }
        return users;
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public String getWeekOfDate(Date dt) {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }


    public String doPost(String requestUrl, JSONObject json) {
        @SuppressWarnings({"resource"})
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
//	            System.out.println(result);
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

    public String getAccessToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("corpid", corpId);
        map.put("corpsecret", corpSecret);
        return httpGetStringResult(accessTokenUrl, map);//获取access_token
    }

    public String httpGetStringResult(String url, Map<String, Object> param) {
        String content = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if (param != null && !param.isEmpty()) {
            StringBuffer strparams = new StringBuffer();
            for (Map.Entry<String, Object> map : param.entrySet()) {
                strparams.append(map.getKey()).append("=").append(map.getValue().toString()).append("&");
            }
            strparams = strparams.deleteCharAt(strparams.length() - 1);
            url = url + "?" + strparams;
        }
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity, "UTF-8");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        return content;
    }

    private String getPreDayOrAfterDay(String current, int flag) {
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


    public String getAttendance(Map<String, Object> map, String access_token_str) {
        String dingDingAttendance = "https://oapi.dingtalk.com/attendance/list?access_token=" + access_token_str;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workDateFrom", map.get("workDateFrom"));
        jsonObject.put("workDateTo", map.get("workDateTo"));
        jsonObject.put("limit", map.get("limit"));
        jsonObject.put("offset", map.get("offset"));
        jsonObject.put("userIdList", map.get("userIdList"));
        return doPost(dingDingAttendance, jsonObject);//获取考勤记录

    }


    //获取部门用户
    //"userlist":[{"name":"牛德源","userid":"213121072228921172"},{"name":"张智伟","userid":"042262656224235781"},{"name":"徐美玲","userid":"150906420524551796"}]}
    private List<Userlist> getUser(DingTalkClient client, String accessToken, long deptId) throws Exception {
        client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/simplelist");
        OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
        request.setDepartmentId(deptId);//获取的部门id
//		request.setOffset(0L);//支持分页查询，与size参数同时设置时才生效，此参数代表偏移量
//		request.setSize(100L);//支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100
        request.setOrder("entry_asc");
		/*支持分页查询，部门成员的排序规则，默认不传是按自定义排序；
		entry_asc：代表按照进入部门的时间升序，
		entry_desc：代表按照进入部门的时间降序，
		modify_asc：代表按照部门信息修改时间升序，
		modify_desc：代表按照部门信息修改时间降序，
		custom：代表用户定义(未定义时按照拼音)排序*/
        request.setHttpMethod("GET");
        OapiUserSimplelistResponse response = client.execute(request, accessToken);
        List<Userlist> userList = response.getUserlist();
        return userList;
    }

    //获取部门用户userid列表
    //[042262656224235781, 213121072228921172, 150906420524551796]
    private List<String> getDeptUser(DingTalkClient client, String accessToken, String deptId) throws Exception {
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
    private List<Long> getSubDept(DingTalkClient client, String accessToken, String deptId) throws Exception {
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
    private List<Department> getDept(DingTalkClient client, String accessToken) throws Exception {
        client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId("1");//父部门id（如果不传，默认部门为根部门，根部门ID为1
        request.setHttpMethod("GET");
        OapiDepartmentListResponse response = client.execute(request, accessToken);
        List<Department> departList = response.getDepartment();
        return departList;
    }

    private String getAccess_token(DingTalkClient client) throws Exception {
        client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey("dingdgiqxnj8qyayn7za");
        request.setAppsecret("bg0yMqmoQTDfHSB7K1HTYJ4HYB727Ua9t4YLJxRsuGaBB926M6MH3oNW8_2YwJqz");
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        return response.getAccessToken();
    }
}
