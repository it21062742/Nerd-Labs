package Database;

import android.provider.BaseColumns;

public final class DeliveryReqTable {

    private DeliveryReqTable(){}

    public static class DeliveryReq implements BaseColumns {
        public static final String TABLENAME = "DeliveryReq";
        public static final String REQID = "reqID";
        public static final String PatientName = "patientName";
        public static final String AREA = "area";
        public static final String CONTACT = "Contact";
        public static final String IMAGE = "prescriptionImage";
        public static final String DATE = "requestedDate";
        public static final String PHARMACYNAME = "PharmacyName";
        public static final String EMAIL = "UserEmail";
    }

    // DeliveryRequestTable(reqId, PatientName, Area, Contact,
    // prescriptionImage, requestedDate, PharmacyName, UserEmail)
    // {patEmail and pharmacyName are fk from patient and pharmacy}
}
