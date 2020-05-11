using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Main : MonoBehaviour
{
    public Board mBoard;
    public PieceManager pieceManager;
    // Start is called before the first frame update
    void Start()
    {
        mBoard.Create();
        pieceManager.Setup(mBoard);
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
