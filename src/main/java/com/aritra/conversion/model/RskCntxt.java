package com.aritra.conversion.model;

import lombok.Data;

import java.util.List;

@Data
public class RskCntxt {
    private List<RiskInputData> riskInputData;
    private List<RiskAssessment> rskAssmnt;
}
