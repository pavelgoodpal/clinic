package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.TreatmentPeriod;
import com.cshop.cosmeticshop.domain.entity.WorkDay;

public interface WorkDayService {

    boolean addTreatmentPeriodToWorkDay(TreatmentPeriod period, WorkDay workDay);

}
