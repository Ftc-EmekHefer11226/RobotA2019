package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Funcs {

    // Final Variables
    private static final double kp = 1;
    /* public Dual rDrive;
     public Dual lDrive;
     public DcMotor collect = null;
     public DcMotor foldCollect = null;
        public DcMotor colElevator = null;
        public DcMotor elevator = null;*/
    public Servo climb = null;
      /* public Servo rArm = null;
       public Servo lArm = null;*/
    private HardwareMap HM = null;
    // Servos
    //DC Motors
    /*private DcMotor rDrive1 = null;
    private DcMotor rDrive2 = null;
    private DcMotor lDrive1 = null;
    private DcMotor lDrive2 = null;*/

    public void TeleInit(HardwareMap HM) {
        this.HM = HM;/*
        // Motors Init
        rDrive1 = HM.get(DcMotor.class, "rDrive1");
        rDrive2 = HM.get(DcMotor.class, "rDrive2");
        lDrive1 = HM.get(DcMotor.class, "lDrive2");
        lDrive2 = HM.get(DcMotor.class, "lDrive2");
        //    collect = HM.get(DcMotor.class, "collection");
        foldCollect = HM.get(DcMotor.class, "foldCollection");
        colElevator = HM.get(DcMotor.class, "collectionElevator");
        elevator = HM.get(DcMotor.class, "elevator");*/
    //  Servos Init
    climb =HM.get(Servo .class,"climb");
       /* rArm = HM.get(Servo.class, "rArm");
        lArm = HM.get(Servo.class, "lArm");
       //  Motors Set Directions
        rDrive1.setDirection(DcMotorSimple.Direction.REVERSE);
        rDrive2.setDirection(DcMotorSimple.Direction.REVERSE);
        lDrive1.setDirection(DcMotorSimple.Direction.FORWARD);
        lDrive2.setDirection(DcMotorSimple.Direction.FORWARD);
         collect.setDirection(DcMotorSimple.Direction.FORWARD);
        foldCollect.setDirection(DcMotorSimple.Direction.FORWARD);
       colElevator.setDirection(DcMotorSimple.Direction.FORWARD);
        elevator.setDirection(DcMotorSimple.Direction.REVERSE);
       //  Servos Set Directions*/
        climb.setDirection(Servo.Direction.FORWARD);
       /* rArm.setDirection(Servo.Direction.REVERSE);
        lArm.setDirection(Servo.Direction.FORWARD);
        // Set Modes
        rDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collect.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        foldCollect.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        colElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Set Positions
        rArm.setPosition(0);
        lArm.setPosition(0);*/
    climb.setPosition(0);
    // Dual Config
       /* rDrive = new Dual(rDrive1, rDrive2);
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
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            int encoderTarget = (int) (target / (4 * Math.PI) * 1120);
            motor1.setTargetPosition(encoderTarget);
            motor2.setTargetPosition(encoderTarget);
            Dual.this.setPower(1);
            while (motor1.isBusy() || motor2.isBusy()) {
                int power = (int) (((target - Dual.this.getCurrentPosition()) / target) * kp);
                Dual.this.setPower(power);
            }
            Dual.this.setPower(0);
            motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }*/
    }
}