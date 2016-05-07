package com.sdw.soft.wekeeper.web.action;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sdw.soft.wekeeper.common.user.vo.SysUser;
import com.sdw.soft.wekeeper.common.utils.VerifyCodeUtil;

/**
 * author shangyd
 * date 2015年11月8日
 **/
@Controller
@RequestMapping("/landing")
public class LandingController {
	
	private static final Logger logger = LoggerFactory.getLogger(LandingController.class);
	
	@RequestMapping("/index")
	public String index(){
		logger.info("login index");
		return "pub/index";
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/fetchVerifyCode",method=RequestMethod.GET)
	public void fetchVerifyCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute("verifyCode", verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
		
	}
	
	@RequestMapping(value="/check",method=RequestMethod.POST)
	public String checkLogin(SysUser loginUser,@RequestParam("verifyCode") String verifyCode,HttpServletRequest request,ModelMap model){
		String redirectUrl = "pub/index";
		logger.info("username={},password={},输入验证码={}",loginUser.getUsername(),loginUser.getPassword(),verifyCode);
		String sessionVerifyCode = (String) request.getSession().getAttribute("verifyCode");
		if(StringUtils.isNotBlank(sessionVerifyCode) && !sessionVerifyCode.equals(verifyCode)){
			model.addAttribute("message","验证码不正确");
			return redirectUrl;
		}
		UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(), loginUser.getPassword());
		token.setRememberMe(true);
		
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
			HttpSession session = request.getSession(true);
			try {
				SysUser user = new SysUser();
			} catch (Exception e) {
				e.printStackTrace();
			}
			redirectUrl = "layout/main";
		} catch (UnknownAccountException uae) {
			logger.info("用户[{}] 未知",loginUser.getUsername());
//			request.setAttribute("message", "未知用户");
			model.addAttribute("message", "未知用户");
		}catch (IncorrectCredentialsException ice){
			logger.info("用户[{}] 输入密码不正确",loginUser.getUsername());
			model.addAttribute("message", "用户名/密码不正确");
		}catch(LockedAccountException lae){
			logger.info("用户[{}] 账户已锁定",loginUser.getUsername());
			model.addAttribute("message", "账户已锁定");
		}catch(ExcessiveAttemptsException eae){
			logger.info("用户[{}] 验证未通过 错误次数过多",loginUser.getUsername());
			model.addAttribute("message", "用户名/密码验证错误次数过多");
		}catch(AuthenticationException ae){
			logger.info("用户[{}]验证未通过 " ,loginUser.getUsername());
			ae.printStackTrace();
			model.addAttribute("message", "用户名/密码不正确");
		}
		
		if(currentUser.isAuthenticated()){
			logger.info("用户[{}]验证通过",loginUser.getUsername());
		}else{
			token.clear();
		}
		return redirectUrl;
	}
}
