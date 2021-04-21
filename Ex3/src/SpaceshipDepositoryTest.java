import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class is a Test Suite, running both the tests for Locker.java and LongTermTest.java.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LockerTest.class,
        LongTermTest.class
})

public class SpaceshipDepositoryTest {}
