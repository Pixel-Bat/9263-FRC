// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  private final PWMSparkMax frontLeftMotor;
  private final PWMSparkMax frontRightMotor;
  private final PWMSparkMax backLeftMotor;
  private final PWMSparkMax backRightMotor;

  private final MotorControllerGroup driveLeft;
  private final MotorControllerGroup driveRight;

  private final DifferentialDrive drive;

  /** Creates a new ExampleSubsystem. */
  public Drivetrain() {
    this.frontLeftMotor = new PWMSparkMax(Constants.FRONT_LEFT_PORT);
    this.frontRightMotor = new PWMSparkMax(Constants.FRONT_RIGHT_PORT);
    this.backLeftMotor = new PWMSparkMax(Constants.BACK_LEFT_PORT);
    this.backRightMotor = new PWMSparkMax(Constants.BACK_RIGHT_PORT);

    this.driveLeft = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
    this.driveRight = new MotorControllerGroup(frontRightMotor, backRightMotor);

    this.driveRight.setInverted(true);

    this.drive = new DifferentialDrive(driveLeft, driveRight);
  }

  /**
   * Command that takes in a double supplier method
   * 
   * @param forward
   * @param rotation
   * @return Arcade Drive Command
   */
  public Command arcadeDriveCommand(DoubleSupplier forward, DoubleSupplier rotation) {
    return run(() -> {
      this.drive.arcadeDrive(forward.getAsDouble(), rotation.getAsDouble());
    });
  }

  /**
   * Returns a Command that drives at a certain percentage
   * 
   * @param forward
   * @param rotation
   * @return
   */
  public Command drive(double forward, double rotation) {
    return runEnd(() -> {
      this.drive.arcadeDrive(forward, rotation);
    }, this.drive::stopMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
