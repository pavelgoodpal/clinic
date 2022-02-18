package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.entity.TreatmentPeriod;
import com.cshop.cosmeticshop.domain.entity.WorkDay;
import com.cshop.cosmeticshop.repository.WorkDayRepository;
import com.cshop.cosmeticshop.service.WorkDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service to manage WorkDay Entity.
 *
 * @author PavelPa1
 */
@Service
@RequiredArgsConstructor
public class WorkDayServiceImpl implements WorkDayService {

    private final WorkDayRepository workDayRepository;

    /**
     * Add treatment period to work day if it is possible.
     *
     * @param period  treatment period to add
     * @param workDay work day to add treatment period
     * @return if treatment is added returns true, else return false
     */
    @Override
    @Transactional
    public boolean addTreatmentPeriodToWorkDay(TreatmentPeriod period, WorkDay workDay) {
        if (canAddTreatmentPeriodToWorkDay(period, workDay)) {
            workDay.addTreatmentPeriod(period);
            workDayRepository.save(workDay);
            return true;
        }
        return false;
    }

    /**
     * Check can treatment period be added to work day or not.
     *
     * @param period  treatment period to add
     * @param workDay work day to add treatment period
     * @return true if treatment period can be added, else return false
     */
    private boolean canAddTreatmentPeriodToWorkDay(TreatmentPeriod period, WorkDay workDay) {
        List<TreatmentPeriod> treatmentPeriods = workDay.getTreatmentPeriods();
        if (workDay.isWorkDay()) {
            if (!isTreatmentPeriodLayInWorkTimePeriod(period, workDay)) {
                return false;
            } else return !isPeriodOverridesOtherPeriods(period, treatmentPeriods) && isPeriodGreaterNow(period);
        }
        return false;
    }

    /**
     * Check is treatment period greater than now time
     *
     * @param period treatment period
     * @return true if treatment period greater than now, else return false
     */
    private boolean isPeriodGreaterNow(TreatmentPeriod period) {
        LocalDateTime now = LocalDateTime.now();
        return period.getStartAt().isAfter(now) && period.getFinishAt().isAfter(now);
    }

    /**
     * Check is treatment period lay in work day start and finish period.
     *
     * @param period  treatment period to check
     * @param workDay work day
     * @return true if treatment period lay in work day start and finish period, else return false
     */
    private boolean isTreatmentPeriodLayInWorkTimePeriod(TreatmentPeriod period, WorkDay workDay) {
        var dayDate = workDay.getDate();
        var workDayStartAt = workDay.getWorkStartAt();
        var workDayFinishAt = workDay.getWorkFinishAt();
        LocalDateTime workStartAtDateTime = LocalDateTime.of(dayDate, workDayStartAt);
        LocalDateTime workFinishAtDateTime = LocalDateTime.of(dayDate, workDayFinishAt);
        return !(period.getStartAt().isBefore(workStartAtDateTime) || period.getFinishAt().isAfter(workFinishAtDateTime));
    }

    /**
     * Check is treatment period override other treatment periods in its list.
     *
     * @param period           treatment period
     * @param treatmentPeriods list of periods
     * @return return true if period overrides other periods in list of periods, else return false
     */
    private boolean isPeriodOverridesOtherPeriods(TreatmentPeriod period, List<TreatmentPeriod> treatmentPeriods) {
        return treatmentPeriods.stream()
                .anyMatch(otherPeriod -> isPeriodsOverrides(period, otherPeriod));
    }

    /**
     * Check is two treatments periods overrides.
     *
     * @param period      first treatment period
     * @param otherPeriod second treatment period
     * @return if treatment periods overrides returns true, else returns false
     */
    private boolean isPeriodsOverrides(TreatmentPeriod period, TreatmentPeriod otherPeriod) {
        LocalDateTime periodStartAt = period.getStartAt();
        LocalDateTime periodFinishAt = period.getFinishAt();
        LocalDateTime otherPeriodStartAt = otherPeriod.getStartAt();
        LocalDateTime otherPeriodFinishAt = otherPeriod.getFinishAt();
        return otherPeriodStartAt.isBefore(periodFinishAt) || otherPeriodFinishAt.isBefore(periodStartAt);
    }
}
