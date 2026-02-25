package org.example.domains;

import lombok.Builder;
import lombok.Getter;

@Builder
public class EnergySystem {
    @Getter
    private double batteryLevel;
    @Getter
    private double lowBatteryThreshold;
    @Getter
    private double maxBattery;
    @Getter
    private double minBattery;

    public boolean consume(double amount) {
        if (amount <= 0 || batteryLevel <= minBattery) {
            return false;
        }

        batteryLevel = Math.max(minBattery, batteryLevel - amount);
        return true;
    }

    public boolean hasSufficientPower() {
        return batteryLevel > lowBatteryThreshold;
    }

    @Override
    public String toString() {
        return "EnergySystem{" +
                "batteryLevel=" + batteryLevel +
                "}";
    }
}