// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
//import com.revrobotics.spark.SparkBase.ResetMode;


import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.RobotBase;

/**
 * Do NOT add any static variables to this class, or any initialization at all. Unless you know what
 * you are doing, do not modify this file except to change the parameter class to the startRobot
 * call.
 */
public class Pivot {
  public SparkMax pivotMotor;
  public SparkMaxConfig pivotConfig;
  public ArmFeedforward feedforward;
  public PivotState pivotState;
  public SparkClosedLoopController PID;

  
  public enum PivotState {
    GROUND(0),
    //0
    LOWALGAEINTAKE(40.3), //was 163.7
    HIGHALGAEINTAKE(50.5), // was 153.5
    L1CORALSCORE(94.444),
    L2CORALSCORE(110),
    L3CORALSCORE(107),
    L4CORALSCORE(105),
    PROCESSOR(38),
    HUMANSTATIONINTAKE(80.289), //other side is 80.289
    TRANSITIONSTATE(95),
    SHOOTINGNET(80),

    SIGMATEST(120.3),
    CLIMB(33);

    public double position;
    
    
    private PivotState(double position) {
        this.position = position;
        
    }
}
public Pivot()
  {
    pivotMotor = new SparkMax(Ports.pivot, MotorType.kBrushless);
    SparkMaxConfig pivotConfig = new SparkMaxConfig();
    pivotConfig.closedLoop
    .pid(0, 0, 0)
    .feedbackSensor(null);
    pivotConfig.idleMode(IdleMode.kBrake);
    pivotMotor.configure(pivotConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    PID = pivotMotor.getClosedLoopController();
    feedforward = new ArmFeedforward(0, 0, 0);
  }
  public void updatePose()
  {
    PID.setReference(pivotState.position, ControlType.kPosition, ClosedLoopSlot.kSlot0, -(feedforward.calculate(pivotMotor.getEncoder().getPosition(), 0) ));
  }
  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>If you change your main robot class, change the parameter type.
   */
}
