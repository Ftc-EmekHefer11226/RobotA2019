package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

enum FindCubeState {
    middle,
    right,
    left
}

@Autonomous(name = "Auto3", group = "Iterative Opmode")
public class Auto3 extends OpMode {
    private Funcs funcs = new Funcs();
    private FindCubeState findCubeState = FindCubeState.right;

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
        findCube();
        findCube();
        findCube();
    }

    @Override
    public void loop() {
        telemetry.addData("Red  ", funcs.colorSensor.red());
        telemetry.addData("Green", funcs.colorSensor.green());
        telemetry.addData("Blue ", funcs.colorSensor.blue());
        telemetry.update();
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
    }

}
