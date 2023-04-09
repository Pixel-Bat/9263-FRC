package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * The claw subsystem is a simple system with a motor for opening and closing. If using stronger
 * motors, you should probably use a sensor so that the motors don't stall.
 */
public class Arm extends SubsystemBase {
  private final CANSparkMax motor;
  private final RelativeEncoder encoder;
  private final SparkMaxPIDController controller;

  private double angle;
  
  /** Create a new claw subsystem. */
  public Arm() {
    this.motor = new CANSparkMax(10, CANSparkMaxLowLevel.MotorType.kBrushless);
    
    this.motor.restoreFactoryDefaults();
    this.motor.setInverted(true);
    this.motor.setSmartCurrentLimit(60, 20);
    this.motor.setIdleMode(IdleMode.kBrake);

    this.encoder = this.motor.getEncoder();
    this.encoder.setPositionConversionFactor(Constants.ARM_POSITION_CONVERSION);

    this.controller = this.motor.getPIDController();
    SmartDashboard.putNumber("P", 0);
    SmartDashboard.putNumber("I", 0);
    SmartDashboard.putNumber("D", 0);
    SmartDashboard.putNumber("FF", 0);
    SmartDashboard.putNumber("Angle", 0);
    // this.controller.setP(Constants.PID_CONSTANTS.kP);
    // this.controller.setI(Constants.PID_CONSTANTS.kI);
    // this.controller.setD(Constants.PID_CONSTANTS.kD);
    // this.controller.setFF(Constants.PID_CONSTANTS.kFF);
  }

  public Command retractArm() {
    return runOnce(() -> {
      this.setArmPosition(0);
    });
  }

  public Command deployArm() {
    return runOnce(() -> {
      this.setArmPosition(angle);
    });
  }

  public void resetEncoder() {
    this.encoder.setPosition(0);
  }

  /**
   * Sets the arm position in degrees
   * @param angle
   */
  private void setArmPosition(double angle) {
    this.controller.setReference(angle, ControlType.kPosition);
  }

  public void log() {
    SmartDashboard.putNumber("Shoulder Position", this.encoder.getPosition());
    
    this.controller.setP(SmartDashboard.getNumber("P", 0));
    this.controller.setI(SmartDashboard.getNumber("I", 0));
    this.controller.setD(SmartDashboard.getNumber("D", 0));
    this.controller.setFF(SmartDashboard.getNumber("FF", 0));
    angle = SmartDashboard.getNumber("Angle", 30);
  }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}