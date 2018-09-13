package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ThreadTest extends Thread {

    private DcMotor motor2;

    private Telemetry telemetry;
    private ElapsedTime timer = new ElapsedTime();

    ThreadTest(Telemetry telemetry, HardwareMap hardwareMap) {
        this.telemetry = telemetry;
        motor2 = hardwareMap.dcMotor.get("motor2");
        timer.reset();
    }

    @Override
    public void run() {
        while (timer.seconds() < 5) {
            telemetry.addData("thread2", timer.seconds()/5);
            motor2.setPower(timer.seconds()/5);
            telemetry.update();
        }
    }
}