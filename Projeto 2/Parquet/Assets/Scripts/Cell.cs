using System.Collections;
using System.Collections.Generic;
using System.Reflection;
using UnityEngine;
using UnityEngine.UI;

public class Cell : MonoBehaviour
{
    public Piece mCurrentPiece = null;
    public Image outlineImage;
    public Vector2Int boardPosition = Vector2Int.zero;
    public Board board = null;
    public RectTransform rectTransform = null;

    public void Setup(Vector2Int newBoardPosition, Board newBoard)
    {
        boardPosition = newBoardPosition;
        board = newBoard;
        rectTransform = GetComponent<RectTransform>();
    }

    void Awake()
    {

    }

    void Start()
    {
        
    }

    void Update()
    {
        
    }
}
