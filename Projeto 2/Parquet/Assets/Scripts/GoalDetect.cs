using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GoalDetect : MonoBehaviour
{
    [HideInInspector]
    public AgentScript agent;

    void OnCollisionEnter(Collision col)
    {
        if(col.gameObject.CompareTag("goal"))
        {
            agent.ScoredAGoal();
        }
    }
}
