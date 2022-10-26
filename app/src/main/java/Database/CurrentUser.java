package Database;

import android.provider.BaseColumns;

public class CurrentUser {
    private CurrentUser(){}

    public static class PresentUser implements BaseColumns {
        public static final String TABLENAME = "CurrentUser";
        public static final String id = "id";
        public static final String EMAIL = "UserEmail";
    }
}
