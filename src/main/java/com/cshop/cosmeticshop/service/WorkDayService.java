package com.cshop.cosmeticshop.service;

import com.cshop.cosmeticshop.domain.entity.TreatmentPeriod;
import com.cshop.cosmeticshop.domain.entity.WorkDay;

public interface WorkDayService {

    boolean addTreatmentPeriod(TreatmentPeriod period, WorkDay workDay);

}
