package org.example.jpa_demo.repository;

import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.PollingStationTask;
import org.example.jpa_demo.entity.PollingStationSchedule;

import java.util.List;

public interface PollingStationRepository {
    List<PollingStation> getPollingStationsByUserID(String userId);
    List<PollingStationTask> getTaskByPollingStationID(String pollingStationId);
    List<PollingStationSchedule> getScheduleByPollingStationID(String pollingStationId);

}
