package com.sdw.soft.wekeeper.web.action;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdw.soft.wekeeper.common.utils.VerifyCodeUtil;
import com.sdw.soft.wekeeper.common.vo.ResultVo;
import com.sdw.soft.wekeeper.common.vo.SysUser;

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
	@RequestMapping("/fetchVerifyCode")
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
	
	@RequestMapping("/check")
	public @ResponseBody ResultVo checkLogin(SysUser loginUser,HttpServletRequest request){
		ResultVo result = new ResultVo();
		UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(), loginUser.getPassword());
		
		try {
			SecurityUtils.getSubject().login(token);
			HttpSession session = request.getSession(true);
			try {
				SysUser user = new SysUser();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			
		}
		
		
		return result;
	}
}
