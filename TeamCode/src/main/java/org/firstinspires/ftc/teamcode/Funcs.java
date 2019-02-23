package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class Funcs {

    // Final Variables
    private static final double kp = 1;
    public Dual rDrive;
    public Dual lDrive;
    public DcMotor collect = null;
    public DcMotor foldCollect = null;
    public DcMotor colElevator = null;
    // Servos
    public DcMotor elevator = null;
    public Servo climb = null;
    public Servo rArm = null;
    public Servo lArm = null;
    private HardwareMap HM = null;
    //DC Motors
    private DcMotor rDrive1 = null;
    private DcMotor rDrive2 = null;
    private DcMotor lDrive1 = null;
    private DcMotor lDrive2 = null;
    // Sensors
    private BNO055IMU imu = null;
    public ColorSensor colorSensor = null;

    public void TeleInit(HardwareMap HM) {
        this.HM = HM;
        // Motors Init
        rDrive1 = HM.get(DcMotor.class, "rDrive1");
        rDrive2 = HM.get(DcMotor.class, "rDrive2");
        lDrive1 = HM.get(DcMotor.class, "lDrive1");
        lDrive2 = HM.get(DcMotor.class, "lDrive2");
        //collect = HM.get(DcMotor.class, "collection");
        //foldCollect = HM.get(DcMotor.class, "foldCollection");
        colElevator = HM.get(DcMotor.class, "collectionElevator");
        elevator = HM.get(DcMotor.class, "elevator");
        //  Servos Init
        climb = HM.get(Servo.class, "climb");
        rArm = HM.get(Servo.class, "rArm");
        lArm = HM.get(Servo.class, "lArm");
        // Sensors Init
        imu = HM.get(BNO055IMU.class, "imu");
        colorSensor = HM.get(ColorSensor.class, "colorSensor");

        // Motors Set Directions
        rDrive1.setDirection(DcMotor.Direction.REVERSE);
        rDrive2.setDirection(DcMotor.Direction.REVERSE);
        lDrive1.setDirection(DcMotor.Direction.FORWARD);
        lDrive2.setDirection(DcMotor.Direction.FORWARD);
        // collect.setDirection(DcMotor.Direction.FORWARD);
        // foldCollect.setDirection(DcMotor.Direction.FORWARD);
        colElevator.setDirection(DcMotor.Direction.FORWARD);
        elevator.setDirection(DcMotor.Direction.REVERSE);
        // Servos Set Directions
        climb.setDirection(Servo.Direction.FORWARD);
        rArm.setDirection(Servo.Direction.REVERSE);
        lArm.setDirection(Servo.Direction.FORWARD);
        // Set Modes
        rDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // collect.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // foldCollect.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        colElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Set Positions
        rArm.setPosition(0);
        lArm.setPosition(0);
        climb.setPosition(0);
        // Set Brake
        rDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Dual Config
        rDrive = new Dual(rDrive1, rDrive2);
        lDrive = new Dual(lDrive1, lDrive2);
    }

    class Dual {
        DcMotor motor1;
        DcMotor motor2;

        public Dual(DcMotor motor1, DcMotor motor2) {
            this.motor1 = motor1;
            this.motor2 = motor2;
        }

        public void setPower(double power) {
            motor1.setPower(power);
            motor2.setPower(power);
        }

        public int getCurrentPosition() {
            return motor1.getCurrentPosition();
        }

        public void runToPosition(double target) {
            motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            int encoderTarget = (int) (target / (4 * Math.PI) * 1120);
            motor1.setTargetPosition(encoderTarget);
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Dual.this.setPower(1);
            while (motor1.isBusy()) {
            }
            Dual.this.setPower(0);
            motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void driveForward(double TargetIn) {
        rDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int encoderTarget = (int) (TargetIn / (4 * Math.PI) * 1120);
        rDrive1.setTargetPosition(encoderTarget);
        lDrive1.setTargetPosition(encoderTarget);
        rDrive1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lDrive1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rDrive.setPower(0.7 * encoderTarget / Math.abs(encoderTarget));
        lDrive.setPower(0.7 * encoderTarget / Math.abs(encoderTarget));
        while (rDrive1.isBusy() && lDrive1.isBusy()) {
        }
        rDrive.setPower(0);
        lDrive.setPower(0);

//        lDrive.runToPosition(TargetIn);
//        rDrive.runToPosition(TargetIn);
    }

    public void turnDeg(double deg) {
        rDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lDrive1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int encoderTarget = (int) ((((15.5 * Math.PI) * (deg / 360)) / (4 * Math.PI)) * 1120);
        rDrive1.setTargetPosition(encoderTarget);
        lDrive1.setTargetPosition(-encoderTarget);
        rDrive1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lDrive1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rDrive.setPower(0.7 * encoderTarget / Math.abs(encoderTarget));
        lDrive.setPower(-0.7 * encoderTarget / Math.abs(encoderTarget));
        while (rDrive1.isBusy() && lDrive1.isBusy()) {
            rDrive.getCurrentPosition();
        }
        rDrive.setPower(0);
        lDrive.setPower(0);
        // Positive deg = Right, Negative deg = Left
    }

    public void elevatorUp() {
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevator.setTargetPosition(11700);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevator.setPower(1);
        while (elevator.isBusy()) {
        }
        elevator.setPower(0);
    }

    public boolean colorSensor() {
        int counter = 0;
        for (int i = 0; i < 100; i++) {
            if (colorSensor.green() > 30 && colorSensor.green() < 40 && colorSensor.blue() > 20 && colorSensor.blue() < 40) {
                counter += 1;
            }
        }
        if (counter > 30) {
            return true;
        } else {
            return false;
        }
    }

}