package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Auto3", group = "Iterative Opmode")
public class Auto3 extends OpMode {
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
        funcs.elevatorUp();
        funcs.climb.setPosition(0);
        funcs.driveForward(25);

    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
    }

}
