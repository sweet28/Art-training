package com.carpi.api.pojo;

import java.util.Date;

public class FensAuthentication {
    private Integer id;

    private String bak1;

    private String bak2;

    private String bak3;

    private Integer isDelete;

    private Date createDate;

    private String creater;

    private Date deleteDate;

    private String attachment;

    private Integer fensUserId;

    private Integer cardType;

    private String cardNumber;

    private String cardFrontpic;

    private String cardBehindpic;

    private String cardUserpic;

    private String goolePrivateKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBak1() {
        return bak1;
    }

    public void setBak1(String bak1) {
        this.bak1 = bak1 == null ? null : bak1.trim();
    }

    public String getBak2() {
        return bak2;
    }

    public void setBak2(String bak2) {
        this.bak2 = bak2 == null ? null : bak2.trim();
    }

    public String getBak3() {
        return bak3;
    }

    public void setBak3(String bak3) {
        this.bak3 = bak3 == null ? null : bak3.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public Integer getFensUserId() {
        return fensUserId;
    }

    public void setFensUserId(Integer fensUserId) {
        this.fensUserId = fensUserId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public String getCardFrontpic() {
        return cardFrontpic;
    }

    public void setCardFrontpic(String cardFrontpic) {
        this.cardFrontpic = cardFrontpic == null ? null : cardFrontpic.trim();
    }

    public String getCardBehindpic() {
        return cardBehindpic;
    }

    public void setCardBehindpic(String cardBehindpic) {
        this.cardBehindpic = cardBehindpic == null ? null : cardBehindpic.trim();
    }

    public String getCardUserpic() {
        return cardUserpic;
    }

    public void setCardUserpic(String cardUserpic) {
        this.cardUserpic = cardUserpic == null ? null : cardUserpic.trim();
    }

    public String getGoolePrivateKey() {
        return goolePrivateKey;
    }

    public void setGoolePrivateKey(String goolePrivateKey) {
        this.goolePrivateKey = goolePrivateKey == null ? null : goolePrivateKey.trim();
    }
}