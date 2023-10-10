package com.example.assignment_gd1.api;

public class API {
    public static final String url = "http://192.168.1.12:3000/api/";
//    Mỗi lần thay api thì chỉ tín tiền theo này từ ngày thay api

    public static final String API_GET = url+"/users";
    public static final String API_POST =url+"/users";

    public static final String API_GETUser =url+"/user";
    public static final String API_PostUser =url+"/user";
    public static final String API_DeleteUser =url+"/delete/";

    public static final String API_GetComment =url+"/comment?id_product=";
    public static final String API_PostComment =url+"/comment";

    // gio hang
    public static final String API_PostGiohang =url+"/giohang";


    // mua san pham
    public static final String API_Get_Comment =url+"/comment?id_product=";

    public static final String API_Get_Commentgiohang1pro =url+"/giohang?id_user=";

    public static final String API_Get_Message =url+"/message?id_user=";
    public static final String API_Send_Message =url+"/message";

    public static final String API_Tongtien = url+ "sumprice";
    public static final String API_TongtienMonth = url+ "transaction";
}
