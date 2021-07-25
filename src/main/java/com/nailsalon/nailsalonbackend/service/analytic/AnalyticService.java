package com.nailsalon.nailsalonbackend.service.analytic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AnalyticService {

    List<Map<String, String>> getMonthlySummary(String begin, String end) throws IOException;
    List<Map<String, String>> getDailySummary(String begin) throws IOException;
    List<Map<String, String>> getYearlySummary(String begin, String end) throws IOException;
    List<Map<String, String>> getProductSummary();
    List<Map<String, String>> getServiceSummary(String curMonth, String nextMonth) throws IOException;
    List<Map<String, String>> getEmployeeSummary(String curMonth, String nextMonth) throws IOException;
}
