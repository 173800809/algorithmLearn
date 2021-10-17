import java.util.List;

/**
 * @author xiangyuan.ni
 */
public class MakeInvoiceVo {
    /** 提现单编号列表 */
    private List<String> withdrawNoList;

    /** 发票信息列表 */
    private List<InvoiceVo> invoiceVoList;

    public List<String> getWithdrawNoList() {
        return withdrawNoList;
    }

    public void setWithdrawNoList(List<String> withdrawNoList) {
        this.withdrawNoList = withdrawNoList;
    }

    public List<InvoiceVo> getInvoiceVoList() {
        return invoiceVoList;
    }

    public void setInvoiceVoList(List<InvoiceVo> invoiceVoList) {
        this.invoiceVoList = invoiceVoList;
    }

    public static class InvoiceVo {
        /** 发票号码 */
        private String invoiceNo;

        /** 发票金额 */
        private String invoiceAmount;

        /** 发票机构名称和提现系统的提现名称相同 */
        private String withdrawName;

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getInvoiceAmount() {
            return invoiceAmount;
        }

        public void setInvoiceAmount(String invoiceAmount) {
            this.invoiceAmount = invoiceAmount;
        }

        public String getWithdrawName() {
            return withdrawName;
        }

        public void setWithdrawName(String withdrawName) {
            this.withdrawName = withdrawName;
        }

        @Override
        public String toString() {
            return "InvoiceVo{" +
                    "invoiceNo='" + invoiceNo + '\'' +
                    ", invoiceAmount='" + invoiceAmount + '\'' +
                    ", withdrawName='" + withdrawName + '\'' +
                    '}';
        }
    }
}
