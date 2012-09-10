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
package egovframework.rte.tex.com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.AccessDeniedException;
import org.springframework.security.ui.AccessDeniedHandlerImpl;

/**  
 * @Class Name : ImagePaginationRenderer.java
 * @Description : ImagePaginationRenderer Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 * 
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */
public class EgovAccessDeniedHandlerImpl extends AccessDeniedHandlerImpl {
	private String errorPage;

	public void handle(ServletRequest request, ServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		
		
		if (errorPage != null) {
			// Put exception into request scope (perhaps of use to a view)
			((HttpServletRequest) request).setAttribute(
					SPRING_SECURITY_ACCESS_DENIED_EXCEPTION_KEY,
					accessDeniedException);

			// Perform RequestDispatcher "forward"
			//RequestDispatcher rd = request.getRequestDispatcher(errorPage);
			//rd.forward(request, response);
			
			((HttpServletResponse)response).sendRedirect(errorPage);

		}

		if (!response.isCommitted()) {
			// Send 403 (we do this after response has been written)
			((HttpServletResponse) response).sendError(
					HttpServletResponse.SC_FORBIDDEN,
					accessDeniedException.getMessage());
		}
	}

	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}

}
