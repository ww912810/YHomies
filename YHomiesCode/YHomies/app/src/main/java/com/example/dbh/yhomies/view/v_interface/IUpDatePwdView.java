package com.example.dbh.yhomies.view.v_interface;

public interface IUpDatePwdView {


    /**
     * 修改密码
     * 忘记密码
     *
     * @param userPhone
     * @param pwd
     */
    void updatePwdOnSuccess(String userPhone, String pwd);

    /**
     * 使用网络API接口请求方式时，虽然已经请求成功但是由
     * 于{@code msg}的原因无法正常返回数据。
     */
    void updatePwdOnFailure(String msg);

    /**
     * 请求数据失败，指在请求网络API接口请求方式时，出现无法联网、
     * 缺少权限，内存泄露等原因导致无法连接到请求数据源。
     */
    void updatePwdOnError();

}
