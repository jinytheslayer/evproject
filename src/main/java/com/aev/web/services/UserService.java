package com.aev.web.services;

import com.aev.web.services.soap.Notifications;
import com.aev.web.services.soap.Parameters;
import com.aev.web.services.soap.UserIds;
import com.aev.web.services.soap.Users;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserService {
    @WebMethod
    @WebResult(name = "notifications")
    Notifications dsUserMassCreate(@WebParam(name = "users") Users users);

    @WebMethod
    @WebResult(name = "notifications")
    Notifications dsUserMassDelete(@WebParam(name = "userId") UserIds userId);

    @WebMethod
    @WebResult(name = "users")
    Users dsUserFindListByParam(@WebParam(name = "parameters")Parameters parameters);
}