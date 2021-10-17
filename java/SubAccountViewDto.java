import java.math.BigDecimal;

/**
 * @Author lee1.li
 * @Date 12:07 下午 2021/4/21
 **/
public class SubAccountViewDto {
    private long userId;
    private String taxOrgCode;
    private BigDecimal totalAmount;
    private int accountType;
    private long subAccountId;

    public SubAccountViewDto() {
    }

    public SubAccountViewDto(long userId, String taxOrgCode, BigDecimal totalAmount, int accountType, long subAccountId) {
        this.userId = userId;
        this.taxOrgCode = taxOrgCode;
        this.totalAmount = totalAmount;
        this.accountType = accountType;
        this.subAccountId = subAccountId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTaxOrgCode() {
        return taxOrgCode;
    }

    public void setTaxOrgCode(String taxOrgCode) {
        this.taxOrgCode = taxOrgCode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public long getSubAccountId() {
        return subAccountId;
    }

    public void setSubAccountId(long subAccountId) {
        this.subAccountId = subAccountId;
    }
}
