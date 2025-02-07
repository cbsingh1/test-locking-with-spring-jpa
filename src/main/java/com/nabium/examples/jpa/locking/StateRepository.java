package com.nabium.examples.jpa.locking;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;
import java.util.Optional;

public interface StateRepository extends JpaRepository<State, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<State> findForUpdateById(String id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<State> findForShareById(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "0"))
    Optional<State> findForUpdateNoWaitById(String id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "0"))
    Optional<State> findForShareNoWaitById(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "-2"))
    Optional<State> findForUpdateSkipLockedById(String id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "-2"))
    Optional<State> findForShareSkipLockedById(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "2900"))
    Optional<State> findForUpdateWithTimeoutById(String id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "3100"))
    Optional<State> findForUpdateWithTimeout3100ById(String id);

    int countByCensusRegion(CensusRegion censusRegion);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints
    Optional<State> findFirst1ForShareByCensusRegion(CensusRegion censusRegion);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints(@QueryHint(name = AvailableSettings.JAKARTA_LOCK_TIMEOUT, value = "-2"))
    List<State> findForUpdateSkipLockedByCensusRegion(CensusRegion censusRegion);
}
