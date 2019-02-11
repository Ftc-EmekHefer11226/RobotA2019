package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
@Disabled
public class TeleOp extends OpMode
{
    private Funcs funcs = new Funcs();
    @Override
    public void init()
    {
        telemetry.addData("Status", "Initializing");
        funcs.TeleInit(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop()
    {}

    @Override
    public void start()
    {}

    @Override
    public void loop() {
        // Drive
        if (gamepad1.right_stick_y > 0.1 || gamepad1.right_stick_y < -0.1 || gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_y < -0.1)
        funcs.rDrive.setPower(-gamepad1.right_stick_y);
        funcs.lDrive.setPower(-gamepad1.left_stick_y);
        // Arm
        if (gamepad2.a)
    }

    @Override
    public void stop() {
    }

}
