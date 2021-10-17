import java.math.BigDecimal;
import java.util.Date;


public class MakeInvoice  {

    @javax.validation.constraints.NotNull(message = "分成比例信息不能为空")
    private Long id;

    private String makeInvoiceNo;

    private Integer invoiceStatus;

    private Date invoiceMakeTime;

    private Date invoiceReceivedTime;

    private Integer operationType;

    private BigDecimal invoicesTotalAmount;

    private BigDecimal withdrawAmount;

    // 注意: 在新建该对象时，如果对象没有关联不能将balance设置为0，
    // 0表示已经关联但发票金额和提现记录差额为0，
    // null表示没有关联，未设置差额
    private BigDecimal balance;

    private Long userId;

    private String userName;

    private String merchantOrderNo;

    private BigDecimal offlineAmount;

    private boolean isDiscard;

    private int temp;

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMakeInvoiceNo() {
        return makeInvoiceNo;
    }

    public void setMakeInvoiceNo(String makeInvoiceNo) {
        this.makeInvoiceNo = makeInvoiceNo;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Date getInvoiceMakeTime() {
        return invoiceMakeTime;
    }

    public void setInvoiceMakeTime(Date invoiceMakeTime) {
        this.invoiceMakeTime = invoiceMakeTime;
    }

    public Date getInvoiceReceivedTime() {
        return invoiceReceivedTime;
    }

    public void setInvoiceReceivedTime(Date invoiceReceivedTime) {
        this.invoiceReceivedTime = invoiceReceivedTime;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getInvoicesTotalAmount() {
        return invoicesTotalAmount;
    }

    public void setInvoicesTotalAmount(BigDecimal invoicesTotalAmount) {
        this.invoicesTotalAmount = invoicesTotalAmount;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public BigDecimal getOfflineAmount() {
        return offlineAmount;
    }

    public void setOfflineAmount(BigDecimal offlineAmount) {
        this.offlineAmount = offlineAmount;
    }

    public boolean isDiscard() {
        return isDiscard;
    }

    public void setDiscard(boolean discard) {
        isDiscard = discard;
    }
}