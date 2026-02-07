package org.example;

public abstract class Satellite {
    protected String name;
    protected SatelliteState state;
    protected EnergySystem energy;

    public Satellite(String name, double batteryLevel) {
        this.name = name;
        this.energy = new EnergySystem(batteryLevel);
        this.state = new SatelliteState();
        System.out.println("Создан спутник: " + name + " (заряд: " + (int)(batteryLevel * 100) + "%)");
    }

    public String getName() { return name; }
    public SatelliteState getState() { return state; }
    public EnergySystem getEnergy() { return energy; }

    public boolean activate() {
        if (state.activate(energy.hasSufficientPower())) {
            System.out.println("✅ " + name + "Ж Активация успешна");
            return true;
        }

        System.out.println("❌ " + name + ": Ошибка активации (заряд: " + (int)(energy.getBatteryLevel() * 100) + "%)");
        return false;
    }

    public void deactivate() {
        if (state.isActive()) {
            state.deactivate();
            System.out.println("❌ " + name + ": Деактивирован");
        }
    }

    public abstract void performMission();
}
