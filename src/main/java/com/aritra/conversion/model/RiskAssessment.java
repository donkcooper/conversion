package com.aritra.conversion.model;

import lombok.Data;

import java.util.List;

@Data
public class RiskAssessment {
    private String rslt;
    private String rskAssmntTp;
    private RiskAssessmentEntity rskAssmntNtty;
    private List<AddtionalRiskData> addtlRskData;
}
