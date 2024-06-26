package org.example.jpa_demo.service;

import org.example.jpa_demo.entity.PollingStation;
import org.example.jpa_demo.entity.PollingStationTask;
import org.example.jpa_demo.entity.PollingStationSchedule;
import org.example.jpa_demo.repository.PollingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollingStationServiceImpl implements PollingStationService {

    private final PollingStationRepository pollingStationRepository;

    @Autowired
    public PollingStationServiceImpl(PollingStationRepository pollingStationRepository) {
        this.pollingStationRepository = pollingStationRepository;
    }


    @Override
    public List<PollingStation> getPollingStationsByUserId(String userID) {
        return pollingStationRepository.getPollingStationsByUserID(userID);
    }

    @Override
    public  List<PollingStationTask> getTaskByPollingStationID(String pollingStationID) {
        return pollingStationRepository.getTaskByPollingStationID(pollingStationID);
    }

    @Override
    public List<PollingStationSchedule> getScheduleByPollingStationID(String pollingStationID) {
        return pollingStationRepository.getScheduleByPollingStationID(pollingStationID);
    }



}
