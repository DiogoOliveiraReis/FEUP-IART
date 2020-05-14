using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using MLAgents;

public class AgentScript : Agent
{
    public GameObject board;
    public GameObject background;

    public Bounds boardBounds;

    ParquetAcademy academy;

    public GameObject goal;

    public GoalDetect goalDetect;

    void Awake()
    {
        academy = FindObjectOfType<ParquetAcademy>();
    }

    public override void InitializeAgent()
    {
        base.InitializeAgent();
        goalDetect = agent.GetComponent<GoalDetect>();
        goalDetect.agent = this;
    }

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
