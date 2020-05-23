using System;
using System.Collections;
using UnityEngine;
using Unity.MLAgents;
using Unity.MLAgents.Sensors;
using System.Security.AccessControl;
using System.Security.Cryptography;
using System.Collections.Specialized;

public class OpponentAgent : Agent
{
    Rigidbody rBody;
    void Start()
    {
        rBody = GetComponent<Rigidbody>();
    }

    public Transform Target;
    public Transform VoidPiece1;
    public Transform VoidPiece2;
    public Transform VoidPiece3;
    private static Vector3 InitialPosition = new Vector3(250, 250, 0);
    private static Vector3 InitialTargetPosition = new Vector3(-350, -350, 0);
    private static float InitialDistanceToTarget = Vector3.Distance(InitialTargetPosition, InitialPosition);

    public override void OnEpisodeBegin()
    {
        // If the Agent fell, zero its momentum
        /*
        this.rBody.angularVelocity = Vector3.zero;
        this.rBody.velocity = Vector3.zero;
        */
        this.transform.localPosition = new Vector3(250, 250, 0);

        // Move the target to a new spot
        Target.localPosition = new Vector3(-350, -350, 0);
        VoidPiece1.localPosition = new Vector3(-50, 50, 0);
        VoidPiece2.localPosition = new Vector3(50, -50, 0);
        VoidPiece3.localPosition = new Vector3(50, 50, 0);
    }
    public override void CollectObservations(VectorSensor sensor)
    {
        sensor.AddObservation(Target.localPosition);
        sensor.AddObservation(VoidPiece1.localPosition);
        sensor.AddObservation(VoidPiece2.localPosition);
        sensor.AddObservation(VoidPiece3.localPosition);
        sensor.AddObservation(this.transform.localPosition);
    }

    public override void OnActionReceived(float[] vectorAction)
    {
        Vector3 controlSignal = Vector3.zero;
        controlSignal.x = (vectorAction[0] * (float)-43);
        controlSignal.y = (vectorAction[1] * (float)-43);
        rBody.transform.Translate(controlSignal);

        float distanceToTarget = Vector3.Distance(this.transform.localPosition, Target.localPosition);
        AddReward(InitialDistanceToTarget - distanceToTarget);

        // Reached Target
        if (distanceToTarget < 50)
        {
            AddReward(1);
            EndEpisode();
        }

        // Hit Void Piece
        float distanceToVoidPiece1 = Vector3.Distance(this.transform.localPosition, VoidPiece1.localPosition);
        if (distanceToVoidPiece1 < 50)
        {
            AddReward(-1);
            EndEpisode();
        }
        float distanceToVoidPiece2 = Vector3.Distance(this.transform.localPosition, VoidPiece2.localPosition);
        if (distanceToVoidPiece2 < 50)
        {
            AddReward(-1);
            EndEpisode();
        }
        float distanceToVoidPiece3 = Vector3.Distance(this.transform.localPosition, VoidPiece3.localPosition);
        if (distanceToVoidPiece3 < 50)
        {
            AddReward(-1);
            EndEpisode();
        }

        // Hit Board Limit
        if (this.transform.localPosition.x > 355 || this.transform.localPosition.x < -355 || this.transform.localPosition.y > 355 || this.transform.localPosition.y < -355)
        {
            AddReward(-1);
            EndEpisode();
        }
    }

    public override void Heuristic(float[] actionsOut)
    {
        /*
        actionsOut[0] = Math.Abs(Input.GetAxisRaw("Horizontal"));
        actionsOut[1] = Math.Abs(Input.GetAxisRaw("Vertical"));
        */
        actionsOut[0] = Input.GetAxisRaw("Horizontal");
        actionsOut[1] = Input.GetAxisRaw("Vertical");
    }
}