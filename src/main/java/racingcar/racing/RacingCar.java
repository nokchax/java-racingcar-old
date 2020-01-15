package racingcar.racing;

import racingcar.dto.RacingCarInput;
import racingcar.dto.RacingCarResult;

public class RacingCar {
    private Cars cars;
    private StringBuilder raceHistory;
    private int round;
    private int currentRound;

    public RacingCar(RacingCarInput racingCarInput) {
        this(racingCarInput.getNumberOfCar(), racingCarInput.getNumberOfRound());
    }

    public RacingCar(int numberOfCar, int round) {
        this(new Cars(numberOfCar), round);
    }

    public RacingCar(Cars cars, int round) {
        if(round < 0) {
            throw new IllegalArgumentException("Round must be greater than 0");
        }

        this.cars = cars;
        this.round = round;
        this.currentRound = 0;
        this.raceHistory = new StringBuilder();
        registerHistory(cars.showCurrentState());
    }

    public void race() {
        for( ; currentRound < round ; ++currentRound) {
            cars.moveCars();

            registerHistory(cars.showCurrentState());
        }
    }

    private void registerHistory(String state) {
        raceHistory.append(state)
                .append('\n');
    }

    public String getRaceHistory() {
        return raceHistory.toString();
    }

    public String showCurrentState() {
        return cars.showCurrentState();
    }

    public RacingCarResult result() {
        return new RacingCarResult(getRaceHistory());
    }
}
