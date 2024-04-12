package com.example.navigation.Crud;

import android.app.Application;

public class App extends Application {
   // tạo một biến static để lưu trữ một đối tượng của lớp Ao.
    public  static  Ao ao;
}
//Tóm lại, sử dụng Interface và cơ chế lắng nghe sự kiện giúp tạo ra
// một cách tương tác giữa các thành phần của ứng dụng một cách rõ ràng
// và linh hoạt. Interface cho phép bạn định nghĩa các hành vi sự kiện
// một cách tổng quát, trong khi Adapter và các thành phần khác có thể
// tương tác với các đối tượng cụ thể implement Interface này một cách
// linh hoạt, không cần biết về các chi tiết cụ thể của đối tượng
// implement Interface. Điều này tạo ra một mức độ phân tách tốt giữa
// các thành phần của ứng dụng, giúp mã nguồn của bạn trở nên dễ đọc và bảo trì hơn.