package labs.radya.androidcodebase;

public class Constant {

    public static final String PREFERENCE_NAME = "AndroidBasePref";
    public static final String DB_NAME = "AndroidBaseDB";
    public static final String RP = "Rp. ";
    public static final int SPLASH_DELAY = 500;
    public static final String SERIALIZABLE_NAME = "serializableData";

    public class DateFormat {
        public static final String SHORT = "dd/MM/yy";
        public static final String MEDIUM = "EEE, dd/MM/yyyy";
        public static final String SERVER = "yyyy-MM-dd'T'HH:mm:ss";
    }

    public class Preference {
        public class User {
            public static final String LATITUDE = "LATITUDE";
            public static final String LONGITUDE = "LONGITUDE";
            public static final String AUTH_TOKEN = "AUTH_TOKEN";
        }
    }

    public class Key {
        public class AES {
            public static final String A = "12345678";
            public static final String B = "12345678";
        }

        public class SSL {
            public static final String TRUST = "TRUSTKEY";
        }
    }

    public class Validation {
        public static final String EMAIL_ADDRESS_EXPRESSION = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    }

    public class Api {
        private static final String DEVELOPMENT = "";

        public static final String BASE_URL = DEVELOPMENT;
        public static final int TIMEOUT = 15;
        public static final int LIMIT = 10;

        public class EndPoint {

            public class Contact {
                public static final String LIST = "contact";
                public static final String DETAIL = "contact";
                public static final String ADD = "contact";
            }

        }

        public class Params {
            public static final String AUTHORIZATION = "X-Aru-Token";
            public static final String LIMIT = "limit";
            public static final String OFFSET = "offset";
            public static final String KEYWORD = "keyword";
        }

        public class Code {
            public static final int SUCCESS = 200;
            public static final int INVALID_TOKEN = 203;
            public static final int NO_INTERNET = 400;
        }
    }

    public class Data {
        public static final String TOKEN = "uX6GQw3mxqfHnOurwRNeO32k6D0IjmL4xsT1oKjXZ+Q4XoovyNZSJdHjFSvYFudWZ3aHRvXMlI3Nj5UcDgaEWaVnpDQiNxsiglV3CJPwXoYgU0A2anPRcJVt4hKlCPFG8LPGNnMMR7F8WNlCdFlnk0WHRQXdIEXYYPxuvAz55O4sC4B84p48/Vf7FhqfkeNyy/8UZj2k/ojJ0KYjLf+hGTtrQlmk3quPjCFfJhTQix503UszX9qV7M0tvzd6dUP4OyB75SC33FYe+DcwyAgG8PBMRV0P274z8IUvei6CONE=";
        public static final String NO_INTERNET = "Connection problem\nPull to refresh";
    }

    public class Bundle {
        public static final String MESSAGE = "MESSAGE";
        public static final String ICON = "ICON";
    }

    public class Cache {
        public static final int CONTACT = 1;
    }
}
