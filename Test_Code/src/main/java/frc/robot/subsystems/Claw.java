package frc.robot.subsystems;

import frc.robot.Robot;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXSimCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * The claw subsystem is a simple system with a motor for opening and closing. If using stronger
 * motors, you should probably use a sensor so that the motors don't stall.
 */
public class Claw extends SubsystemBase {
  //private final TalonSRX m_motorRight = new TalonSRX(8);
  private final TalonSRX m_motorLeft = new TalonSRX(1);

  TalonSRXSimCollection m_leftSim = m_motorLeft.getSimCollection();
  //TalonSRXSimCollection m_rightSim = m_motorRight.getSimCollection();
  //private final CAN m_motorLeft = new CAN(0);
  
//   private final DigitalInput m_contact = new DigitalInput(7);
  // private final double m_speed = 0.085;
  private final double m_speed_in = 0.50;
  private final double m_speed_out = -0.85;

  /** Create a new claw subsystem. */
  public Claw() {
    HAL.initialize(200, 0);
    Robot.initTalon(m_motorLeft);
    //Robot.initTalon(m_motorRight);
  }


  public void log() {
    //SmartDashboard.putData("Claw switch", m_contact);
    SmartDashboard.putNumber("Claw - Left", m_leftSim.getMotorOutputLeadVoltage());
    //SmartDashboard.putNumber("Claw - Right", m_motorRight.getMotorOutputPercent());
    // Drivetrain.m_driveSim.update(0.020);
  }

  /** Set the claw motor to move in the open direction. 
 * @return */
  public Command release() {
    return runEnd(() -> {
        m_motorLeft.set(ControlMode.PercentOutput, m_speed_out);
        //m_motorRight.set(ControlMode.PercentOutput, m_speed);
      }, () -> {
        m_motorLeft.set(ControlMode.PercentOutput, 0);
    });
  }

  /** Set the claw motor to move in the close direction. 
 * @return */
  public Command grab() {
    return runEnd(() -> {
        m_motorLeft.set(ControlMode.PercentOutput, m_speed_in);
        //m_motorRight.set(ControlMode.PercentOutput, -m_speed);
    }, () -> {
        m_motorLeft.set(ControlMode.PercentOutput, 0);
    });
  }

  /** Stops the claw motor from moving. 
 * @return */
  public Command stop() {
    return run(() -> {
        m_motorLeft.set(ControlMode.PercentOutput, 0);
    });

  }

//   /** Return true when the robot is grabbing an object hard enough to trigger the limit switch. */
//   public boolean isGrabbing() {
//     return m_contact.get();
//   }

  /** Call log method every loop. */
  @Override
  public void periodic() {
    log();
  }
}
