package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="AutoDepot", group="Linear Opmode")
public class AutoDepot extends LinearOpMode {

    private Funcs funcs = new Funcs();

    @Override
    public void runOpMode() {

        funcs.Init(hardwareMap);

        waitForStart();
        funcs.elevatorUp();
        funcs.climb.setPosition(90);
        funcs.driveForward(50);
        funcs.collectDown();
        funcs.driveForward(5);
        funcs.turnDeg(-120);
        funcs.driveForward(68);
    }
}
