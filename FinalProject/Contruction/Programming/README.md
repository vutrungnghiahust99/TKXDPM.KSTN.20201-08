# Hướng dẫn cài đặt hệ thống ECOBIKE
 - Cài đặt MySQL 8.0
    - Xem hướng dẫn tại: https://www.mysql.com/
    - Sau khi cài đặt tạo username và password
 - Tạo cơ sở dữ liệu từ script trong thư mục "DetailedDesign/DataModeling"
 - Thay đổi username và password trong "src/connection/DBConnection" theo thông tin phía trên
 - Ctrl + Shift + Alt + S => Modules => Thêm thư viện: lib/java_lib và lib/javafx-sdk-11.0.2/lib
 - Thêm vào VM options: --module-path "lib/javafx-sdk-11.0.2/lib" --add-modules "javafx.controls,javafx.fxml"
 - Thêm JUnit vào project theo hướng dẫn tại: https://www.jetbrains.com/help/idea/testing.html#add-testing-libraries
