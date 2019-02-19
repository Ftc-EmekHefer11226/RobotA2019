package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Auto", group = "Iterative Opmode")
public class Auto2019 extends OpMode {
    private Funcs funcs = new Funcs();

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");
        funcs.TeleInit(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        funcs.elevator.setTargetPosition(-20);
        funcs.climb.setPosition(0);
        funcs.rDrive.runToPosition(20);
        funcs.lDrive.runToPosition(20);
        funcs.rDrive.runToPosition(15);
        funcs.lDrive.runToPosition(-15);
        funcs.rDrive.runToPosition(-40);
        funcs.lDrive.runToPosition(-40);
        funcs.rArm.setPosition(180);
        funcs.lArm.setPosition(180);
        funcs.rDrive.runToPosition(5);
        funcs.lDrive.runToPosition(-5);
        funcs.rArm.setPosition(0);
        funcs.lArm.setPosition(0);
        funcs.rDrive.runToPosition(50);
        funcs.lDrive.runToPosition(50);
        // Values to Be Changed
    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
    }

}
