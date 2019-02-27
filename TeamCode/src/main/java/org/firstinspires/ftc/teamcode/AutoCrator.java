package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoCrator", group="Linear Opmode")
public class AutoCrator extends LinearOpMode {

    private Funcs funcs = new Funcs();

    @Override
    public void runOpMode() {

        funcs.Init(hardwareMap);

        waitForStart();
        funcs.elevatorUp();
        funcs.climb.setPosition(90);
        funcs.driveForward(50);
    }
}
