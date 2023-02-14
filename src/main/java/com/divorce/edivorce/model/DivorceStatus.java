package com.divorce.edivorce.model;

public enum DivorceStatus {
    PENDING, //it means that is pending for notary signed or the husband/wife to sign
    APPROVED, //it means that notary signed successful
    NOT_APPROVED, //notary didn't approve the divorce
    SUCCESSFUL_DIVORCE, //divorced approved by notary and ex couple
    UNSUCCESSFUL_DIVORCE, //i.e. Marriage didn't last more than two months
    NOT_ALLOW_SAME_LAWYER, //same lawyer on husband and wife
    USER_NOT_FOUND, //same of users on divorce request wasn't found
}
