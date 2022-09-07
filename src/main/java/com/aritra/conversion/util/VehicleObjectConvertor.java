package com.aritra.conversion.util;

import com.aritra.conversion.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Component
public class VehicleObjectConvertor {

    public Object convertObjectsToVehicle(Vehicle vehicle) {
        RskCntxt rskCntxtObject = new RskCntxt();
        Object rskCntxtObj = vehicle.getRskCntxt();
        List<RiskInputData> rskInptDataListObj = new ArrayList<>();
        List<RiskAssessment> rskAssmntListObj = new ArrayList<>();
        if (rskCntxtObj instanceof ArrayList) {
            ((ArrayList<?>) rskCntxtObj).stream()
                    .forEach(rskCntxt -> {
                        if (rskCntxt instanceof LinkedHashMap) {
                            processRiskContextMap(rskInptDataListObj, rskAssmntListObj, (LinkedHashMap) rskCntxt);
                        }
                    });
            rskCntxtObject.setRiskInputData(rskInptDataListObj.size() > 0 ? rskInptDataListObj : null);
            rskCntxtObject.setRskAssmnt(rskAssmntListObj.size() > 0 ? rskAssmntListObj : null);
            List<RskCntxt> rskCntxtList = new ArrayList<>();
            rskCntxtList.add(rskCntxtObject);
            return rskCntxtList;
        } else if(rskCntxtObj instanceof LinkedHashMap) {
            processRiskContextMap(rskInptDataListObj, rskAssmntListObj, (LinkedHashMap) rskCntxtObj);
            rskCntxtObject.setRiskInputData(rskInptDataListObj.size() > 0 ? rskInptDataListObj : null);
            rskCntxtObject.setRskAssmnt(rskAssmntListObj.size() > 0 ? rskAssmntListObj : null);
            return rskCntxtObject;
        }
        return null;
    }

    private void processRiskContextMap(List<RiskInputData> rskInptDataListObj, List<RiskAssessment> rskAssmntListObj, LinkedHashMap rskCntxt) {
        Set<String> setOfRskCntxt = rskCntxt.keySet();
        setOfRskCntxt.stream()
                .forEach(key -> {
                    if (key.equalsIgnoreCase("rskInptData")) {
                        processObjectForRiskInputData(rskInptDataListObj, rskCntxt, key);
                    } else if (key.equalsIgnoreCase("rskAssmnt")) {
                        Object rskAssmntList = rskCntxt.get("rskAssmnt");
                        processObjectForRiskAssement(rskAssmntListObj, rskAssmntList);
                    }
                });
    }

    private void processObjectForRiskAssement(List<RiskAssessment> rskAssmntListObj, Object rskAssmntList) {
        if (rskAssmntList instanceof ArrayList) {
            ((ArrayList<?>) rskAssmntList).stream()
                    .forEach(rskAssemnt -> {
                        if (rskAssemnt instanceof LinkedHashMap) {
                            RiskAssessment rskAssmntObj = new RiskAssessment();
                            Set<String> setOfRskAssmnt = ((LinkedHashMap) rskAssemnt).keySet();
                            setOfRskAssmnt.stream()
                                    .forEach(k -> {
                                        if (k.equalsIgnoreCase("rskAssmntTp")) {
                                            String rskAssmntTp = (String) ((LinkedHashMap) rskAssemnt).get(k);
                                            rskAssmntObj.setRskAssmntTp(rskAssmntTp);
                                        } else if (k.equalsIgnoreCase("rslt")) {
                                            String rslt = (String) ((LinkedHashMap) rskAssemnt).get(k);
                                            rskAssmntObj.setRslt(rslt);
                                        } else if (k.equalsIgnoreCase("rskAssmntNtty")) {
                                            RiskAssessmentEntity rskEntityObj = getRiskAssessmentEntity((LinkedHashMap) rskAssemnt, k);
                                            rskAssmntObj.setRskAssmntNtty(rskEntityObj);
                                        }
                                    });
                            rskAssmntListObj.add(rskAssmntObj);
                        }
                    });
        }
    }

    private RiskAssessmentEntity getRiskAssessmentEntity(LinkedHashMap rskAssemnt, String k) {
        LinkedHashMap entity = (LinkedHashMap) rskAssemnt.get(k);
        Set<String> keyOfEntity = entity.keySet();
        RiskAssessmentEntity rskEntityObj = new RiskAssessmentEntity();
        keyOfEntity.stream()
                .forEach(enty -> {
                    if(enty.equalsIgnoreCase("shrtNm"))  {
                        String shrtNm = (String) ((LinkedHashMap) entity).get(enty);
                        rskEntityObj.setShrtNm(shrtNm);
                    } else if(enty.equalsIgnoreCase("tp")) {
                        //todo - need check enums PartyCodeTp
                        String tp = (String) ((LinkedHashMap) entity).get(enty);
                        rskEntityObj.setTp(PartyTypeCode.valueOf(tp));
                    }
                });
        return rskEntityObj;
    }

    private void processObjectForRiskInputData(List<RiskInputData> rskInptDataListObj, LinkedHashMap rskCntxt, String key) {
        if ((rskCntxt.get(key)) instanceof ArrayList) {
            ArrayList<?> rskInptDataList = (ArrayList<?>) rskCntxt.get(key);
            ((ArrayList<?>) rskInptDataList).stream()
                    .forEach(rskInptData -> {
                        if (rskInptData instanceof LinkedHashMap) {
                            RiskInputData riskInputData = new RiskInputData();
                            Set<String> setOfRskInptData = ((LinkedHashMap) rskInptData).keySet();
                            setOfRskInptData.stream()
                                    .forEach(k -> {
                                        if (k.equalsIgnoreCase("tp")) {
                                            String tp = (String) ((LinkedHashMap) rskInptData).get(k);
                                            riskInputData.setTp(tp);
                                        } else if (k.equalsIgnoreCase("val")) {
                                            String val = (String) ((LinkedHashMap) rskInptData).get(k);
                                            riskInputData.setVal(val);
                                        } else if(k.equalsIgnoreCase("entity")) {
                                            Entity entityObj = getEntity((LinkedHashMap) rskInptData, k);
                                            riskInputData.setEntity(entityObj);
                                        }
                                    });
                            rskInptDataListObj.add(riskInputData);
                        }
                    });
        }
    }

    private Entity getEntity(LinkedHashMap rskInptData, String k) {
        LinkedHashMap entity = (LinkedHashMap) rskInptData.get(k);
        Set<String> keyOfEntity = entity.keySet();
        Entity entityObj = new Entity();
        keyOfEntity.stream()
                .forEach(enty -> {
                    if(enty.equalsIgnoreCase("tp"))  {
                        String tp = (String) ((LinkedHashMap) entity).get(enty);
                        entityObj.setTp(tp);
                    } else if(enty.equalsIgnoreCase("otherTp")) {
                        String otherTp = (String) ((LinkedHashMap) entity).get(enty);
                        entityObj.setOtherTp(otherTp);
                    }
                });
        return entityObj;
    }
}