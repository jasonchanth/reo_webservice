package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.Alert;
import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.PollingStationTask;

import java.util.List;

public interface AlertRepository {
    List<Alert> getAlertsByPSID(String PSID);

    String updateAlert(String alertid, String ps, String answer);
}
