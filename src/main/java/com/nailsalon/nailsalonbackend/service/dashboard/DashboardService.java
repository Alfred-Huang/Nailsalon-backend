package com.nailsalon.nailsalonbackend.service.dashboard;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DashboardService {

    List<Map<String, Object>> getDailyHourSummary(String date) throws IOException;
    List<Map<String, String>> getEmployeeSummary(String date) throws IOException;
    List<Map<String, Object>> getAppointment(String date) throws IOException;
    List<Map<String, Object>> getProductReminder() throws IOException;
}
