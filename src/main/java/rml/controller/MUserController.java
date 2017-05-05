package rml.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rml.dto.AccessToken;
import rml.model.Base;
import rml.model.LoginResponse;
import rml.model.UserWeapp;
import rml.request.UserRequest;
import rml.service.JsonMapper;
import rml.service.MUserServiceI;

import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/User")
public class MUserController {

	public static final String appid = "wxa97f30ee6cd9ffc0";
	public static final String GRANT_TYPE = "authorization_code";
	public static final String secret = "96b0d8c25e65644476f1657991429bbb";
	public static final String TOKEN_URL = "https://api.weixin.qq.com/sns/jscode2session";

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MUserServiceI muserService;


	private JsonMapper jsonMapper;


	public JsonMapper getJsonMapper() {
		return jsonMapper;
	}

	@Autowired
	public void setJsonMapper(JsonMapper jsonMapper) {
		this.jsonMapper = jsonMapper;
	}

	

/*
	public MUserServiceI getMuserService() {
		return muserService;
	}

	@Autowired
	public void setMuserService(MUserServiceI muserService) {
		this.muserService = muserService;
	}
*/

	/*@RequestMapping(value = "/listUser")
	@ResponseBody
	public String listUser(HttpServletRequest request) {

		List<User> list = muserService.getAll();
		request.setAttribute("userlist", list);
		return "listUser";
	}*/

	@RequestMapping(value = "/userWeapp/update", method = RequestMethod.POST)
	@ResponseBody
	public Base addUser(UserRequest userRequest) {

		Base base = new Base();
		try {

			if(muserService.updateUserWeapp(userRequest) == 0){
				throw  new Exception("没有此用户");
			};

			base.setCode(0);
			base.setState("成功");
			base.setData("用户数据更新成功");
			return base;
		} catch (Exception e) {
			base.setCode(2);
			base.setState(e.getMessage());
			base.setState(null);
			return base;
		}


	/*	ReturnJson json = new ReturnJson();
		if (StringUtils.isEmpty(userRequest.getMobile())) {
			json.setErrorCode(1001);
			json.setReturnMessage("输入参数为空或者输入参数不正确");
			json.setServerStatus(1);
		}
		//String id = UUID.randomUUID().toString().replaceAll("-", "");

		try {
			muserService.insert(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			json.setReturnMessage("服务器错误");
			json.setServerStatus(2);
			json.setErrorCode(1002);
			return json;
		}
		json.setReturnValue(user.getUserId());
		json.setReturnMessage("调用成功");
		return json;*/
	}

	@RequestMapping(value = "/appLogin")
	@ResponseBody

	public Base appLogin(String code) {

		//ReturnUser json = new ReturnUser();
		Base base = new Base();

		try {
			if (code == null) {
				throw new Exception("参数异常");
			}

			AccessToken dto = null;
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("appid", appid);
			params.put("secret", secret);
			params.put("js_code", code);
			params.put("grant_type", GRANT_TYPE);
			//String tokenStr = OkHttpUtil.run(TOKEN_URL, null, params);
			//	String tokenStr = HttpUtil.getInstance().httpGet(TOKEN_URL, params);
			//	String tokenStr = HttpPostUtils.getInstance().postHttps(TOKEN_URL,params);

			CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost("https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + GRANT_TYPE);
			CloseableHttpResponse response = closeableHttpClient.execute(post);
			String tokenStr = EntityUtils.toString(response.getEntity());
			HashMap<String, Object> tokenInfo = jsonMapper.toMap(tokenStr);
			System.out.print("tokenInfo---" + tokenInfo.toString());

			if (tokenInfo != null && !tokenInfo.containsKey("errcode")) {
				dto = new AccessToken();
				dto.setOpenid(String.valueOf(tokenInfo.get("openid")));
				dto.setSessionKey(String.valueOf(tokenInfo.get("session_key")));
				// dto.setScope(String.valueOf(tokenInfo.get("scope")));
				if (dto != null) {
					UserWeapp orderuser = muserService.selectWeapp(dto.getOpenid());
					LoginResponse longinResponse = new LoginResponse();
					if(orderuser == null){
						UserWeapp userWeapp = new UserWeapp();
						userWeapp.setAppId(appid);
						userWeapp.setOpenId(dto.getOpenid());
						String id = UUID.randomUUID().toString().replaceAll("-", "");
						userWeapp.setUserId(id);
						userWeapp.setCreateTime(System.currentTimeMillis());
						muserService.insertWeapp(userWeapp);
						longinResponse.setUid(id);
						base.setData(longinResponse);

					}else{
						longinResponse.setUid(orderuser.getUserId());
						base.setData(longinResponse);
					}

					base.setCode(0);
					base.setState("成功");

				}

				} else {
					base.setCode((Integer) tokenInfo.get("errcode"));
					base.setState((String) tokenInfo.get("errmsg"));

				}




		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			base.setCode(2);
			base.setState(e.getMessage());
		}
		return base;
	}
/*
	@RequestMapping(value = "/userWeapp/insert" , method = RequestMethod.POST)
	public Base insertUser(RequestUser requestUser){


	}*/

	@RequestMapping("/insert")
	@ResponseBody
	public String insert(String code) {
		UserWeapp userWeapp = muserService.selectWeapp(code);
		if(userWeapp == null){
			return "+++++++++";
		}
		return "----------"+userWeapp.toString();
	}

}
