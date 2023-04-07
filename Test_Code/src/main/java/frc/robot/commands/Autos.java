// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command driveBackAuto(Drivetrain drive) {
    return Commands.sequence(
      drive.drive(1, 0).withTimeout(0.3),
      drive.drive(-0.5, 0).withTimeout(2),
      drive.drive(0.75, 0).withTimeout(2)
    );
  }

  // public static Command driveStraightAuto(Drivetrain drive) {
  //   return Commands.sequence(
  //     drive.drive(1, 0).withTimeout(1),
  //     drive.drive(-0.25, 0).withTimeout(3),
  //     drive.drive(0.5, 0).withTimeout(4)
  //   );
  // }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
