package org.firstinspires.ftc.teamcode.test;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ThreadTest extends Thread {

    private Telemetry telemetry;

    ThreadTest(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public void run() {
        while (true) {
            telemetry.addData("thread", "thread");
            telemetry.update();
        }
    }
}
