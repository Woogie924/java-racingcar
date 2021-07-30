package race;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CarsTest {

    private static final int DEFAULT_TRY_COUNT = 5;
    private static final int DEFAULT_CAR_COUNT = 3;
    private static final String[] DEFAULT_CAR_NAMES = {"pobi", "crong", "honux"};
    private static final int MINIMUM_DISTANCE = 0;

    @DisplayName("생성된 Car객체에 각 시도 횟수(라운드)만큼 이동(전진,후진)이 있는지 확인")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void moveAll(int index) {
        Cars cars = getCars();
        cars.moveAll();

        Car car = cars.getCars().get(index);
        int distanceCount = car.getDistance().size() - 1;
        assertThat(distanceCount).isEqualTo(DEFAULT_TRY_COUNT);
    }

    @DisplayName("자동차 게임 우승자 있는지 확인. 최소 1명이 나와야한다.")
    @Test
    void getWinner() {
        Cars cars = getCars();
        cars.moveAll();

        assertThat(cars.getWinner(MINIMUM_DISTANCE).split(",").length).isGreaterThanOrEqualTo(1);
    }

    private Cars getCars() {
        return Cars.of(Condition.of(DEFAULT_CAR_COUNT, DEFAULT_TRY_COUNT, DEFAULT_CAR_NAMES));
    }

}