package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop", group = "Iterative Opmode")
public class Teleop extends OpMode {
    private Funcs funcs = new Funcs();

    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");
        funcs.Init(hardwareMap);
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
        drive();
        // Collect
        collect();
        // Collection Elevator
        colElevator();
        // Collection Fold
        colFold();
        // Arm
        arm();
        // Climb
        climb();
        // Elevator
        elevator();
    }

    @Override
    public void stop() {
    }

    private boolean sensitiveVal = false;

    private boolean sensitive() {
        if (gamepad1.x) {
            sensitiveVal = !sensitiveVal;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        return sensitiveVal;
    }

    public void drive() {
        if (!sensitive()) {
            telemetry.addData("unsensitive: ", sensitive());
            if (gamepad1.right_stick_y > 0.2 || gamepad1.right_stick_y < -0.2 || gamepad1.left_stick_y > 0.2 || gamepad1.left_stick_y < -0.2) {
                funcs.rDrive.setPower(-gamepad1.right_stick_y);
                funcs.lDrive.setPower(-gamepad1.left_stick_y);
                telemetry.addData("LeftPower: ", funcs.lDrive.motor1.getPower());
                telemetry.addData("RightPower: ", funcs.rDrive.motor1.getPower());
            } else {
                funcs.rDrive.setPower(0);
                funcs.lDrive.setPower(0);
            }

        } else {
            telemetry.addData("sensitive: ", sensitive());
            if (gamepad1.right_stick_y > 0.2 || gamepad1.right_stick_y < -0.2 || gamepad1.left_stick_y > 0.2 || gamepad1.left_stick_y < -0.2) {
                funcs.rDrive.setPower(-gamepad1.right_stick_y * 0.4);
                funcs.lDrive.setPower(-gamepad1.left_stick_y * 0.4);
                telemetry.addData("LeftPower: ", funcs.lDrive.motor1.getPower());
                telemetry.addData("RightPower: ", funcs.rDrive.motor1.getPower());
            } else {
                funcs.rDrive.setPower(0);
                funcs.lDrive.setPower(0);
            }
        }
        telemetry.update();
    }

    public void collect() {
        if (gamepad2.right_trigger > 0.2) {
            funcs.collect.setPower(gamepad2.right_trigger);
        } else if (gamepad2.left_trigger > 0.2) {
            funcs.collect.setPower(-gamepad2.left_trigger);
        } else {
            funcs.collect.setPower(0);
        }
    }


    public void colElevator() {
        if (gamepad2.y) {
            funcs.colElevator.setPower(-0.7);
        } else if (gamepad2.a) {
            funcs.colElevator.setPower(0.7);
        } else {
            funcs.colElevator.setPower(0);
        }
    }

    public void colFold() {
        if (gamepad2.right_bumper) {
            funcs.foldCollect.setPower(1);
        } else if (gamepad2.left_bumper) {
            funcs.foldCollect.setPower(-1);
        } else {
            funcs.foldCollect.setPower(0);
        }
    }

    public void arm() {
        if (gamepad1.dpad_up) {
            funcs.rArm.setPosition(0.8);
            funcs.lArm.setPosition(0.8);
            telemetry.addData("servo", "up");

        } else if (gamepad1.dpad_down) {
            funcs.rArm.setPosition(0.2);
            funcs.lArm.setPosition(0.2);
            telemetry.addData("servo", "down");
        } else {
            funcs.rArm.setPosition(0.5);
            funcs.lArm.setPosition(0.5);
            telemetry.addData("servo", "stop");
        }
        telemetry.update();
    }

    public void climb() {
        if (gamepad1.left_bumper) {
            funcs.climb.setPosition(0);
        } else if (gamepad1.right_bumper) {

            funcs.climb.setPosition(90);
        }

    }

    public void elevator() {
        if (gamepad2.dpad_up) {
            funcs.elevator.setPower(-1);
        } else if (gamepad2.dpad_down) {
            funcs.elevator.setPower(1);
        } else {
            funcs.elevator.setPower(0);
        }
    }

}
