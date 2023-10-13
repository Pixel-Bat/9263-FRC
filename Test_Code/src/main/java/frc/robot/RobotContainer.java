// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.commands.Autos;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Arm;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.subsystems.Camera;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain drivetrain = new Drivetrain();
  private final Claw claw = new Claw();
  private final Arm arm = new Arm();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandJoystick driveController =
      new CommandJoystick(Constants.DRIVER_PORT);
  private final XboxController m_controllerTemp = new XboxController(Constants.DRIVER_PORT);

  private Camera camera;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    this.drivetrain.setDefaultCommand(
      this.drivetrain.arcadeDriveCommand(this.driveController::getY, this.driveController::getZ));

    // Configure the trigger bindings

    SmartDashboard.putData(drivetrain);
    SmartDashboard.putData(arm);
    SmartDashboard.putData(claw);

    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    final JoystickButton ReleaseButton = new JoystickButton(m_controllerTemp, 8);
    final JoystickButton GrabButton = new JoystickButton(m_controllerTemp, 7);
    final JoystickButton RetractButton = new JoystickButton(m_controllerTemp, 5);
    final JoystickButton DeployButton = new JoystickButton(m_controllerTemp, 6);
 
    // Connect the buttons to commands
    ReleaseButton.whileTrue(claw.release());
    GrabButton.whileTrue(claw.grab());
    RetractButton.whileTrue(arm.retractArm());
    DeployButton.whileTrue(arm.deployArm());
  }

  public void Initialize() {
    this.arm.resetEncoder();

    camera = new Camera();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.driveBackAuto(drivetrain);
  }
}
