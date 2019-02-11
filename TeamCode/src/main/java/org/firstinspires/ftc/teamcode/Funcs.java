package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Funcs {

    public Dual rDrive;
    public Dual lDrive;
    private HardwareMap HM = null;
    // DC Motors
    private DcMotor rDrive1 = null;
    private DcMotor rDrive2 = null;
    private DcMotor lDrive1 = null;
    private DcMotor lDrive2 = null;
    private DcMotor collection = null;
    private DcMotor foldCollection = null;
    private DcMotor collectionElevator = null;
    private DcMotor elevator = null;
    // Servos
    public Servo climb = null;
    public Servo rArm = null;
    public Servo lArm = null;
    // Final Variables
    private static final double kp = 1;

    public void TeleInit(HardwareMap HM2)
    {
        HM = HM2;
        // Motors Init
        rDrive1 = HM.get(DcMotor.class, "rDrive1");
        rDrive2 = HM.get(DcMotor.class, "rDrive2");
        lDrive1 = HM.get(DcMotor.class, "lDrive2");
        lDrive2 = HM.get(DcMotor.class, "lDrive2");
        collection = HM.get(DcMotor.class, "collection");
        foldCollection = HM.get(DcMotor.class, "foldCollection");
        collectionElevator = HM.get(DcMotor.class, "collectionElevator");
        elevator = HM.get(DcMotor.class, "elevator");
        // Servos Init
        climb = HM.get(Servo.class, "climb");
        rArm = HM.get(Servo.class, "rArm");
        lArm = HM.get(Servo.class, "lArm");
        // Set Directions
        rDrive1.setDirection(DcMotorSimple.Direction.REVERSE);
        rDrive2.setDirection(DcMotorSimple.Direction.REVERSE);
        lDrive1.setDirection(DcMotorSimple.Direction.FORWARD);
        lDrive2.setDirection(DcMotorSimple.Direction.FORWARD);
        /* insert the rest */
        elevator.setDirection(DcMotorSimple.Direction.FORWARD);
        climb.setDirection(Servo.Direction.FORWARD);
        rArm.setDirection(Servo.Direction.REVERSE);
        lArm.setDirection(Servo.Direction.FORWARD);
        // Set Modes
        rDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lDrive1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lDrive2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        /* insert the rest */
        // Dual Config
        rDrive = new Dual (rDrive1, rDrive2);
        lDrive = new Dual (lDrive1, lDrive2);
    }

    class Dual
    {
        DcMotor motor1;
        DcMotor motor2;
        public Dual (DcMotor motor1, DcMotor motor2)
        {
            this.motor1 = motor1;
            this.motor2 = motor2;
        }

        public void setPower (double power)
        {
            motor1.setPower(power);
            motor2.setPower(power);
        }

        public int getCurrentPosition ()
        {
            return motor1.getCurrentPosition();
        }

        public void runToPosition (int target)
        {
            motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor1.setTargetPosition(target);
            motor2.setTargetPosition(target);
            Dual.this.setPower(1);
            while (motor1.isBusy() || motor2.isBusy())
            {
                int power = (int)((target - Dual.this.getCurrentPosition())*kp);
                Dual.this.setPower(power);
            }
            Dual.this.setPower(0);
            motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
}