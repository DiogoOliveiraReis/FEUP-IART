using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Diagnostics;
using System.Security.Cryptography;
using UnityEngine;

public class PieceManager : MonoBehaviour
{
    public GameObject PiecePrefab;

    private Dictionary<string, Type> mPieceLibrary = new Dictionary<string, Type>()
    {
        {"P",  typeof(Piece)}
    };

    public void Setup(Board board)
    {
        /*
        PlacePiece(2, 1, 1, board);
        PlacePiece(3, 6, 6, board);
        */
    }

    private void PlacePiece(int player, int x, int y, Board board)
    {
        GameObject newPieceObject = Instantiate(PiecePrefab);
        newPieceObject.transform.SetParent(transform);
        newPieceObject.transform.localScale = new Vector3(1, 1, 1);
        newPieceObject.transform.localRotation = Quaternion.identity;
        Type pieceType = mPieceLibrary["P"];
        Piece newPiece = (Piece)newPieceObject.AddComponent(pieceType);
        if (player == 2) {
            newPiece.Setup(Color.blue, new Color32(80, 124, 159, 255), this);
        }
        else {
            newPiece.Setup(Color.red, new Color32(210, 95, 64, 255), this);
        }
        newPiece.Place(board.mAllCells[x, y]);
    }

    void Update()
    {

    }
}