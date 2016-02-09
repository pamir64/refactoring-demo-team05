package com.scrumtrek.simplestore.reports;

import com.scrumtrek.simplestore.Customer;

public interface Reporter {
    String getReport(Customer customer);
}
