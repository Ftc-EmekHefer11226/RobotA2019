package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.lang.Thread;
import java.lang.InterruptedException;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Teleop", group = "Iterative Opmode")
public class Teleop extends OpMode {
    private Funcs funcs = new Funcs();
    private boolean arm_toggle = false;
    private boolean climb_toggle = false;

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
        //test
        vex();
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
                telemetry.update();
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
                telemetry.update();
            } else {
                funcs.rDrive.setPower(0);
                funcs.lDrive.setPower(0);
            }
        }
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

    public void colExtend() {
        if (gamepad2.y) {
            if (funcs.colElevator.getCurrentPosition() < 1000) {
                funcs.colElevator.setPower(0.7);
            } else if (funcs.colElevator.getCurrentPosition() > 1000) {
                funcs.colElevator.setPower(0);
                funcs.foldCollect.setPower(0.7);
            }
        } else if (gamepad2.a) {
            if (funcs.foldCollect.getCurrentPosition() > 1) {
                funcs.foldCollect.setPower(-0.7);
            } else if (funcs.foldCollect.getCurrentPosition() < 1) {
                funcs.foldCollect.setPower(0);
                funcs.colElevator.setPower(-0.7);
            }
        } else {
            funcs.colElevator.setPower(0);
            funcs.foldCollect.setPower(0);
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
            funcs.rArm.setPosition(0);
            funcs.lArm.setPosition(0);
            telemetry.addData("servo", "up");

        } else if (gamepad1.dpad_down) {
            funcs.rArm.setPosition(100);
            funcs.lArm.setPosition(100);
            telemetry.addData("servo", "down");
        }
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
            funcs.elevator.setPower(0.9);
        } else if (gamepad2.dpad_down) {
            funcs.elevator.setPower(-0.9);
        } else {
            funcs.elevator.setPower(0);
        }
        telemetry.addData("elevator", funcs.elevator.getCurrentPosition());
        telemetry.update();

    }


    public void vex(){
        if (gamepad2.x){
            funcs.test.setPower(0.5);
        }
        else if (gamepad2.b){
            funcs.test.setPower(-0.5);
        }
        else{
            funcs.test.setPower(0);
        }
    }
}
