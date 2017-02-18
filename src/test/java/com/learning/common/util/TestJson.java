package com.learning.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

/**
 *
 * Created by liuw on 17-2-17.
 */
public class TestJson {
    @Test
    public void s() {
//        System.out.println(buildUpdateRateJsonString());
//        System.out.println(buildPayNotifyJsonString());
//        List<RepayRetDataVo> details = new ArrayList<RepayRetDataVo>();
//        System.out.println(buildRepayNotifyJsonString(details));
//        details.add(new RepayRetDataVo("loanNo", new BigDecimal("111"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("1")));
//        System.out.println(buildRepayNotifyJsonString(details));
//        details.add(new RepayRetDataVo("loanNo", new BigDecimal("111"), new BigDecimal("100"), new BigDecimal("10"), new BigDecimal("1")));
//        System.out.println(buildRepayNotifyJsonString(details));
        ElectProtocolVo electProtocolVo = new ElectProtocolVo("cus", 1);
        System.out.println(buildElectProtocolNotifyJsonString(electProtocolVo));
    }

    public String buildUpdateRateJsonString() {
        String formatter = "'{'\"prodName\":\"{0}\",\"newInterestRate\":\"{1}\",\"amount\":{2}'}'";

        return MessageFormat.format(formatter, "dingdandai", new BigDecimal("0.1500").toPlainString(), new BigDecimal("150000").toPlainString());
    }

    public String buildPayNotifyJsonString() {
        String formatter = "'{'\"prodName\":\"{0}\",\"loanNo\":\"{1}\",\"amount\":\"{2}\",\"status\":\"{3}\",\"message\":\"{4}\"'}'";
        return MessageFormat.format(formatter, "dingdandai", "LD32233333", new BigDecimal("150000").toPlainString(), 1, "message");
    }

    public String buildRepayNotifyJsonString(List<RepayRetDataVo> details) {
        StringBuilder queryBuild = new StringBuilder();
        String formatter = "'{'\"productName\":\"{0}\",\"batchNum\":\"{1}\",\"totalAmt\":\"{2}\",\"status\":\"{3}\"";
        queryBuild.append(MessageFormat.format(formatter, "dingdandai", "batchNo", new BigDecimal("150000").toPlainString(), 1));


        queryBuild.append(",\"detail\":[");
        for (RepayRetDataVo repayRetDataVo : details) {
            formatter = "'{'\"loanNo\":\"{0}\",\"repayAmt\":\"{1}\",\"repayPrincipal\":\"{2}\",\"repayInterest\":\"{3}\",\"repayInterestPenalty\":\"{4}\"'}'";queryBuild.append(MessageFormat.format(formatter, repayRetDataVo.getLoanNo(), repayRetDataVo.getRepayAmt(), repayRetDataVo.getRepayPrincipal().toPlainString(),
                    repayRetDataVo.getRepayInterest().toPlainString(), repayRetDataVo.getRepayInterestPenalty().toPlainString())).append(",");
        }
        queryBuild.deleteCharAt(queryBuild.length() - 1);
        queryBuild.append("]}");
        return queryBuild.toString();
    }

    public String buildElectProtocolNotifyJsonString(ElectProtocolVo electProtocolVo) {
        String formatter = "'{'\"cusTid\":\"{0}\",\"docType\":\"{1}\",\"loanNo\":\"{2}\",\"productNo\":\"{3}\"'}'";
        return MessageFormat.format(formatter, electProtocolVo.getCusTid(), electProtocolVo.getDocType(), electProtocolVo.getLoanNo(), electProtocolVo.getProductNo());
    }

    public class RepayRetDataVo {
        private String loanNo;
        private BigDecimal repayAmt;
        private BigDecimal repayPrincipal;
        private BigDecimal repayInterest;
        private BigDecimal repayInterestPenalty;

        public RepayRetDataVo(String loanNo, BigDecimal repayAmt, BigDecimal repayPrincipal, BigDecimal repayInterest, BigDecimal repayInterestPenalty) {
            this.loanNo = loanNo;
            this.repayAmt = repayAmt;
            this.repayPrincipal = repayPrincipal;
            this.repayInterest = repayInterest;
            this.repayInterestPenalty = repayInterestPenalty;
        }

        public String getLoanNo() {
            return loanNo;
        }

        public void setLoanNo(String loanNo) {
            this.loanNo = loanNo;
        }

        public BigDecimal getRepayAmt() {
            return repayAmt;
        }

        public void setRepayAmt(BigDecimal repayAmt) {
            this.repayAmt = repayAmt;
        }

        public BigDecimal getRepayPrincipal() {
            return repayPrincipal;
        }

        public void setRepayPrincipal(BigDecimal repayPrincipal) {
            this.repayPrincipal = repayPrincipal;
        }

        public BigDecimal getRepayInterest() {
            return repayInterest;
        }

        public void setRepayInterest(BigDecimal repayInterest) {
            this.repayInterest = repayInterest;
        }

        public BigDecimal getRepayInterestPenalty() {
            return repayInterestPenalty;
        }

        public void setRepayInterestPenalty(BigDecimal repayInterestPenalty) {
            this.repayInterestPenalty = repayInterestPenalty;
        }
    }

    public class ElectProtocolVo {
        private String cusTid;

        private Integer docType;

        private String loanNo;

        private String productNo;

        public ElectProtocolVo(String cusTid, Integer docType, String loanNo, String productNo) {
            this.cusTid = cusTid;
            this.docType = docType;
            this.loanNo = loanNo;
            this.productNo = productNo;
        }

        public ElectProtocolVo(String cusTid, Integer docType){
            this.cusTid = cusTid;
            this.docType = docType;
        }


        public String getCusTid() {
            return cusTid;
        }

        public void setCusTid(String cusTid) {
            this.cusTid = cusTid;
        }

        public Integer getDocType() {
            return docType;
        }

        public void setDocType(Integer docType) {
            this.docType = docType;
        }

        public String getLoanNo() {
            return loanNo;
        }

        public void setLoanNo(String loanNo) {
            this.loanNo = loanNo;
        }

        public String getProductNo() {
            return productNo;
        }

        public void setProductNo(String productNo) {
            this.productNo = productNo;
        }
    }
}
