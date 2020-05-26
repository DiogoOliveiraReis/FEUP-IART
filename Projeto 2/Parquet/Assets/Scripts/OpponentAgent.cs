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
    public Transform VoidPiece4;
    public Transform PlayerAgent;
    private static Vector3 InitialPosition = new Vector3(250, 250, 0);
    private static Vector3 InitialTargetPosition = new Vector3(-350, -350, 0);
    private static float InitialDistanceToTarget = Vector3.Distance(InitialTargetPosition, InitialPosition);

    public override void OnEpisodeBegin()
    {
        // Reset Pieces Position
        this.transform.localPosition = new Vector3(250, 250, 0);
        Target.localPosition = new Vector3(-350, -350, 0);
        VoidPiece1.localPosition = new Vector3(-50, 50, 0);
        VoidPiece2.localPosition = new Vector3(50, -50, 0);
        VoidPiece3.localPosition = new Vector3(50, 50, 0);
        VoidPiece4.localPosition = new Vector3(-50, -50, 0);
    }
    public override void CollectObservations(VectorSensor sensor)
    {
        sensor.AddObservation(Target.localPosition);
        sensor.AddObservation(VoidPiece1.localPosition);
        sensor.AddObservation(VoidPiece2.localPosition);
        sensor.AddObservation(VoidPiece3.localPosition);
        sensor.AddObservation(VoidPiece4.localPosition);
        sensor.AddObservation(this.transform.localPosition);
    }

    public override void OnActionReceived(float[] vectorAction)
    {
        Vector3 controlSignal = Vector3.zero;
        controlSignal.x = (vectorAction[0] * (float)-43);
        controlSignal.y = (vectorAction[1] * (float)-43);
        rBody.transform.Translate(controlSignal);

        // Reward Based On Distance To Target
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
        float distanceToVoidPiece4 = Vector3.Distance(this.transform.localPosition, VoidPiece4.localPosition);
        if (distanceToVoidPiece4 < 50)
        {
            AddReward(-1);
            EndEpisode();
        }

        // Agent Collision
        float distanceToPlayerAgent = Vector3.Distance(this.transform.localPosition, PlayerAgent.localPosition);
        if (distanceToPlayerAgent < 50)
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
        actionsOut[0] = Input.GetAxisRaw("Horizontal");
        actionsOut[1] = Input.GetAxisRaw("Vertical");
        */
    }
}