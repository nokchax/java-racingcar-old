package racingcar.dto;

import racingcar.history.CarSnapshot;
import racingcar.history.RacingHistory;
import racingcar.history.RoundHistory;
import racingcar.racing.Car;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingResult {
    private static final String SPLITTER = " : ";
    private static final char DASH_MARK = '-';

    private RacingHistory racingHistory;
    private List<Car> winners;

    public RacingResult(RacingHistory racingHistory, List<Car> winners) {
        this.racingHistory = racingHistory;
        this.winners = winners;
    }

    public String showRacingHistory() {
        StringBuilder history = new StringBuilder();

        for(RoundHistory roundHistory : racingHistory.getRoundHistories()) {
            history.append(showRoundHistory(roundHistory))
                    .append('\n');
        }

        return history.toString();
    }

    private String showRoundHistory(RoundHistory roundHistory) {
        StringBuilder history = new StringBuilder();

        for(CarSnapshot carSnapshot : roundHistory.getCarSnapshots()) {
            history.append(showCarSnapshot(carSnapshot))
                    .append('\n');
        }

        return history.toString();
    }

    private String showCarSnapshot(CarSnapshot carSnapshot) {
        StringBuilder carMovement = new StringBuilder(carSnapshot.getCarName() + SPLITTER);

        IntStream.rangeClosed(0, carSnapshot.getPosition())
                .forEach(x -> carMovement.append(DASH_MARK));

        return carMovement.toString();
    }

    public List<String> getWinnerNames() {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public RacingHistory getRacingHistory() {
        return racingHistory;
    }

    public List<Car> getWinners() {
        return winners;
    }
}
