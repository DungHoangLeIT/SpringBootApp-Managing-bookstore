package com.example.employee.Util;

public class ValidateUtil {
    public static class FILE_STYLE {
        public static final String BANNER = "BANNER";
        public static final String PROFILE = "PROFILE";
    }
    public static boolean regexValidation(String username, String input) {
        return (input).matches(input);
    }
    public static class ROLE {
        public static final Integer ADMIN = 2;
    }
    public static class VALIDATE_INPUT {
        public static final String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        public static final String regexPhone = "^[0-9]{10}$";
    }
    public static class MESSAGE_CODE {
        public static final String USER_NOT_FOUND = "USER NOT FOUND";
        public static final String TRUNG = "tài khoản đã tồn tại";
        public static final String BOOK_NOT_FOUND = " không tìm thấy id sách";
        public static final String TOKEN_INVALID ="TOKEN INVALID" ;
        public static final String INVALID_FILE_TYPE = "INVALID FILE TYPE";
        public static final String FILE_NOT_EXIST = "FILE NOT EXIST";
    }
}
