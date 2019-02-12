package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop", group = "Iterative Opmode")
public class Teleop extends OpMode {
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
    }

    @Override
    public void loop() {
        // Drive
        if (gamepad1.right_stick_y > 0.2 || gamepad1.right_stick_y < -0.2 || gamepad1.left_stick_y > 0.2 || gamepad1.left_stick_y < -0.2) {
            funcs.rDrive.setPower(-gamepad1.right_stick_y);
            funcs.lDrive.setPower(-gamepad1.left_stick_y);
            telemetry.addData("powerl: ", funcs.lDrive.motor1.getPower());
            telemetry.addData("powerr: ", funcs.rDrive.motor1.getPower());
            telemetry.update();
        } else {
            funcs.rDrive.setPower(0);
            funcs.lDrive.setPower(0);
        }
        // Collect
        if (gamepad2.right_trigger > 0) {
            funcs.collect.setPower(0.7);
        } else if (gamepad2.left_trigger == 0) {
            funcs.collect.setPower(-0.7);
        } else {
            funcs.collect.setPower(0);
        }
        // Collection Elevator
        if (gamepad2.right_stick_y > 0) {
            funcs.colElevator.setPower(0.7);
        } else if (gamepad2.left_stick_y < 0) {
            funcs.colElevator.setPower(-0.7);
        } else {
            funcs.colElevator.setPower(0);
        }
        // Collection Fold
        if (gamepad2.right_bumper) {
            funcs.foldCollect.setPower(0.7);
        } else if (gamepad2.left_bumper) {
            funcs.foldCollect.setPower(-0.7);
        } else {
            funcs.foldCollect.setPower(0);
        }
        // Arm
        if (gamepad2.y) {
            funcs.rArm.setPosition(180);
            funcs.lArm.setPosition(180);
        }
        if (gamepad2.a) {
            funcs.rArm.setPosition(0);
            funcs.lArm.setPosition(0);
        }
        // Climb
        if (gamepad2.x) {
            funcs.climb.setPosition(90);
        } else if (gamepad2.b) {
            funcs.climb.setPosition(0);
        }
        // Elevator
        if (gamepad2.dpad_up) {
            funcs.elevator.setPower(0.7);
        } else if (gamepad2.dpad_down) {
            funcs.elevator.setPower(-0.7);
        } else {
            funcs.elevator.setPower(0);
        }


    }

    @Override
    public void stop() {
    }

}
