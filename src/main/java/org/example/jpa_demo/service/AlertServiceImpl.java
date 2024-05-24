package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.Alert;
import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.PollingStationTask;
import org.example.jpa_demo.repository.AlertRepository;
import org.example.jpa_demo.repository.PollingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService{

    private final AlertRepository alertRepository;

    @Autowired
    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }


    @Override
    public List<Alert> getAlertsByPSID(String PSID) {
        return alertRepository.getAlertsByPSID(PSID);
    }

    @Override
    public String updateAlert(String alertid, String ps, String answer) {
        return alertRepository.updateAlert(alertid,ps,answer);
    }


}
