package Database;

import android.provider.BaseColumns;

public class InfoBeforeImage {
    private InfoBeforeImage(){}

    public static class Info implements BaseColumns {
        public static final String TABLENAME = "InfoBeforeDelete";
        public static final String ID = "ID";
        public static final String NAME = "PatientName";
        public static final String AREA = "Location";
        public static final String CONTACT = "Contact";
    }
}
