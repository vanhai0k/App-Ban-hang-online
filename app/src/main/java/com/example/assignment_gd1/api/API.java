package com.example.assignment_gd1.api;

public class API {
    String url = "http://localhost:3000/api/users";
    public static final String API_GET ="http://192.168.1.6:3000/api/users";
//    public static final String API_GET ="http://10.24.45.15:3000/api/users";
    public static final String API_POST ="http://192.168.1.6:3000/api/users";

    public static final String API_GETUser ="http://192.168.1.6:3000/api/user";
    public static final String API_PostUser ="http://192.168.1.6:3000/api/user";
    public static final String API_DeleteUser ="http://192.168.1.6:3000/api/user/delete/";

    public static final String API_GetComment ="http://192.168.1.6:3000/api/comment?id_product=";
    public static final String API_PostComment ="http://192.168.1.6:3000/api/comment";

    // gio hang
    public static final String API_PostGiohang ="http://192.168.1.6:3000/api/giohang";


    // mua san pham
    public static final String API_Get_Comment ="http://192.168.1.6:3000/api/comment?id_product=";

    public static final String API_Get_Commentgiohang1pro ="http://192.168.1.6:3000/api/giohang?id_user=";
}
