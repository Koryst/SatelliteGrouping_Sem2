package org.example.domains;

public class CommunicationSatellite extends Satellite {
    private final double bandwidth;

    public CommunicationSatellite(String name, double batteryLevel, double bandwidth) {
        super(name, batteryLevel);
        this.bandwidth = bandwidth;
    }

    public double getBandwidth() { return bandwidth; }

    @Override
    public void performMission() {
        if (state.isActive()) {
            System.out.println(name + ": Передача данных со скоросью " + bandwidth + " МБит/с");
            sendData(bandwidth);
            energy.consume(0.05);
        } else {
            System.out.println("❌ " + name + ": Не может выполнить миссию - не активен");
        }
    }

    private void sendData(double dataAmount) {
        System.out.println(name + ": Отправил " + dataAmount + " Мбит данных!");
    }

    @Override
    public String toString() {
        return "CommunicationSatellite{" +
                "bandwidth=" + bandwidth +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", energy=" + energy +
                "}";
    }
}
