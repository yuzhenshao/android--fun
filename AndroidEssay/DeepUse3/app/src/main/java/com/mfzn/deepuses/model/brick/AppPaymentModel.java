package com.mfzn.deepuses.model.brick;

public class AppPaymentModel {

    /**
     * orderString : app_id=2019041763954272&method=alipay.trade.app.pay&format=JSON&charset=UTF-8&sign_type=RSA2&version=1.0&return_url=https%3A%2F%2Fcs.itsurfing.cn%2Fhome%2Fpay%2Freturnpage&notify_url=https%3A%2F%2Fcs.itsurfing.cn%2Fapi%2Fpay%2Falinotify&timestamp=2019-05-05+13%3A33%3A56&sign=Baerzg%2Bg5cTdHHnMYtSiqJS25s7C6W4W6oubk%2FuC2DVBUfmeTd2uUha4DHTwuZ2eENfHH9cPAFsEQADH6XHsan6TVGUAA2Zg7o802z7ltVCvXoASgGIDCIkyH1mc0t5TEbfMbfvv0vn5LoA3KV6J9KdxCKnGzfeTjTiKJjQH0fwgWAYdvsvOV6kEpB8D4ktCZ3gbwjZKAoZGIgZWrrjpxUDKaL3Lp2CVOIeXJvd4rPP8C%2B7MUgJtW9oczDRboEvZk7rMp18EtF%2FpTt21RQSvI6y6Wv%2BaQ5ZVVS8ZDw%2BRQSGxcSiDdyzgVsleZYW8sne%2BnOf%2FI1AxisfvGShPjvFcoA%3D%3D&biz_content=%7B%22body%22%3A%22%5Cu8d2d%5Cu4e70%5Cu4f1a%5Cu5458%5Cu670d%5Cu52a1%22%2C%22subject%22%3A%22%5Cu8d2d%5Cu4e70%5Cu4f1a%5Cu5458%5Cu670d%5Cu52a1%22%2C%22out_trade_no%22%3A%222019050552501025%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D
     */

    private String orderString;

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }
}
