package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

enum FindCubeState {
    middle,
    right,
    left
}
@Disabled
@Autonomous(name = "Auto3", group = "Iterative Opmode")
public class Auto3 extends OpMode {
    private Funcs funcs = new Funcs();
    private FindCubeState findCubeState = FindCubeState.right;

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
        telemetry.addData("pass:", funcs.elevator.getCurrentPosition());
        /*funcs.elevatorUp();
        funcs.climb.setPosition(90);*/
        telemetry.addData("find cube", ".");
        //funcs.driveForward(14);
         findCube();
        findCube();
        findCube();
        /*funcs.driveForward(20);
        funcs.turnDeg(180);
        // sleep
        funcs.rArm.setPosition(0);
        funcs.lArm.setPosition(0);
        // sleep
        funcs.rArm.setPosition(100);
        funcs.lArm.setPosition(100);
        // sleep
        funcs.turnDeg(45);
        // sleep
        funcs.driveForward(95);*/


    }

    @Override
    public void loop() {
        /*
        telemetry.addData("Red  ", funcs.colorSensor.red());
        telemetry.addData("Green", funcs.colorSensor.green());
        telemetry.addData("Blue ", funcs.colorSensor.blue());
        telemetry.update();
        */
    }

    @Override
    public void stop() {
    }

    private void findCube() {
        switch (findCubeState) {
            case middle:
                if (funcs.colorSensor()) {
                    break;
                }
                findCubeState = FindCubeState.right;
                break;

            case right:
                funcs.driveForward(-6.5);
                funcs.turnDeg(-45);
                funcs.driveForward(10);
                if (funcs.colorSensor()) {
                    break;
                }
                findCubeState = FindCubeState.left;
                break;

            case left:
                funcs.driveForward(-10);
                funcs.turnDeg(90);
                funcs.driveForward(10);
                if (funcs.colorSensor()) {
                    break;
                }
                break;
        }
            if (funcs.colorSensor()) {
                telemetry.addData("r:", funcs.colorSensor.red());
                telemetry.addData("g:", funcs.colorSensor.green());
                telemetry.addData("b:", funcs.colorSensor.blue());
            }
                else {
                    funcs.rDrive.setPower(0);
                    funcs.lDrive.setPower(0);
                }
        }
    }


