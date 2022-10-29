package Database;
import android.provider.BaseColumns;

public final class DeliveryReqTable {

    private DeliveryReqTable(){}

    public static class DeliveryReq implements BaseColumns {
        public static final String TABLENAME = "DeliveryReq";
        public static final String REQID = "reqID";
        public static final String PATIENTNAME = "patientName";
        public static final String AREA = "area";
        public static final String CONTACT = "Contact";
        public static final String IMAGENAME = "prescriptionImage";
        public static final String STATUS = "status";
        public static final String DATE = "requestedDate";
        public static final String PHARMACYNAME = "PharmacyName";
        public static final String EMAIL = "UserEmail";
    }
}
