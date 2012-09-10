/*
 * Copyright 2011 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.rte.tex.com.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.tex.com.service.EgovMailService;
import egovframework.rte.tex.mbr.service.MemberVO;

/**
 * 메일기능에 관한 비지니스클래스를 정의한다.
 * 
 * @author 실행환경 개발팀 신혜연
 * @since 2011.06.07
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.07  신혜연          최초 생성
 * 
 * </pre>
 */
@Service("mailService")
public class EgovMailServiceImpl extends AbstractServiceImpl implements EgovMailService {

	//SpEL이 아닌 properties로 적용할 경우
	//@Resource(name="mailInfoService")
	//protected EgovPropertyService mailInfoService ;
	
	//SpEL적용 START
	/** HOST NAME */
	@Value("#{mailInfoService.hostName}")
	private String hostName;
	
	/** PORT */
	@Value("#{mailInfoService.port}")
	private int port;
	
	/** MAIL ID */
	@Value("#{mailInfoService.mailId}")
	private String mailId;
	
	/** MAIL PASSWORD */
	@Value("#{mailInfoService.mailPass}")
	private String mailPass;
	
	/** MAIL NAME */
	@Value("#{mailInfoService.mailName}")
	private String mailName;
	
	/** MAIL SUBJECT*/
	@Value("#{mailInfoService.subject}")
	private String subject;
	//SpEL적용 END
	
	/**
	 * 회원 정보를 사용하여 회원에게 메일을 전송한다.
	 * @param vo 회원정보
	 * @return 메일전송 여부
	 */
	public boolean sendEmailTo(MemberVO vo) {
		boolean result = false;

		Email email = new SimpleEmail();

		email.setCharset("utf-8"); // 한글 인코딩

		// setHostName에 실제 메일서버정보
		// email.setHostName(mailInfoService.getString("hostName"));  // SpEL이 아닌 properties로 적용할 경우
		email.setHostName(hostName); // SMTP서버 설정
		email.setSmtpPort(port);
		email.setAuthenticator(new DefaultAuthenticator(mailId, mailPass));
		email.setTLS(true);
		try {
			email.addTo(vo.getEmail(), vo.getId()); // 수신자 추가
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.setFrom(mailId, mailName); // 보내는 사람
		} catch (EmailException e) {
			e.printStackTrace();
		}
		email.setSubject(subject); // 메일 제목
		email.setContent("ID: "+vo.getId() + "<br>" + "PASSWORD: "+vo.getPassword(), "text/plain; charset=utf-8");
		try {
			email.send();
			result = true;
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return result;
	}
}
