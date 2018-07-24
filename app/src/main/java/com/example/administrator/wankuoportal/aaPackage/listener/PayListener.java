package com.example.administrator.wankuoportal.aaPackage.listener;

/**
 */
public interface PayListener {

    void paySuccess(String orderId);

    void payFail(String orderId);

    void payCancel();
}
