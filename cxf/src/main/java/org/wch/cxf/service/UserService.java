package org.wch.cxf.service;

import org.wch.cxf.bean.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author weichunhe
 *         Created on 2016/12/2.
 * @version 1.0
 */
@WebService
public interface UserService {
    @WebMethod
    String getName(@WebParam(name = "userId") Long userId);

    @WebMethod
    User getUser(Long userId);
}
