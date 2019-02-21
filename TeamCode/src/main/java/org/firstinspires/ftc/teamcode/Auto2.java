package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Auto2", group="Linear Opmode")
public class Auto2 extends LinearOpMode {

    private Funcs funcs = new Funcs();

    @Override
    public void runOpMode() {

        funcs.TeleInit(hardwareMap);

        waitForStart();
        funcs.driveForward(10);
    }
}
