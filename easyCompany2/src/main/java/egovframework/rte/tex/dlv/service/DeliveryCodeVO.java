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
package egovframework.rte.tex.dlv.service;

import java.io.Serializable;


/**
 * 납품코드 VO클래스를 정의한다.
 * 
 * @author 실행환경 개발팀 이영진
 * @since 2011.06.07
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.07  이영진          최초 생성
 * 
 * </pre>
 */
public class DeliveryCodeVO implements Serializable{
	
	private static final long serialVersionUID = -157665702921187881L;
	
	/** 납품 코드 */
	private String dlvySe; 

	/** 납품 상태 */
	private String dlvySttus; 
	
	/**
	 * 납품 코드를 반환한다.
	 * @return String 납품 코드
	 */
	public String getDlvySe() {
		return dlvySe;
	}
	/**
	 * 납품코드를 설정한다.
	 * @param dlvySe 납품코드
	 */
	public void setDlvySe(String dlvySe) {
		this.dlvySe = dlvySe;
	}
	/**
	 * 납품상태명을 반환한다.
	 * @return String 납품상태명
	 */
	public String getDlvySttus() {
		return dlvySttus;
	}
	/**
	 * 납품상태명을 설정한다.
	 * @param dlvySttus 납품상태명
	 */
	public void setDlvySttus(String dlvySttus) {
		this.dlvySttus = dlvySttus;
	}
	
	
}
