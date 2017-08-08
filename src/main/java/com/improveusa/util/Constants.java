package com.improveusa.util;

/**
 * Constants
 *
 * @author Mykhailo Soltys
 */
public interface Constants {
    /* Base PATHs */
    String API_PV =     "/api";
    String VENDOR_PV =  "/{vendor}";
    String CID_PV =      "/{cid}";
    String SID_PV =      "/{sid}";

    String CATALOG_PATH =       API_PV + "/catalog";
    String ALL_SUB_PATH =       VENDOR_PV + "/all";
    String STATISTIC_SUB_PATH = VENDOR_PV + "/statistic";

    String CATEGORIES_SUB_PATH =    VENDOR_PV + "/categories";
    String SERVICES_SUB_PATH =      CATEGORIES_SUB_PATH + CID_PV + "/services";
    String QUESTIONS_SUB_PATH =     SERVICES_SUB_PATH + SID_PV + "/questions";
}