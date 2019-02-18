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
        funcs.rDrive.runToPosition(40);
        funcs.lDrive.runToPosition(40);
    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
    }

}
